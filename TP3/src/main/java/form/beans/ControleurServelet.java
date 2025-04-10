package form.beans;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mod.sco.Scolarite;

import java.io.IOException;

/**
 * Servlet implementation class ControleurServelet
 */
@WebServlet("/ControleurServelet")
public class ControleurServelet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoteForm nf=new NoteForm(); 
		Scolarite sco=new Scolarite(); 
		nf.setNumIns(request.getParameter("numIns")); 
		nf.setLesNotes(sco.getNotes(nf.getNumIns())); 
		HttpSession session=request.getSession(); 
		session.setAttribute("nf",nf); 
		response.sendRedirect("Notes.jsp"); 
	}

}
