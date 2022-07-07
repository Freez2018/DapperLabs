package com.example.demo.data;

public class User {
    private int _id;
    private String _email;
    private String _firstName;
    private String _lastName;
    private String _password;

    private User(int id,
                 String email,
                 String firstName,
                 String lastName,
                 String password){
        _id = id;
        _email = email;
        _firstName = firstName;
        _lastName = lastName;
    }

    public String getEmail() {
        return _email;
    }

    public String getFirstName() {
        return _firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public int getId() {
        return _id;
    }

    public String getPassword(){
        return _password;
    }

    public static class Builder {
        private int b_id;
        private String b_email;
        private String b_firstName;
        private String b_lastName;
        private String b_password;

        public void withId(int id) {b_id = id;}
        public void withEmail(String email){
            b_email = email;
        }
        public void withFirstName(String firstName){
            b_firstName = firstName;
        }
        public void withLastName(String lastName){
            b_lastName = lastName;
        }
        public void withPassword(String password){b_password = password;}
        public User build(){
            return new User(b_id, b_email, b_firstName, b_lastName, b_password);
        }
    }
}
