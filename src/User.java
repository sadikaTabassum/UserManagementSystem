public class User {

    private String userID;
    private String username;
    private String email;
    private String password;
    private String userType;

    public User(String userID, String username, String email, String password, String userType) {

        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public String getUserType() {
        return userType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserID: " + userID + ", Username: " + username + ", Email: " + email + ", Role: " + userType;
    }
}
