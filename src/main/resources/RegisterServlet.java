package com.oldbooks;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO users(name,email,password) VALUES (?,?,?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            int i = ps.executeUpdate();
            if(i > 0){
                response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch(Exception e){
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
