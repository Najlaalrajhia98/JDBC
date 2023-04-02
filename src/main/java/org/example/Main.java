package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try {
            // Establish a connection to the database by calling the getConnection() method of the DriverManager class.
            // Here, we're passing the database URL, username, and password as arguments to the getConnection() method.
            Connection connection =
                    DriverManager.getConnection
                            ("jdbc:mysql://localhost:3306/najla",
                                    "root",
                                    System.getenv("DB_USER_PASS"));

            // Create a Statement object that allows us to execute SQL queries against the database.
            Statement statement = connection.createStatement();

            // passing the SQL query as an argument in execute() method of the Statement object
            // selecting all columns from the STUDENT table where id matches the first argument passed in the main method.
            statement.execute("SELECT * FROM STUDENT WHERE id =" + args[0]);
            statement.execute("select * from student");

            // Retrieve the ResultSet object returned by the last executed query by calling
            // the getResultSet() method of the Statement object.
            ResultSet resultSet = statement.getResultSet();

            // Process the result set by iterating through each row using the next() method of the ResultSet object.
            // retrieving the values of the id, name, and email columns for each row and printing them to the console.
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println("ID:" + id + " ,NAME:" + name + " , Email:" + email);
            }

            //Update record
            int id2Update = 4;
            String newName = "MANAR";
            String newEmail = "MANAR@OUTLOOK.COM";
            statement.executeUpdate("UPDATE STUDENT SET NAME ='" + newName + "'" + ",Email = '" + newEmail + "' where id =" + id2Update);
            System.out.println("updated student with id :" + id2Update + " with the new name =" + newName + " and new Email:" + newEmail);

            // Create Record
            String name = "nada";
            int id = 7;
            String Email = "Nada@outlook.com";
            statement.execute("INSERT INTO STUDENT VALUES (" + id + ",'" + name + "'," + "'" + Email + "')");
            System.out.println("inserted new student");

            // Delete Record
            int idToDelete = 7;
            statement.execute("DELETE FROM STUDENT WHERE ID= " + idToDelete);
            System.out.println("Deleted the record ");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}