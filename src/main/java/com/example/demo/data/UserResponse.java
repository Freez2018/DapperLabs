package com.example.demo.data;

import java.util.Objects;

public class UserResponse {
    private String firstname;
    private String lastname;
    private String email;

    public UserResponse(String firstname, String lastname, String email) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return email.equals(that.email) && firstname.equals(that.firstname) && lastname.equals(that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstname, lastname);
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
