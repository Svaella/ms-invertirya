package inverte.ya.api.repo;

import inverte.ya.api.model.User;

public interface IUserRepo extends IGenericRepo<User, Integer>
{
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
}
