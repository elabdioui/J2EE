package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domaine.Etudiant;

public class EtudiantDaoMySQL implements IEtudiantDAO { 
    
    @Override 
    public void add(Etudiant e) { 
        System.out.println("=== AJOUT ETUDIANT ===");
        System.out.println("Code: " + e.getCode() + ", Nom: " + e.getNom());
        
        String req = "INSERT INTO etudiants VALUES(?, ?, ?, ?)"; 
        try { 
            Connection cnx = ConnectionFactory.getConnection();
            System.out.println("Connexion établie: " + (cnx != null));
            
            PreparedStatement ps = cnx.prepareStatement(req); 
            ps.setString(1, e.getCode()); 
            ps.setString(2, e.getNom()); 
            ps.setString(3, e.getVille()); 
            ps.setInt(4, e.getAge()); 
            
            int result = ps.executeUpdate();
            System.out.println("Lignes affectées: " + result);
            ps.close();
            cnx.close();
            
        } catch (Exception ex) { 
            System.err.println("ERREUR lors de l'ajout: " + ex.getMessage());
            ex.printStackTrace();
        } 
    } 
    
    @Override 
    public void delete(String code) { 
        String req = "DELETE FROM etudiants WHERE code=?"; 
        try { 
            Connection cnx = ConnectionFactory.getConnection(); 
            PreparedStatement ps = cnx.prepareStatement(req); 
            ps.setString(1, code); 
            ps.executeUpdate(); 
            ps.close();
            cnx.close();
        } catch (Exception e) { 
            System.err.println("Erreur delete: " + e.getMessage());
            e.printStackTrace();
        } 
    } 
    
    @Override 
    public List<Etudiant> findAll() { 
        String req = "SELECT * FROM etudiants"; 
        List<Etudiant> etudiantsList = new ArrayList<>(); 
        try { 
            Connection cnx = ConnectionFactory.getConnection(); 
            Statement st = cnx.createStatement(); 
            ResultSet rs = st.executeQuery(req); 
            
            while (rs.next()) { 
                Etudiant e = new Etudiant(); 
                e.setCode(rs.getString(1)); 
                e.setNom(rs.getString(2)); 
                e.setVille(rs.getString(3)); 
                e.setAge(rs.getInt(4)); 
                etudiantsList.add(e);
            } 
            rs.close();
            st.close();
            cnx.close();
            
        } catch (Exception ex) {
            System.err.println("Erreur findAll: " + ex.getMessage());
            ex.printStackTrace();
        }
        return etudiantsList; 
    } 
    
    @Override 
    public void update(Etudiant e) { 
        // ✅ CORRIGÉ : Requête SQL avec tous les champs
        String req = "UPDATE etudiants SET nom=?, ville=?, age=? WHERE code=?";  
        try { 
            Connection cnx = ConnectionFactory.getConnection(); 
            PreparedStatement ps = cnx.prepareStatement(req); 
            
            // ✅ CORRIGÉ : Ordre correct des paramètres
            ps.setString(1, e.getNom());    // nom
            ps.setString(2, e.getVille());  // ville  
            ps.setInt(3, e.getAge());       // age
            ps.setString(4, e.getCode());   // code (WHERE)
            
            ps.executeUpdate(); 
            ps.close();
            cnx.close();
            
        } catch (Exception ex) {
            System.err.println("Erreur update: " + ex.getMessage());
            ex.printStackTrace();
        } 
    } 
    
    @Override 
    public List<Etudiant> findByVille(String v) { 
        String req = "SELECT * FROM etudiants WHERE ville=?"; 
        List<Etudiant> etudiantsList = new ArrayList<>(); 
        try { 
            Connection cnx = ConnectionFactory.getConnection(); 
            PreparedStatement ps = cnx.prepareStatement(req); 
            ps.setString(1, v); 
            ResultSet rs = ps.executeQuery(); 
            
            while (rs.next()) { 
                Etudiant e = new Etudiant(); 
                e.setCode(rs.getString(1)); 
                e.setNom(rs.getString(2)); 
                e.setVille(rs.getString(3)); 
                e.setAge(rs.getInt(4)); 
                etudiantsList.add(e);
            } 
            rs.close();
            ps.close();
            cnx.close();
            
        } catch (Exception ex) {
            System.err.println("Erreur findByVille: " + ex.getMessage());
            ex.printStackTrace();
        } 
        return etudiantsList; 
    } 
    
    @Override 
    public Etudiant findByCode(String c) { 
        String req = "SELECT * FROM etudiants WHERE code=?"; 
        Etudiant e = null; 
        try { 
            Connection cnx = ConnectionFactory.getConnection(); 
            PreparedStatement ps = cnx.prepareStatement(req); 
            ps.setString(1, c); 
            ResultSet rs = ps.executeQuery(); 
            
            if (rs.next()) { 
                e = new Etudiant(); 
                e.setCode(rs.getString(1)); 
                e.setNom(rs.getString(2)); 
                e.setVille(rs.getString(3)); 
                e.setAge(rs.getInt(4)); 
            } 
            rs.close();
            ps.close();
            cnx.close();
            
        } catch (Exception ex) {
            System.err.println("Erreur findByCode: " + ex.getMessage());
            ex.printStackTrace();
        } 
        return e; 
    } 
}