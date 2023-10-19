package backend.user;

import backend.common.Result;
import backend.user.entity.User;

public interface IUser {
    Result save(User user);
    Result updateUser(String username);
    Result getUser(String username);
}
