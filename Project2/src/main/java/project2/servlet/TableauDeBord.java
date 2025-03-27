package project2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TableauDeBord")
public class TableauDeBord extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String taille = null, poids = null, imc = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("taille")) {
                    taille = cookie.getValue();
                } else if (cookie.getName().equals("poids")) {
                    poids = cookie.getValue();
                } else if (cookie.getName().equals("imc")) {
                    imc = cookie.getValue();
                }
            }
        }

        if (taille != null && poids != null && imc != null) {
            request.setAttribute("taille", taille);
            request.setAttribute("poids", poids);
            request.setAttribute("imc", imc);
        } else {
            request.setAttribute("error", "Aucune donnée disponible.");
        }

        request.getRequestDispatcher("tableauDeBord.jsp").forward(request, response);
    }
}
