package eco.solutions.controller.user;

import eco.solutions.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements IUserController{

    private IUserService userService;

    @Override
    public void getAll() {
        userService.getAll();
    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
