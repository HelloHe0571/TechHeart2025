package com.core.TecHeart.service.impl;

import com.core.TecHeart.entity.Article;
import com.core.TecHeart.entity.HealthAdvice;
import com.core.TecHeart.mapper.ArticleMapper;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public Result<Article> createArticle(Article article) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);

        article.setCreatedAt(str);
        article.setUpdatedAt(str);
        Result<Article> result = new Result<>();
        int flag = articleMapper.insertArticle(article);
        if (flag > 0){
            result.setResultSuccess("文章添加成功",article);
        }else{
            result.setResultFailed("文章添加失败");
        }
        return result;
    }

    @Override
    public Result<Article> updateArticle(Article article) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);

        article.setUpdatedAt(str);
        Result<Article> result = new Result<>();
        int flag = articleMapper.updateArticle(article);
        if (flag > 0){
            result.setResultSuccess("文章修改成功",article);
        }else{
            result.setResultFailed("文章修改失败");
        }
        return result;
    }

    @Override
    public Result<Void> deleteArticle(Integer articleId) {
        Result<Void> result = new Result<>();
        int flag = articleMapper.deleteArticle(articleId);
        if (flag > 0){
            result.setResultSuccess("文章删除成功");
        }else{
            result.setResultFailed("文章删除失败");
        }

        return result;
    }

    @Override
    public Result<Article> getArticleById(Integer articleId) {
        Result<Article> result = new Result<>();
        Article article = articleMapper.selectArticleById(articleId);
        if (article != null){
            result.setResultSuccess("查询成功",article);
        }else{
            result.setResultFailed("查询失败");
        }
        return result;
    }

    @Override
    public Result<List<Article>> getAllArticles() {
        Result<List<Article>> result = new Result<>();
        List<Article> list = articleMapper.selectAllArticles();
        result.setResultSuccess("查询成功",list);
        return result;
    }

    @Override
    public Result<List<Article>> searchArticles(Map<String, Object> params) {
        Result<List<Article>> result = new Result<>();
        List<Article> list = articleMapper.searchArticles(params);
        result.setResultSuccess("查询成功",list);
        return result;
    }

    @Override
    public Result<List<Article>> getArticlesByCategory(Integer categoryId) {
        Result<List<Article>> result = new Result<>();
        List<Article> list = articleMapper.selectByCategory(categoryId);
        result.setResultSuccess("查询成功",list);
        return result;
    }

    @Override
    public Result<List<Article>> getLatestArticles(int limit) {
        Result<List<Article>> result = new Result<>();
        List<Article> list = articleMapper.selectLatestArticles(limit);
        result.setResultSuccess("查询成功",list);
        return result;
    }
}
