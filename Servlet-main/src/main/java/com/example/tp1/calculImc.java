package com.example.tp1;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalculDeMonImc")
public class calculImc extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Get parameters
        String poidsStr = request.getParameter("poids");
        String tailleStr = request.getParameter("taille");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Calcul IMC</title>");
        out.println("</head>");
        out.println("<body>");
        
        try {
            double poids = Double.parseDouble(poidsStr);
            double taille = Double.parseDouble(tailleStr);
            double imc = poids / (taille * taille);
            
            out.println("<h1>Résultat du calcul d'IMC</h1>");
            out.println("<p>Poids: " + poids + " kg</p>");
            out.println("<p>Taille: " + taille + " m</p>");
            out.println("<p>Votre IMC est: " + String.format("%.2f", imc) + "</p>");
        } catch (NumberFormatException e) {
            out.println("<p>Erreur: Veuillez entrer des valeurs numériques valides.</p>");
        }
        
        out.println("</body>");
        out.println("</html>");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}