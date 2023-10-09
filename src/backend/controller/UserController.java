package backend.controller;

import api.IUser;
import api.common.Result;
import backend.entity.User;
import backend.service.IUserService;
import backend.service.impl.UserService;

public class UserController implements IUser {
    private IUserService service = new UserService();

    @Override
    public Result save(User user) {
        try {
            service.save(user);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @Override
    public Result getUser(String username) {
        try {
            User user = service.getByUsername(username);
            return Result.success(user);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @Override
    public Result updateUser(String username) {
        return null;
    }
}
