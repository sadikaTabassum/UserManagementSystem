import java.util.Scanner;

public abstract class AbstractRoleHandler implements IRoleHandler {

    protected IUserService userService;

    public AbstractRoleHandler(IUserService userService) {

        this.userService = userService;
    }
}
