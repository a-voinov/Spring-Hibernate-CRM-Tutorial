package com.tutorial.testdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/TestDb")
public class TestDbServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = "springstudent";
        String password = "springstudent";
        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
        String driver = "com.mysql.cj.jdbc.Driver";

        try {
            PrintWriter printWriter = resp.getWriter();
            printWriter.println("Connecting to database " + jdbcUrl);
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
            printWriter.println("Connection successful!");
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
