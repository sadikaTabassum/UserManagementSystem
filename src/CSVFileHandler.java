import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVFileHandler implements IFileHandler {

    private static final String USER_CSV = "User.csv";
    private static final String ADMIN_CSV = "Admin.csv";

    @Override
    public List<User> loadUsers() {

        List<User> users = new ArrayList<>();
        loadUsersFromFile(users, USER_CSV, false);
        loadUsersFromFile(users, ADMIN_CSV, true);
        return users;
    }

    private void loadUsersFromFile(List<User> users, String fileName, boolean isAdmin) {


        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if ((isAdmin && values.length == 4) || (!isAdmin && values.length == 5)) {
                    users.add(new User(values[0], values[1], values[2], values[3], isAdmin ? "Admin" : values[4]));
                }
            }
        }
        catch (IOException e) {

            System.out.println("Error reading file: " + e.getMessage());
        }
    }



    @Override
    public void saveUser(User user) {

        String fileName = "Admin".equalsIgnoreCase(user.getUserType()) ? ADMIN_CSV : USER_CSV;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {

            bw.write(user.getUserID() + "," + user.getUsername() + "," + user.getEmail() + "," +
                    user.getPassword() + (fileName.equals(USER_CSV) ? "," + user.getUserType() : ""));
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }



    @Override
    public void saveAllUsers(List<User> users) {


        try (BufferedWriter userWriter = new BufferedWriter(new FileWriter(USER_CSV));
             BufferedWriter adminWriter = new BufferedWriter(new FileWriter(ADMIN_CSV))) {
            for (User user : users) {
                BufferedWriter writer = "Admin".equalsIgnoreCase(user.getUserType()) ? adminWriter : userWriter;
                writer.write(user.getUserID() + "," + user.getUsername() + "," +
                        user.getEmail() + "," + user.getPassword() +
                        ("Admin".equalsIgnoreCase(user.getUserType()) ? "" : "," + user.getUserType()));
                writer.newLine();
            }
        }

        catch (IOException e) {

            System.out.println("Error writing to files: " + e.getMessage());
        }
    }
}
