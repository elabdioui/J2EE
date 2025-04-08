

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class TableauDeBord
 */
@WebServlet("/TableauDeBord")
public class TableauDeBord extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

        Cookie[] cookies = request.getCookies();
        String poids = null;
        String taille = null;
        String imc = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                switch (cookie.getName()) {
                    case "poids":
                        poids = cookie.getValue();
                        break;
                    case "taille":
                        taille = cookie.getValue();
                        break;
                    case "imc":
                        imc = cookie.getValue();
                        break;
                }
            }
        }

        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>Tableau de Bord</h1>");
        response.getWriter().println("<p>Poids : " + poids + "</p>");
        response.getWriter().println("<p>Taille : " + taille + "</p>");
        response.getWriter().println("<p>IMC : " + imc + "</p>");
        response.getWriter().println("</body></html>");
    }
}

