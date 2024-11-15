import java.util.Scanner;

public class UserManagementSystem {

    private static UserManagementSystem instance;
    private IUserService userService;


    private UserManagementSystem() {

        IFileHandler fileHandler = new CSVFileHandler();
        this.userService = new UserService(fileHandler);
    }



    public static UserManagementSystem getInstance() {

        if (instance == null) {
            instance = new UserManagementSystem();
        }
        return instance;
    }



    public static void main(String[] args) {


        UserManagementSystem system = UserManagementSystem.getInstance();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nUser Management System");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    system.userService.createAccount(scanner);
                    break;
                case 2:
                    system.userService.login(scanner);
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
