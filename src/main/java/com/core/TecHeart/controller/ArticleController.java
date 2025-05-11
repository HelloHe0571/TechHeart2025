package com.core.TecHeart.controller;

import com.core.TecHeart.entity.Article;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @RequestMapping("/createArticle")
    public Result<Article> createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }

    @RequestMapping("/updateArticle")
    public Result<Article> updateArticle(@RequestBody Article article) {
        return articleService.updateArticle(article);
    }

    @RequestMapping("/deleteArticle")
    public Result<Void> deleteArticle(@RequestBody Integer id) {
        return articleService.deleteArticle(id);
    }

    @RequestMapping("/getArticleById")
    public Result<Article> getArticleById(@RequestBody Integer id) {
        return articleService.getArticleById(id);
    }

    @RequestMapping("/getAllArticles")
    public Result<List<Article>> getAllArticles() {
        return articleService.getAllArticles();
    }

    @RequestMapping("/searchArticles")
    public Result<List<Article>> searchArticles(@RequestBody Map<String, Object> params) {
        return articleService.searchArticles(params);
    }

    @RequestMapping("/getArticlesByCategory")
    public Result<List<Article>> getArticlesByCategory(@RequestBody Integer categoryId) {
        return articleService.getArticlesByCategory(categoryId);
    }

    @RequestMapping("/getLatestArticles")
    public Result<List<Article>> getLatestArticles(@RequestParam(defaultValue = "5") int limit) {
        return articleService.getLatestArticles(limit);
    }
}
