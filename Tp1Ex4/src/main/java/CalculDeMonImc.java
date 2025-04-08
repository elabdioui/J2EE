

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CalculDeMonImc
 */
@WebServlet("/CalculDeMonImc")
public class CalculDeMonImc extends HttpServlet {
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

        String poidsParam = request.getParameter("poids");
        String tailleParam = request.getParameter("taille");

        
                double poids = Double.parseDouble(poidsParam);
                double taille = Double.parseDouble(tailleParam);
                double imc = poids / (taille * taille);

                // Écrire les cookies
                Cookie poidsCookie = new Cookie("poids", poidsParam);
                Cookie tailleCookie = new Cookie("taille", tailleParam);
                Cookie imcCookie = new Cookie("imc", String.valueOf(imc));
                response.addCookie(poidsCookie);
                response.addCookie(tailleCookie);
                response.addCookie(imcCookie);

                response.setContentType("text/html");
                response.getWriter().println("<html><body>");
                response.getWriter().println("<h1>Votre IMC est : " + imc + "</h1>");
                response.getWriter().println("</body></html>");
           
       
		
	}

}
