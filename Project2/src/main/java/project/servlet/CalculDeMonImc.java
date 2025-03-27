package project.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.model.Imc;

@WebServlet("/CalculDeMonImc")
public class CalculDeMonImc extends HttpServlet {
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
            double resultatImc = imc.calcul();

            Cookie cookieTaille = new Cookie("taille", String.valueOf(taille));
            Cookie cookiePoids = new Cookie("poids", String.valueOf(poids));
            Cookie cookieImc = new Cookie("imc", String.valueOf(resultatImc));

            cookieTaille.setMaxAge(60 * 60);  
            cookiePoids.setMaxAge(60 * 60); 
            cookieImc.setMaxAge(60 * 60);   

            response.addCookie(cookieTaille);
            response.addCookie(cookiePoids);
            response.addCookie(cookieImc);

            request.setAttribute("resultatImc", resultatImc);

            request.getRequestDispatcher("resultat.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Les valeurs de taille et de poids doivent être des nombres valides.");
            request.getRequestDispatcher("index.html").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response); 
    }
}
