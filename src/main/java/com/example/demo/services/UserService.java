package com.example.demo.services;

import com.example.demo.data.User;
import com.example.demo.data.UserResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("_userService")
@Scope("singleton")
public class UserService {
    public List<UserResponse> getAllUsers() {
        Connection c = null;
        Statement stmt = null;
        ArrayList<User> users = new ArrayList<>();
        try {
            c = getConnection();

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.user;");

            while (rs.next()) {
                User.Builder builder = new User.Builder();
                builder.withEmail(rs.getString("email"));
                builder.withFirstName(rs.getString("firstname"));
                builder.withLastName(rs.getString("lastname"));
                users.add(builder.build());
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("users fetched successfully");
        return Collections.unmodifiableList(UserTransformer.transformUserToUserResponse(users));
    }

    public boolean checkLogIn(String email, String password) {
        Connection c = null;
        Statement stmt = null;
        ArrayList<User> users = new ArrayList<>();
        try {
            c = getConnection();
            stmt = c.createStatement();
            String sql = "SELECT * FROM public.user " +
                    "WHERE email = '" + email + "' " +
                    "and password = '" + password + "';";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User.Builder builder = new User.Builder();
                builder.withEmail(rs.getString("email"));
                users.add(builder.build());
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) { //thwo exeption?
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("users fetched successfully");
        return checkLoginResult(users);
    }

    public void updateUserFirstname(String email, String firstname) {
        Connection c = null;
        Statement stmt = null;
        ArrayList<User> users = new ArrayList<>();
        try {
            c = getConnection();
            stmt = c.createStatement();
            String sql = "UPDATE public.user " +
                    "SET " +
                    "FIRSTNAME = '" + firstname + "' " +
                    "WHERE EMAIL = '" + email + "';";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("first name updated");
    }

    public void updateUserLastname(String email, String lastname) {
        Connection c = null;
        Statement stmt = null;
        ArrayList<User> users = new ArrayList<>();
        try {
            c = getConnection();
            stmt = c.createStatement();
            String sql = "UPDATE public.user " +
                    "SET " +
                    "LASTNAME = '" + lastname + "'" +
                    "WHERE EMAIL = '" + email + "';";
            stmt.executeQuery(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("lastname name updated");
    }

    public void createUser(String firstname,
                           String lastname,
                           String email,
                           String password) {
        Connection c = null;
        Statement stmt = null;
        try {
            c = getConnection();
            stmt = c.createStatement();
            String sql = "INSERT INTO public.user (FIRSTNAME,LASTNAME,EMAIL,PASSWORD) " +
                    "VALUES ('" +
                    firstname + "'" +
                    ", '" + lastname + "'" +
                    ", '" + email + "'" +
                    ", '" + password + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("user added successfully");
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection c;
        Class.forName("org.postgresql.Driver");
        c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/DapperLabs",//create a db first
                        "postgres", "int");

        System.out.println("Opened database successfully");
        return c;
    }
    private boolean checkLoginResult(List<User> users) {
        return users.size() == 1;
    }
}
