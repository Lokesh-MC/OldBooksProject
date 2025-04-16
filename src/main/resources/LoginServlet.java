package com.oldbooks;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("user", rs.getString("name"));
                response.sendRedirect("welcome.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch(Exception e){
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
