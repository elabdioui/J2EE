

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class CalculDeMonImc
 */
@WebServlet("/CalculDeMonImc")
public class CalculDeMonImc extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String poids = request.getParameter("poidsF");
		String taille = request.getParameter("tailleF");
		
		double p = Double.parseDouble(poids);
		double t = Double.parseDouble(poids);
		
		double IMC = p/t*t;
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<html><body>");
        out.println("<h1>Votre IMC est : " + IMC + "</h1>");
        out.println("</body></html>");

        
        

		
		
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
