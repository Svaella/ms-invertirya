package inverte.ya.api.service.impl;

import inverte.ya.api.model.Article;
import inverte.ya.api.repo.IArticleRepo;
import inverte.ya.api.repo.IGenericRepo;
import inverte.ya.api.service.IArticleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl extends CRUDImpl<Article, Integer> implements IArticleService
{

    private final IArticleRepo repo;
    @Override
    protected IGenericRepo<Article, Integer> getRepo() {
        return repo;
    }

    @Override
    public Article save(Article article) {
        article.setCreatedAt(LocalDateTime.now());
        return super.save(article);
    }

    @Transactional
    @Override
    public Article update(Article article, Integer integer)
    {
        Article articleFound = repo.findById(integer).orElseThrow(()->new RuntimeException("Article not found"));
        article.setCreatedAt(articleFound.getCreatedAt());
        return super.update(article, integer);
    }

    @Override
    public List<Article> saveAll(List<Article> t) throws Exception {
        repo.deleteAll();
        t.forEach(article -> article.setCreatedAt(LocalDateTime.now()));
        return repo.saveAll(t);
    }
}

