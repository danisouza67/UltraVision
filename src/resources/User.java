package resources;

import interfaces.UserInterface;

public class User implements UserInterface {

    private String usr;
    private String pass;

    private String loginID;
    private String loginFname;
    private String loginLname;
    private String loginUsername;
    private String loginAdmin;

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getLoginFname() {
        return loginFname;
    }

    public void setLoginFname(String loginFname) {
        this.loginFname = loginFname;
    }

    public String getLoginLname() {
        return loginLname;
    }

    public void setLoginLname(String loginLname) {
        this.loginLname = loginLname;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getLoginAdmin() {
        return loginAdmin;
    }

    public void setLoginAdmin(String loginAdmin) {
        this.loginAdmin = loginAdmin;
    }

    public User(String usr, String pss) {

        this.usr = usr;
        this.pass = pss;
    }

    public User(String loginID, String loginFname, String loginLname, String loginUsername, String loginAdmin) {

        this.loginID = loginID;
        this.loginFname = loginFname;
        this.loginLname = loginLname;
        this.loginUsername = loginUsername;
        this.loginAdmin = loginAdmin;

    }

}
