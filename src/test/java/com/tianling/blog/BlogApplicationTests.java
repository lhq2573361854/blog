package com.tianling.blog;

import cn.hutool.core.io.FileUtil;
import com.tianling.blog.beans.ArticleInfo;
import com.tianling.blog.elasticsearch.dao.ArticleInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    ArticleInfoRepository articleInfoRepository;
    @Test
    void contextLoads() {
    }
    @Test
    public void testGenerator() {
        ArticleInfo articleInfoBean = new ArticleInfo();
        articleInfoBean.setArticleId(123123);
        articleInfoBean.setUserId(1115052);
        articleInfoBean.setArticleRecom("123123");
        articleInfoBean.setArticleTitle("xiaoming");
        //articleInfoRepository.save(articleInfoBean);
        long count = articleInfoRepository.count();
        articleInfoRepository.index(articleInfoBean);
        System.out.println("count = " + count);
    }

    public static void main(String[] args) throws IOException {
//        Ftp ftp = new Ftp("39.96.67.142");
//
//        ftp.cd("./backup/images");
//
//        ftp.upload("/opt/upload", FileUtil.file("C:/Users/TianLing/Desktop/English.txt"));
//
//        ftp.download("/opt/upload", "test.jpg", FileUtil.file("e:/test2.jpg"));
//
//        ftp.close();



    }

}

class Demo{
    public static void main(String[] args) throws IOException {
//        Ftp ftp = new Ftp("39.96.67.142");
//
//        ftp.cd("backup/images/");
//        ftp.setMode(FtpMode.Passive);
//
//        ftp.upload("backup/images/", FileUtil.file("d:/1.bmp"));
//
//        ftp.download("/backup/config/", "my.cnf", FileUtil.file("d:/my.cnf"));
//
//        ftp.close();
        for (int i = 0; i < 2; i++) {
            File tempFile = FileUtil.createTempFile(new File("D:/file/upload/"));
        }
    }
}
