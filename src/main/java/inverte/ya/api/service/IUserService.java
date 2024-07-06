package inverte.ya.api.service;

import inverte.ya.api.dto.AuthDTO;
import inverte.ya.api.model.User;

import java.util.List;

public interface IUserService extends ICRUD<User, Integer>
{
    User findByUsername(String username);
    User login(AuthDTO dto);
    List<User> saveAll(List<User> t) throws Exception;
}
