package project4.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project.model.Imc;

import java.io.IOException;

@WebServlet("/TableauDeBord2")
public class TableauDeBord2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); 
        
        if (session != null) {
            Imc imc = (Imc) session.getAttribute("imc");
            if (imc != null) {
                request.getRequestDispatcher("tableauDeBord2.jsp").forward(request, response);
                return;
            }
        }
        
        response.sendRedirect(request.getContextPath() + "/NewFile.html");
    }
}
