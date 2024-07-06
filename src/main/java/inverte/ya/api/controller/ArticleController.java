package inverte.ya.api.controller;

import inverte.ya.api.dto.ArticleDTO;
import inverte.ya.api.model.Article;
import inverte.ya.api.service.IArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController
{
    private final IArticleService service;
    private final ModelMapper mapper;

    @PostMapping()
    public ResponseEntity<ArticleDTO> save(@RequestBody @Valid ArticleDTO article)
    {
        ArticleDTO articleDTO = this.convertToDTO(service.save(this.convertToEntity(article)));
        return new ResponseEntity<>(articleDTO, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<ArticleDTO> update(@RequestBody @Valid ArticleDTO article)
    {
        ArticleDTO articleDTO = this.convertToDTO(service.update(this.convertToEntity(article), article.getId()));
        return new ResponseEntity<>(articleDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> readById(@PathVariable Integer id)
    {
        ArticleDTO articleDTO = this.convertToDTO(service.readById(id));
        return new ResponseEntity<>(articleDTO, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ArticleDTO>> findAll()
    {
        List<ArticleDTO> articles = service.readAll().stream().map(this::convertToDTO).toList();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id)
    {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Article convertToEntity(ArticleDTO articleDTO)
    {
        return mapper.map(articleDTO, Article.class);
    }

    private ArticleDTO convertToDTO(Article article)
    {
        return mapper.map(article, ArticleDTO.class);
    }
}

