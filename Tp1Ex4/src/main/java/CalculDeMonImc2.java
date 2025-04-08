

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class CalculDeMonImc2
 */
@WebServlet("/CalculDeMonImc2")
public class CalculDeMonImc2 extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String poidsParam = request.getParameter("poids");
        String tailleParam = request.getParameter("taille");

        if (poidsParam != null && tailleParam != null) {
            try {
                double poids= Double.parseDouble(poidsParam);
                double taille = Double.parseDouble(tailleParam);
                double imc = poids / (taille * taille);

                // Sauvegarder les données dans la session
                HttpSession session = request.getSession();
                session.setAttribute("poids", poidsParam);
                session.setAttribute("taille", tailleParam);
                session.setAttribute("imc", imc);

                response.setContentType("text/html");
                response.getWriter().println("<html><body>");
                response.getWriter().println("<h1>Votre IMC est : " + imc + "</h1>");
                response.getWriter().println("</body></html>");
            } catch (NumberFormatException e) {
                response.setContentType("text/html");
                response.getWriter().println("<html><body>");
                response.getWriter().println("<h1>Erreur : Veuillez entrer des valeurs numériques valides pour le poids et la taille.</h1>");
                response.getWriter().println("</body></html>");
            }
        } else {
            response.setContentType("text/html");
            response.getWriter().println("<html><body>");
            response.getWriter().println("<h1>Erreur : Les paramètres poids et taille sont requis.</h1>");
            response.getWriter().println("</body></html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
