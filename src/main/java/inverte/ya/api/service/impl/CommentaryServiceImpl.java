package inverte.ya.api.service.impl;

import inverte.ya.api.model.Commentary;
import inverte.ya.api.repo.ICommentaryRepo;
import inverte.ya.api.repo.IGenericRepo;
import inverte.ya.api.service.ICommentaryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentaryServiceImpl extends CRUDImpl<Commentary, Integer> implements ICommentaryService
{
    private final ICommentaryRepo repo;
    @Override
    protected IGenericRepo<Commentary, Integer> getRepo() {
        return repo;
    }

    @Override
    public Commentary save(Commentary commentary) {
        commentary.setCreatedAt(LocalDateTime.now());
        return super.save(commentary);
    }

    @Transactional
    @Override
    public void delete(Integer integer) {
        repo.deleteById(integer);
    }
}

