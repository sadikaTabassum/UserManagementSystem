import java.util.Scanner;

public class AdminRoleHandler extends AbstractRoleHandler {

    public AdminRoleHandler(IUserService userService) {
        super(userService);
    }



    @Override
    public void handleRoleActions(User user, Scanner scanner) {

        System.out.println("As an Admin user, you have full access.");
        boolean exit = false;

        while (!exit) {

            System.out.println("\nAdmin Options:");
            System.out.println("1. Add a new user");
            System.out.println("2. Modify user data");
            System.out.println("3. Exit to main menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    userService.createAccount(scanner);
                    break;
                case 2:
                    modifyUserData(scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }



    private void modifyUserData(Scanner scanner) {


        System.out.print("Enter username to modify: ");
        String username = scanner.nextLine();
        User user = userService.findUserByUsername(username);
        if (user != null) {
            System.out.print("Enter new email: ");
            user.setEmail(scanner.nextLine());
            System.out.print("Enter new password: ");
            user.setPassword(scanner.nextLine());
            System.out.println("User data updated successfully.");
            userService.saveAllUsersToStorage();
        } else {
            System.out.println("User not found.");
        }
    }
}
