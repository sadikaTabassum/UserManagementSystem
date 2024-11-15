import java.util.List;
import java.util.Scanner;



public class UserService implements IUserService {
    private List<User> users;
    private IFileHandler fileHandler;

    public UserService(IFileHandler fileHandler) {
        this.fileHandler = fileHandler;
        this.users = fileHandler.loadUsers();
    }



    @Override
    public void createAccount(Scanner scanner) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (Regular/Power/Admin): ");
        String role = scanner.nextLine();

        if (isEmailTaken(email)) {
            System.out.println("Email is already taken. Please try again.");
            return;
        }
        if (isUsernameTaken(username)) {
            System.out.println("Username is taken. Suggestions:");
            suggestUsernameAlternatives(username);
            return;
        }
        if (!isValidPassword(password)) {
            System.out.println("Password must contain an uppercase letter, a lowercase letter, a special character, and a number.");
            return;
        }

        String userID = generateUniqueID();
        User newUser = new User(userID, username, email, password, role);
        users.add(newUser);
        fileHandler.saveUser(newUser);
        System.out.println("Account created successfully!");
    }



    @Override
    public void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = authenticateUser(username, password);
        if (user != null) {
            System.out.println("Login successful! Welcome " + user.getUsername() + " (" + user.getUserType() + ")");
            getRoleHandler(user).handleRoleActions(user, scanner);
        } else {
            System.out.println("Invalid username or password.");
        }
    }





    private IRoleHandler getRoleHandler(User user) {
        switch (user.getUserType().toLowerCase()) {
            case "admin":
                return new AdminRoleHandler(this);
            case "power":
                return new PowerRoleHandler(this);
            default:
                return new RegularRoleHandler(this);
        }
    }

    private boolean isEmailTaken(String email) {
        return users.stream().anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }



    private boolean isUsernameTaken(String username) {
        return users.stream().anyMatch(user -> user.getUsername().equalsIgnoreCase(username));
    }


    private void suggestUsernameAlternatives(String username) {
        System.out.println(username + "01");
        System.out.println(username + "99");
        System.out.println(username + "123");
    }



    private boolean isValidPassword(String password) {
        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isDigit(c)) hasDigit = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasUpper && hasLower && hasDigit && hasSpecial;
    }



    private String generateUniqueID() {

        return "UID" + (users.size() + 1);
    }




    private User authenticateUser(String username, String password) {

        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }



    @Override
    public User findUserByUsername(String username) {

        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }


    @Override
    public List<User> getUsers() {

        return users;
    }



    @Override
    public void saveAllUsersToStorage() {

        fileHandler.saveAllUsers(users);
    }
}
