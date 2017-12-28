package LuceneUtilTest;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.CachingCollector;
import org.apache.lucene.search.Collector;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiCollector;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.grouping.GroupDocs;
import org.apache.lucene.search.grouping.SearchGroup;
import org.apache.lucene.search.grouping.TermAllGroupsCollector;
import org.apache.lucene.search.grouping.TermFirstPassGroupingCollector;
import org.apache.lucene.search.grouping.TermSecondPassGroupingCollector;
import org.apache.lucene.search.grouping.TopGroups;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Test {
    private static final String PATH = "F:\\lucene\\test";
    
    public static void main(String args[]) {
        LuceneCreateIndexUtil.createIndexbyPath(PATH);// 创建索引
        
        IndexReader reader;
        try {
            // 创建测试索引
            // createIndex();
            Directory directory = FSDirectory.open(new File(PATH));
            reader = IndexReader.open(directory);
            IndexSearcher searcher = new IndexSearcher(reader);
            // Query query = new TermQuery(new Term("content", "like"));
            Query query = new TermQuery(new Term("email", "aa@wdxxl.com"));
            /** 每个分组内部的排序规则 */
            SortField sortField = new SortField("content", SortField.STRING_VAL);
            // Sort groupSort = Sort.RELEVANCE;
            Sort groupSort = new Sort(sortField);
            groupBy(searcher, query, groupSort);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
    }
    
    public static void groupBy(IndexSearcher searcher, Query query, Sort groupSort) {
        /** 前N条中分组 */
        int topNGroups = 10;
        /** 分组起始偏移量 */
        int groupOffset = 0;
        /** 是否填充SearchGroup的sortValues */
        boolean fillFields = true;
        /** groupSort用于对组进行排序，docSort用于对组内记录进行排序，多数情况下两者是相同的，但也可不同 */
        Sort docSort = groupSort;
        /** 用于组内分页，起始偏移量 */
        int docOffset = 0;
        /** 每组返回多少条结果 */
        int docsPerGroup = 2;
        /** 是否需要计算总的分组数量 */
        boolean requiredTotalGroupCount = true;
        /** 是否需要缓存评分 */
        boolean cacheScores = true;
        
        try {
            TermFirstPassGroupingCollector c1 = new TermFirstPassGroupingCollector("content", groupSort,
                    groupOffset + topNGroups);
            
            // 第一次查询缓存容量的大小：设置为16M
            double maxCacheRAMMB = 16.0;
            /**
             * 将TermFirstPassGroupingCollector包装成CachingCollector，为第一次查询加缓存，避免重复评分
             * CachingCollector就是用来为结果收集器添加缓存功能的
             */
            CachingCollector cachedCollector = CachingCollector.create(c1, cacheScores, maxCacheRAMMB);
            // 开始第一次分组统计
            searcher.search(query, cachedCollector);
            
            /** 第一次查询返回的结果集TopGroups中只有分组域值以及每组总的评分，至于每个分组里有几条，分别哪些索引文档，则需要进行第二次查询获取 */
            Collection<SearchGroup<String>> topGroups = c1.getTopGroups(groupOffset, fillFields);
            if (topGroups == null) {
                System.out.println("No groups matched ");
                return;
            }
            System.out.println(topGroups.size());
            System.out.println(topGroups);
            
            Collector secondPassCollector = null;
            
            // 是否获取每个分组内部每个索引的评分
            boolean getScores = true;
            // 是否计算最大评分
            boolean getMaxScores = true;
            // 如果需要对Lucene的score进行修正，则需要重载TermSecondPassGroupingCollector
            TermSecondPassGroupingCollector c2 = new TermSecondPassGroupingCollector("content", topGroups, groupSort, docSort,
                    docOffset + docsPerGroup, getScores, getMaxScores, fillFields);
            
            // 如果需要计算总的分组数量，则需要把TermSecondPassGroupingCollector包装成TermAllGroupsCollector
            // TermAllGroupsCollector就是用来收集总分组数量的
            TermAllGroupsCollector allGroupsCollector = null;
            // 若需要统计总的分组数量
            if (requiredTotalGroupCount) {
                allGroupsCollector = new TermAllGroupsCollector("content");
                secondPassCollector = MultiCollector.wrap(c2, allGroupsCollector);
            } else {
                secondPassCollector = c2;
            }
            
            /** 如果第一次查询已经加了缓存，则直接从缓存中取 */
            if (cachedCollector.isCached()) {
                // 第二次查询直接从缓存中取
                cachedCollector.replay(secondPassCollector);
            } else {
                // 开始第二次分组查询
                searcher.search(query, secondPassCollector);
            }
            
            /** 所有组的数量 */
            int totalGroupCount = 0;
            /** 所有满足条件的记录数 */
            int totalHitCount = 0;
            /** 所有组内的满足条件的记录数(通常该值与totalHitCount是一致的) */
            int totalGroupedHitCount = -1;
            if (requiredTotalGroupCount) {
                totalGroupCount = allGroupsCollector.getGroupCount();
            }
            // 打印总的分组数量
            System.out.println("groupCount: " + totalGroupCount);
            
            TopGroups<String> groupsResult = c2.getTopGroups(docOffset);
            // 这里打印的3项信息就是第一次查询的统计结果
            totalHitCount = groupsResult.totalHitCount;
            totalGroupedHitCount = groupsResult.totalGroupedHitCount;
            System.out.println("groupsResult.totalHitCount:" + totalHitCount);
            System.out.println("groupsResult.totalGroupedHitCount:" + totalGroupedHitCount);
            System.out.println("///////////////////////////////////////////////");
            
            int groupIdx = 0;
            // 下面打印的是第二次查询的统计结果，如果你仅仅值需要第一次查询的统计结果信息，不需要每个分组内部的详细信息，则不需要进行第二次查询，请知晓
            // 迭代组
            for (GroupDocs<String> groupDocs : groupsResult.groups) {
                groupIdx++;
                String groupVL = groupDocs.groupValue == null ? "分组域的域值为空" : new String(groupDocs.groupValue.getBytes());
                // 分组域的域值，groupIdx表示组的索引即第几组
                // System.out.println("group[" + groupIdx + "].groupFieldValue:" + groupVL);
                // // 当前分组内命中的总记录数
                // System.out.println("group[" + groupIdx + "].totalHits:" + groupDocs.totalHits);
                // System.out.println("**********************************************************");
                int docIdx = 0;
                // 迭代组内的记录
                for (ScoreDoc scoreDoc : groupDocs.scoreDocs) {
                    docIdx++;
                    // 打印分组内部每条记录的索引文档ID及其评分
                    System.out.println(
                            "group[" + groupIdx + "][" + docIdx + "]{docID:Score}:" + scoreDoc.doc + "/" + scoreDoc.score);
                    // 根据docID可以获取到整个Document对象，通过doc.get(fieldName)可以获取某个存储域的域值
                    // 注意searcher.doc根据docID返回的document对象中不包含docValuesField域的域值，只包含非docValuesField域的域值，请知晓
                    Document doc = searcher.doc(scoreDoc.doc);
                    System.out.println("group[" + groupIdx + "][" + docIdx + "]{docID:content}:" + doc.get("id") + ":"
                            + doc.get("content"));
                }
                System.out.println("*********************************************************");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
