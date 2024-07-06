package inverte.ya.api.service.impl;

import inverte.ya.api.dto.AuthDTO;

import inverte.ya.api.model.User;
import inverte.ya.api.repo.IGenericRepo;
import inverte.ya.api.repo.IUserRepo;
import inverte.ya.api.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDImpl<User, Integer> implements IUserService
{

    private final IUserRepo repo;

    @Override
    protected IGenericRepo<User, Integer> getRepo() {
        return repo;
    }

    @Override
    public User findByUsername(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public User login(AuthDTO dto) {
        return repo.findByUsernameAndPassword(dto.getEmail(), dto.getPassword());
    }

    @Override
    public List<User> saveAll(List<User> t) throws Exception {
        repo.deleteAll();
        return repo.saveAll(t);
    }
}

