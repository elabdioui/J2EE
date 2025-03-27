package project3.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project.model.Imc;

import java.io.IOException;

@WebServlet("/CalculDeMonImc2")
public class CalculDeMonImc2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String tailleStr = request.getParameter("taille");
            String poidsStr = request.getParameter("poids");

            if (tailleStr == null || tailleStr.trim().isEmpty() || poidsStr == null || poidsStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Les valeurs de taille et de poids sont obligatoires.");
            }

            double taille = Double.parseDouble(tailleStr);
            double poids = Double.parseDouble(poidsStr);

            Imc imc = new Imc(taille, poids);
            HttpSession session = request.getSession();
            session.setAttribute("imc", imc);
            double resultatImc = imc.calcul();
            
            request.setAttribute("resultatImc", resultatImc);

            request.getRequestDispatcher("resultat.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("NewFile.html").forward(request, response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response); 
    }
}
