package com.core.TecHeart.mapper;

import com.core.TecHeart.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {
    int insertArticle(Article article);
    int updateArticle(Article article);
    int deleteArticle(Integer articleId);
    Article selectArticleById(Integer articleId);
    List<Article> selectAllArticles();
    List<Article> searchArticles(Map<String, Object> params);
    List<Article> selectByCategory(Integer categoryId);
    List<Article> selectLatestArticles(int limit);
}
