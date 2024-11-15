import java.util.Scanner;

public class PowerRoleHandler extends AbstractRoleHandler {

    public PowerRoleHandler(IUserService userService) {

        super(userService);
    }

    @Override
    public void handleRoleActions(User user, Scanner scanner) {

        System.out.println("As a Power user, you can view and add user data.");
        userService.getUsers().forEach(System.out::println);
        System.out.print("Would you like to add a new user? (yes/no): ");
        String response = scanner.nextLine();
        if ("yes".equalsIgnoreCase(response)) {
            userService.createAccount(scanner);
        }
    }
}
