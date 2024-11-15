import java.util.List;

public interface IFileHandler {

    List<User> loadUsers();

    void saveUser(User user);

    void saveAllUsers(List<User> users);
}
