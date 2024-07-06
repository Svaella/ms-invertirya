package inverte.ya.api.service;

import inverte.ya.api.model.Article;

import java.util.List;

public interface IArticleService extends ICRUD<Article, Integer>
{
    List<Article> saveAll(List<Article> t) throws Exception;
}

