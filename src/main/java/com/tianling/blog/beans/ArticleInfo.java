package com.tianling.blog.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tianling
 * @since 2020-05-08
 */
@Document(indexName = "article",type = "articleInfoBean", shards = 1,replicas = 0, refreshInterval = "-1")
public class ArticleInfo extends Model<ArticleInfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "article_id", type = IdType.AUTO)
    @Id
    private Integer articleId;
    @Field
    private Integer userId;
    @Field
    private Integer categoryId;
    @Field
    private String articleTitle;
    @Field
    private String articleContent;
    @Field
    private String articleImg;
    @Field
    private String articleRecom;
    @Field
    @DateTimeFormat(pattern = "yyyy-MM-dd\'T\'HH:mm:ss.SSS")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime articleTime;
    @Field
    private String articleMark;


    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleImg() {
        return articleImg;
    }

    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg;
    }

    public String getArticleRecom() {
        return articleRecom;
    }

    public void setArticleRecom(String articleRecom) {
        this.articleRecom = articleRecom;
    }

    public LocalDateTime getArticleTime() {
        return articleTime;
    }

    public void setArticleTime(LocalDateTime articleTime) {
        this.articleTime = articleTime;
    }

    public String getArticleMark() {
        return articleMark;
    }

    public void setArticleMark(String articleMark) {
        this.articleMark = articleMark;
    }

    @Override
    protected Serializable pkVal() {
        return this.articleId;
    }

    @Override
    public String toString() {
        return "ArticleInfo{" +
        "articleId=" + articleId +
        ", userId=" + userId +
        ", categoryId=" + categoryId +
        ", articleTitle=" + articleTitle +
        ", articleContent=" + articleContent +
        ", articleImg=" + articleImg +
        ", articleRecom=" + articleRecom +
        ", articleTime=" + articleTime +
        ", articleMark=" + articleMark +
        "}";
    }
}
