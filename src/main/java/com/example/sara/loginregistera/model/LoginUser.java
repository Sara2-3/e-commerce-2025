package com.example.sara.loginregistera.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginUser {
    @NotEmpty(message="Please enter your email")
    @Email(message="Email is in the improper t")
    private String email;

    @NotEmpty(message = "Please enter your password")
    @Size(min=2,max=255,message="Password must be 2 to 255 characters,inclusivity")
    private String password;

    public LoginUser() {}
    public LoginUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
