import java.util.List;
import java.util.Scanner;

public interface IUserService {
    void createAccount(Scanner scanner);
    void login(Scanner scanner);
    User findUserByUsername(String username);
    List<User> getUsers();
    void saveAllUsersToStorage();
}
