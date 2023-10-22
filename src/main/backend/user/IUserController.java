package main.backend.user;

import main.backend.common.Result;
import main.backend.user.entity.User;

public interface IUserController {
    Result save(User user);
    Result updateUser(String username);
    Result getUser(String username);
}
