package main.backend.user;

import main.backend.common.Result;
import main.backend.user.entity.User;

public interface IUserController {
    Result save(User user);
    Result updateUser(User user);
    Result getUser(String username);
}
