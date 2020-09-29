package com.tianling.blog.elasticsearch.dao;

import com.tianling.blog.beans.ArticleInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tianling
 * @since 2020-05-08
 */
@Component
public interface ArticleInfoRepository extends ElasticsearchRepository<ArticleInfo,Integer> {
    List< ArticleInfo> findArticleInfoByArticleId(Integer articleId );
}
