package Testing.Book;

public class Registerlist {
    String fullname;
    String schoolid;
    String password;
    String firstname;
    String lastname;

    public Registerlist(String fullname, String schoolid, String password, String firstname, String lastname) {
        this.fullname = fullname;
        this.schoolid = schoolid;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public String getFullname() {
        return fullname;
    }
    public String getSchoolid() {
        return schoolid;
    }
    public String getPassword() {
        return password;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
}