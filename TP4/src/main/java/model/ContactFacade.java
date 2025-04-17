package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContactFacade {
	
	 private static final String JDBC_URL = "jdbc:mysql://localhost:3306/j2ee_tp4";
	    private static final String JDBC_USER = "root";
	    private static final String JDBC_PASSWORD = "0000"; // À modifier selon votre configuration
	    
	    // Méthode pour obtenir une connexion à la base de données
	    private Connection getConnection() throws SQLException {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	        } catch (ClassNotFoundException e) {
	            throw new SQLException("Pilote MySQL introuvable", e);
	        }
	    }
		
	    public List<Contact> findAll() {
	        List<Contact> list = new ArrayList<>();
	        String sql = "SELECT * FROM Contact";
	        try (Connection conn = getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Contact c = new Contact();
	                c.setIdContact(rs.getInt("ID_CONTACT"));
	                c.setFirstname(rs.getString("FIRSTNAME"));
	                c.setLastname(rs.getString("LASTNAME"));
	                c.setEmail(rs.getString("EMAIL"));
	                c.setPhone(rs.getString("PHONE"));
	                c.setAddress(rs.getString("ADDRESS"));
	                list.add(c);
	            }
	        } catch (SQLException e) { e.printStackTrace(); }
	        return list;
	    }

	    public void createContact(Contact c) {
	        String sql = "INSERT INTO Contact (FIRSTNAME, LASTNAME, EMAIL, PHONE, ADDRESS) VALUES (?, ?, ?, ?, ?)";
	        try (Connection conn = getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, c.getFirstname());
	            ps.setString(2, c.getLastname());
	            ps.setString(3, c.getEmail());
	            ps.setString(4, c.getPhone());
	            ps.setString(5, c.getAddress());
	            ps.executeUpdate();
	        } catch (SQLException e) { 
	            e.printStackTrace(); 
	            // Vous pouvez éventuellement logger plus d'informations ici
	            System.err.println("Erreur SQL: " + e.getMessage());
	        }
	    }

	    public void deleteContact(int id) {
	        String sql = "DELETE FROM Contact WHERE ID_CONTACT = ?";
	        try (Connection conn = getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setInt(1, id);
	            ps.executeUpdate();
	        } catch (SQLException e) { e.printStackTrace(); }
	    }

	    public void updateContact(Contact c) {
	        String sql = "UPDATE Contact SET FIRSTNAME=?,LASTNAME=?,EMAIL=?,PHONE=?,ADDRESS=? WHERE ID_CONTACT=?";
	        try (Connection conn = getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, c.getFirstname());
	            ps.setString(2, c.getLastname());
	            ps.setString(3, c.getEmail());
	            ps.setString(4, c.getPhone());
	            ps.setString(5, c.getAddress());
	            ps.setInt(6, c.getIdContact());
	            ps.executeUpdate();
	        } catch (SQLException e) { e.printStackTrace(); }
	    }

	    public Contact findById(int id) {
	        Contact c = null;
	        String sql = "SELECT * FROM Contact WHERE ID_CONTACT = ?";
	        try (Connection conn = getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                c = new Contact();
	                c.setIdContact(id);
	                c.setFirstname(rs.getString("FIRSTNAME"));
	                c.setLastname(rs.getString("LASTNAME"));
	                c.setEmail(rs.getString("EMAIL"));
	                c.setPhone(rs.getString("PHONE"));
	                c.setAddress(rs.getString("ADDRESS"));
	            }
	        } catch (SQLException e) { e.printStackTrace(); }
	        return c;
	    }

	    public List<Contact> searchByName(String term) {
	        List<Contact> list = new ArrayList<>();
	        String sql = "SELECT * FROM Contact WHERE FIRSTNAME LIKE ? OR LASTNAME LIKE ?";
	        try (Connection conn = getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            String pattern = "%" + term + "%";
	            ps.setString(1, pattern);
	            ps.setString(2, pattern);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                Contact c = new Contact();
	                c.setIdContact(rs.getInt("ID_CONTACT"));
	                c.setFirstname(rs.getString("FIRSTNAME"));
	                c.setLastname(rs.getString("LASTNAME"));
	                c.setEmail(rs.getString("EMAIL"));
	                c.setPhone(rs.getString("PHONE"));
	                c.setAddress(rs.getString("ADDRESS"));
	                list.add(c);
	            }
	        } catch (SQLException e) { e.printStackTrace(); }
	        return list;
	    }
}



