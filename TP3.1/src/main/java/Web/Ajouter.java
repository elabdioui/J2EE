package Web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import DAO.EtudiantDAO; 
import domaine.Etudiant; 

@WebServlet("/Ajouter")
public class Ajouter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EtudiantDAO dao = new EtudiantDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String code = req.getParameter("code");
		String nom = req.getParameter("nom");
		String ville = req.getParameter("ville");
		int age = Integer.parseInt(req.getParameter("age"));
		
		Etudiant e = new Etudiant(code,nom,ville,age);
		
		dao.add(e);
		List<Etudiant> l= dao.findAll();
		
		req.setAttribute("all", l);
		
		req.getRequestDispatcher("etudiant.jsp").forward(req, resp);
		
		
		
	}
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException { 
			doGet(request, response); 
			}
	
	
	
	
       
    
}


