package backend.user.impl;

import backend.common.Result;
import backend.user.IUserController;
import backend.user.IUserService;
import backend.user.entity.User;

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
    public Result updateUser(String username) {
        return null;
    }
}
