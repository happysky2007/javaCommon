package LuceneUtilTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class TextFileIndexer {
    
    public static void main(String[] args) throws Exception {
        // 需要索引的源文件位置
        File fileDir = new File("F:\\Lucene\\source");
        // 存放索引的文件位置
        File indexDir = new File("F:\\Lucene\\index");
        // 把索引建立在硬盘中
        Directory dir = FSDirectory.open(indexDir);
        // 建立一个标准分词器
        Analyzer luceneAnalyzer = new StandardAnalyzer(Version.LUCENE_35);
        //
        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_35, luceneAnalyzer);
        // 每次执行都是创建新的索引而不是追加索引
        iwc.setOpenMode(OpenMode.CREATE);
        // 创建一个索引器
        IndexWriter indexWriter = new IndexWriter(dir, iwc);
        // 保存源文件的多个文件到数组File[]中
        File[] textFiles = fileDir.listFiles();
        // 索引的开始时间
        long startTime = new Date().getTime();
        
        // for循环遍历源目录下的文件
        for (int i = 0; i < textFiles.length; i++) {
            if (textFiles[i].isFile() && textFiles[i].getName().endsWith(".txt")) { // 获取相对路径???
                System.out.println("文件" + textFiles[i].getCanonicalPath() + "正在被索引...");
                // 调用自定义读取文件内容的方法FileReaderAll()
                String temp = FileReaderAll(textFiles[i].getCanonicalPath(), "GBK");
                // 打印读取到的内容
                System.out.println(temp);
                // 为每一个文件的索引信息建立一个document对象
                Document document = new Document();
                // 文件路径索引：只是存储 不建立路径的索引，因为我们不需要对路径进行查询
                Field fieldPath = new Field("path", textFiles[i].getPath(), Field.Store.YES, Field.Index.NO);
                // 文件内容索引：
                Field fieldBody = new Field("body", temp, Field.Store.YES, Field.Index.ANALYZED,
                        Field.TermVector.WITH_POSITIONS_OFFSETS);
                // 添加各域到document文档对象中
                document.add(fieldPath);
                document.add(fieldBody);
                // 将文档写入索引
                indexWriter.addDocument(document);
            }
        }
        // 关闭
        indexWriter.close();
        
        // 测试一下索引时间
        long endTime = new Date().getTime();
        // getPath,getAbsolutePath,getCanonicalPath的区别参考转载的文章
        System.out.println("用时：" + (endTime - startTime) + "毫秒来把文档增加到索引里面去," + fileDir.getPath());
        System.out.println("用时：" + (endTime - startTime) + "毫秒来把文档增加到索引里面去," + fileDir.getAbsolutePath());
        System.out.println("用时：" + (endTime - startTime) + "毫秒来把文档增加到索引里面去," + fileDir.getCanonicalPath());
    }
    
    // 自定义读取文件内容的方法 FileReaderAll()
    public static String FileReaderAll(String FileName, String charset) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FileName), charset));// 知制定读取文件方式charset:GBK
        
        String line = new String();
        String temp = new String();
        
        while ((line = reader.readLine()) != null) {
            temp += line;
        }
        reader.close();
        return temp;
    }
    
}
