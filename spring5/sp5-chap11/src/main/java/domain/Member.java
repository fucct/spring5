package domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import exception.WrongIdPasswordException;

import java.time.LocalDateTime;

public class Member {

    private  String email;
    private  String name;
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime registerDateTime;
    private Long id;
    @JsonIgnore
    private String password;
    private String errorMessage;


    public Member(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Member(final String email, final String password, final String name,
                  final LocalDateTime registerDateTime) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.registerDateTime = registerDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getRegisterDateTime() {
        return registerDateTime;
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (!password.equals(oldPassword)) {
            throw new WrongIdPasswordException();
        }
        this.password = newPassword;
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
