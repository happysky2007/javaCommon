package LuceneUtilTest;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class TestQuery {
    public static void main(String[] args) throws IOException, ParseException {
        // 搜索的索引路径
        String index = "F:\\Lucene\\index";
        
        IndexReader reader = IndexReader.open(FSDirectory.open(new File(index)));
        // 定义在索引库中进行查询的searcher
        IndexSearcher searcher = new IndexSearcher(reader);
        
        ScoreDoc[] hits = null;
        // 检索词
        String queryString = "M";
        // 声明query对象
        Query query = null;
        // 分词器
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
        
        try {
            QueryParser qp = new QueryParser(Version.LUCENE_35, "body", analyzer);
            query = qp.parse(queryString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        if (searcher != null) {
            TopDocs results = searcher.search(query, 10);
            hits = results.scoreDocs;
            
            if (hits.length > 0) {
                System.out.println("找到hits.length=" + hits.length + "个结果\n" + "找到results.totalHits=" + results.totalHits);
            }
            searcher.close();
        }
    }
    
}
