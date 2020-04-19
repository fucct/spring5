package controller;

public class LoginCommand {

    private String email;
    private String password;
    private boolean rememberEmail;

    public String getEmail() {

        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public boolean isRememberEmail() {
        return rememberEmail;
    }

    public void setRememberEmail(final boolean rememberEmail) {
        this.rememberEmail = rememberEmail;
    }
}
