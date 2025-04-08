

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class TableauDeBord2
 */
@WebServlet("/TableauDeBord2")
public class TableauDeBord2 extends HttpServlet {
   

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String poids = (String) session.getAttribute("poids");
        String taille = (String) session.getAttribute("taille");
        Double imc = (Double) session.getAttribute("imc");

        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>Tableau de Bord</h1>");
        response.getWriter().println("<p>Poids : " + poids + "</p>");
        response.getWriter().println("<p>Taille : " + taille + "</p>");
        response.getWriter().println("<p>IMC : " + imc + "</p>");
        response.getWriter().println("</body></html>");
    }
}
