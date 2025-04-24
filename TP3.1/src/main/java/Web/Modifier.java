package Web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DAO.EtudiantDAO;
import domaine.Etudiant;


@WebServlet("/Modifier")
public class Modifier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	EtudiantDAO dao = new EtudiantDAO();
       
   
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String code=req.getParameter("code"); 
	     Etudiant e=dao.findByCode(code); 
	      req.setAttribute("etud", e); 
	      
	      req.getRequestDispatcher("etudiant.jsp").forward(req, 
	    		  resp);
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
