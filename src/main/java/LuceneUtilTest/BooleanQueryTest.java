
package LuceneUtilTest;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class BooleanQueryTest {
    
    private static final String PATH = "/lib/lucene35/query";
    private static Directory directory;
    
    public static void main(String args[]) {
        searchByBooleanQuery();
    }
    
    static {
        try {
            directory = FSDirectory.open(new File("C:\\Users\\ezeng\\Desktop\\1113\\pinpoint_server_frmfim"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // 3. 可以连接多个条件BooleanQuery
    public static void searchByBooleanQuery() {
        try {
            // 单例获取IndexReader
            System.out.println("");
            
            IndexReader reader = LuceneIndexReaderUtil.getIndexReader(directory);
            BooleanQuery query = new BooleanQuery();
            query.add(new TermQuery(new Term("pps_frmfim_library_id", "b8d654da-53c1-4328-b831-1573c12e8fef")), Occur.MUST);
            query.add(new TermQuery(new Term("pps_frmfim_publication_id", "e6dbf0fe-6a5e-4cf1-898f-52e3202763e3")), Occur.MUST);
            query.add(new TermQuery(new Term("pps_frmfim_data_type", "message")), Occur.MUST);
            query.add(new WildcardQuery(new Term("pps_frmfim_msg_number", "22-11395")), Occur.MUST);
            output(new IndexSearcher(reader), query);
            System.out.println("");
            /*
             * (0-0.81665707)email:aa@wdxxl.com,name:zhangsan,attach:2,date:1368342000000
             * (3-0.1328938)email:dd@sina.org,name:jetty,attach:4,date:1463036400000
             */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void output(IndexSearcher searcher, Query query) throws CorruptIndexException, IOException {
        TopDocs tds = searcher.search(query, 10);
        System.out.println(" int total_number = " + tds.totalHits);
        for (ScoreDoc sd : tds.scoreDocs) {
            Document doc = searcher.doc(sd.doc);
            // sd.score 评分有对应的公式：加权值和文档所出现的次数有关
            // System.out.println("(" + sd.doc + "-" + sd.score + ")" + "email:" + doc.get("email") + ",name:" + doc.get("name")
            // + ",attach:" + doc.get("attach") + ",date:" + doc.get("date"));
        }
    }
    
}
