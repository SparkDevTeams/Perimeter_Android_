package perimeter.sparkdev.com.perimeter;

public class LoginModel {

    private String displayName;
    private String email;
    private String firstName;
    private String lastName;
    private String idUser;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public LoginModel(String displayName, String email, String firstName, String lastName
    , String idUser)
    {
        this.displayName = displayName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idUser = idUser;
    }
}
