package com.example.tp1;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;

// Initially, this will map to /PremiereServlet
@WebServlet("/PremiereServlet")
public class PremiereServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>UN EXEMPLE POUR WALID</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>C'est ma première servlet !</p>");
        out.println("</body>");
        out.println("</html>");
    }
}