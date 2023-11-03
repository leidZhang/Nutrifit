package main.backend.user.impl;

import main.backend.common.Result;
import main.backend.user.IUserController;
import main.backend.user.IUserService;
import main.backend.user.entity.User;

public class UserController implements IUserController {
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
    public Result updateUser(User user) {
        try {
            service.updateUser(user);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
