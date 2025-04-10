

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class BandesDessinees
 */
@WebServlet("/BandesDessinees")
public class BandesDessinees extends HttpServlet {
	
	
	private ArrayList<String> listeBandesDessinees;
	
	@Override
	public void init() {
		listeBandesDessinees = new ArrayList<String>();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head><title>Liste de nos bandes dessinées préférées</title></head>");
		out.println("<body>");
		out.println("<h1>Les bandes dessinées</h1>");
		out.println("<h4>Liste de nos bandes dessinées préférées</h4>");
		out.println("<ul>");

		for (String bd : listeBandesDessinees) {
		    out.println("<li>" + bd + "</li>");
		}

		out.println("</ul>");
		out.println("<form method='POST' action='BandesDessinees'>");
		out.println("Entrez un nom de bande dessinée : <input type='text' name='bd'><br/><br/>");
		out.println("<input type='submit' name='action' value='Ajouter'>");
		out.println("<input type='submit' name='action' value='Retirer'>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		
		
		}


	@Override
	protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		
				String bd = request.getParameter("bd");
				String action = request.getParameter("action");
				
				if(bd != null) {
					
					if(action.equals("Ajouter")) listeBandesDessinees.add(bd);
					else if (action.equals("Retirer")) listeBandesDessinees.remove(bd);
				}
				
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				out.println("<html><head><title>Confirmation</title></head><body>");
				out.println("<h4>Bandes dessinées préférées</h4>");
				out.println("<ul>");
				for (String bdd : listeBandesDessinees) {
				    out.println("<li>" + bd + "</li>");
				}
				out.println("</ul>");

				out.println("<p>" + action + " de \"" + bd + "\" réussi.</p>");
				out.println("<a href='" + request.getRequestURI() + "'>Retour</a>");
				out.println("</body></html>");
				
	}
	
		

}
