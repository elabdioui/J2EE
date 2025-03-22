package com.example.tp1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// définit l'URL pour accéder à cette servlet
@WebServlet("/CalculDeMonImc")
public class CalculDeMonImc extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // méééthode  GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    // méthode POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    // méthode  GET et POST
    private void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Récupération des paramètre
        String poidsStr = request.getParameter("poids");
        String tailleStr = request.getParameter("taille");
                response.setContentType("text/html");
            PrintWriter out = response.getWriter();
        
        // Début du document HTML
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Calcul IMC</title>");
        out.println("</head>");
        out.println("<body>");
        
        try {
            double poids = Double.parseDouble(poidsStr);
            double taille = Double.parseDouble(tailleStr);
            
            Imc imc = new Imc(taille, poids);
            double resultatImc = imc.calcul();
            
            out.println("<h1>Résultat du calcul d'IMC</h1>");
            out.println("<p>Poids: " + poids + " kg</p>");
            out.println("<p>Taille: " + taille + " m</p>");
            out.println("<p>Votre IMC est: " + String.format("%.2f", resultatImc) + "</p>");
        } catch (NumberFormatException e) {
            out.println("<p>Erreur: Veuillez entrer des valeurs numériques valides.</p>");
        }
        
        // Fin du document HTML
        out.println("</body>");
        out.println("</html>");
    }
}
