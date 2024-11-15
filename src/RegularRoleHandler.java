import java.util.Scanner;

public class RegularRoleHandler extends AbstractRoleHandler {

    public RegularRoleHandler(IUserService userService) {

        super(userService);
    }

    @Override
    public void handleRoleActions(User user, Scanner scanner) {

        System.out.println("As a Regular user, you can view user data.");
        userService.getUsers().forEach(System.out::println);
    }
}
