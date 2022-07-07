package com.example.demo.database;
import java.sql.*;

public class PostgreSQLJDBC {
    public static void main(String args[]) throws SQLException {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/DapperLabs",//create a db first
                            "postgres", "int");

            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE public.user " +
                    "(ID SERIAL PRIMARY KEY      NOT NULL," +
                    " FIRSTNAME      CHAR(50)    NOT NULL, " +
                    " LASTNAME       CHAR(50)     NOT NULL, " +
                    " EMAIL          CHAR(50) NOT NULL, " +
                    " PASSWORD       CHAR(100) NOT NULL) " ;
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}
