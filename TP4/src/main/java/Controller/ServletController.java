package Controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;



import model.Contact;
import model.ContactFacade;

@WebServlet("/ServletController")
public class ServletController extends HttpServlet {
    private ContactFacade contactFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        contactFacade = new ContactFacade();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("do_this");
        if (action == null) {
            List<Contact> list = contactFacade.findAll();
            request.setAttribute("listContacts", list);
            request.getRequestDispatcher("accueil.jsp").forward(request, response);

        } else switch (action) {
            case "create":
                if (request.getMethod().equalsIgnoreCase("POST") && request.getParameter("firstName") != null) {
                    Contact c = new Contact();
                    c.setFirstname(request.getParameter("firstName"));
                    c.setLastname(request.getParameter("lastName"));
                    c.setEmail(request.getParameter("email"));
                    c.setPhone(request.getParameter("phone"));
                    c.setAddress(request.getParameter("address"));
                    contactFacade.createContact(c);
                    response.sendRedirect("ServletController");
                } else {
                    response.sendRedirect("addContact.jsp");
                }
                break;
            case "delete":
                String idDel = request.getParameter("contact_id");
                if (idDel != null) {
                    contactFacade.deleteContact(Integer.parseInt(idDel));
                    response.sendRedirect("ServletController");
                } else {
                    response.sendRedirect("removeContact.jsp");
                }
                break;
            case "update":
                if (request.getParameter("idContact") != null && request.getParameter("firstName") != null) {
                    Contact c = new Contact();
                    c.setIdContact(Integer.parseInt(request.getParameter("idContact")));
                    c.setFirstname(request.getParameter("firstName"));
                    c.setLastname(request.getParameter("lastName"));
                    c.setEmail(request.getParameter("email"));
                    c.setPhone(request.getParameter("phone"));
                    c.setAddress(request.getParameter("address"));
                    contactFacade.updateContact(c);
                    response.sendRedirect("ServletController");
                } else if (request.getParameter("id") != null) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Contact c = contactFacade.findById(id);
                    request.setAttribute("contact", c);
                    request.getRequestDispatcher("updateContact.jsp").forward(request, response);
                } else {
                    response.sendRedirect("ServletController");
                }
                break;
            case "search":
                String term = request.getParameter("term");
                if (term != null) {
                    List<Contact> results = contactFacade.searchByName(term);
                    request.setAttribute("listContacts", results);
                    request.getRequestDispatcher("accueil.jsp").forward(request, response);
                } else {
                    response.sendRedirect("searchContact.jsp");
                }
                break;
            default:
                response.sendRedirect("ServletController");
        }
    }
}