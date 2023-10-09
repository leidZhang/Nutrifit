package api;

import api.common.Result;
import backend.entity.User;

public interface IUser {
    Result save(User user);
    Result updateUser(String username);
    Result getUser(String username);
}
