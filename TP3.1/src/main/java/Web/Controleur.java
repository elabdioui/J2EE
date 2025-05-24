package Web;

import DAO.EtudiantDaoMySQL;
import DAO.EtudiantDAO; 
import DAO.IEtudiantDAO;
import DAO.EtudiantDaoMySQL;
import domaine.Etudiant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



@WebServlet("/Controleur")
public class Controleur extends HttpServlet {
    private static final long serialVersionUID = 1L; 
    IEtudiantDAO dao = new EtudiantDaoMySQL();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        
        System.out.println("=== CONTROLEUR doGet ===");
        String action = request.getParameter("action"); 
        System.out.println("Action: " + action);
        
        if ("supprimer".equals(action)) { 
            String code = request.getParameter("code"); 
            System.out.println("Suppression du code: " + code);
            dao.delete(code); 
            
            List<Etudiant> listeEtudiants = dao.findAll(); 
            request.setAttribute("all", listeEtudiants); 
            request.getRequestDispatcher("etudiants1.jsp").forward(request, response); 
            
        } else if ("modifier".equals(action)) { 
            String code = request.getParameter("code"); 
            System.out.println("Modification du code: " + code);
            
            Etudiant e = dao.findByCode(code); 
            request.setAttribute("etud", e); 
            
            List<Etudiant> listeEtudiants = dao.findAll(); 
            request.setAttribute("all", listeEtudiants); 
            
            request.getRequestDispatcher("etudiants1.jsp").forward(request, response); 
        } else {
            // Cas par défaut : afficher tous les étudiants
            List<Etudiant> listeEtudiants = dao.findAll(); 
            request.setAttribute("all", listeEtudiants); 
            request.getRequestDispatcher("etudiants1.jsp").forward(request, response);
        }
    } 
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        
        System.out.println("=== CONTROLEUR doPost ===");
        String code = request.getParameter("code"); 
        String nom = request.getParameter("nom"); 
        String ville = request.getParameter("ville"); 
        String ageStr = request.getParameter("age");
        
        System.out.println("Paramètres reçus:");
        System.out.println("  Code: " + code);
        System.out.println("  Nom: " + nom);
        System.out.println("  Ville: " + ville);
        System.out.println("  Age: " + ageStr);
        
        try {
            int age = Integer.parseInt(ageStr); 
            Etudiant e = new Etudiant(code, nom, ville, age); 
            
            dao.add(e); 
            System.out.println("Étudiant ajouté avec succès");
            
            List<Etudiant> listeEtudiants = dao.findAll(); 
            System.out.println("Nombre d'étudiants récupérés: " + listeEtudiants.size());
            request.setAttribute("all", listeEtudiants); 
            
            request.getRequestDispatcher("etudiants1.jsp").forward(request, response); 
            
        } catch (NumberFormatException ex) {
            System.err.println("Erreur: Age invalide - " + ageStr);
            request.setAttribute("error", "L'âge doit être un nombre valide");
            request.getRequestDispatcher("etudiants1.jsp").forward(request, response);
        }
    } 
}