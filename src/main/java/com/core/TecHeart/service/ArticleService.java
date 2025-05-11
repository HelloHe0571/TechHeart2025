package com.core.TecHeart.service;

import com.core.TecHeart.entity.Article;
import com.core.TecHeart.model.Result;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    Result<Article> createArticle(Article article);
    Result<Article> updateArticle(Article article);
    Result<Void> deleteArticle(Integer articleId);
    Result<Article> getArticleById(Integer articleId);
    Result<List<Article>> getAllArticles();
    Result<List<Article>> searchArticles(Map<String, Object> params);
    Result<List<Article>> getArticlesByCategory(Integer categoryId);
    Result<List<Article>> getLatestArticles(int limit);
}
