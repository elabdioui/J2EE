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
	  String req = "insert into etudiants values(?, ?,?,?)"; 
	  try { 
	   Connection cnx = ConnectionFactory.getConnection();
	   PreparedStatement ps = cnx.prepareStatement(req); 
	   // injecter les donnees dans les ? 
	   ps.setString(1, e.getCode()); 
	   ps.setString(2, e.getNom()); 
	   ps.setString(3, e.getVille()); 
	   ps.setInt(4, e.getAge()); 
	   ps.executeUpdate(); 
	   ps.close(); 
	  } catch (Exception ex) { 
	 
	  } 
	 } 
	 
	 @Override 
	 public void delete(String code) { 
	  String req = "delete from etudiants where code=?"; 
	  try { 
	   Connection cnx = ConnectionFactory.getConnection(); 
	   PreparedStatement ps = cnx.prepareStatement(req); 
	   // injecter le code dans le ? 
	            ps.setString(1, code); 
	            ps.executeUpdate(); 
	   ps.close(); 
	  } catch (Exception e) { 
	  } 
	 
	 } 
	 
	 @Override 
	 public List<Etudiant> findAll() { 
	        String req = "select * from etudiants"; 
	  List<Etudiant> etudiantsList = new ArrayList<>(); 
	  try { 
	   Connection cnx = ConnectionFactory.getConnection(); 
	   Statement st = cnx.createStatement(); 
	   ResultSet rs = st.executeQuery(req); 
	   Etudiant e = null; 
	   while (rs.next()){ 
	   e = new Etudiant(); 
	   e.setCode(rs.getString(1)); 
	   e.setNom(rs.getString(2)); 
	   e.setVille(rs.getString(3)); 
	   e.setAge(rs.getInt(4)); 
	   etudiantsList.add(e);} 
	 
	  } catch (Exception ex) {}
	  return etudiantsList; 
	 } 
	 
	 @Override 
	 public void update(Etudiant e) { 
	  String req = "update etudiants set ville=? where code=?";  
	  try { 
	   Connection cnx = ConnectionFactory.getConnection(); 
	   PreparedStatement ps = cnx.prepareStatement(req); 
	   ps.setString(1, e.getVille()); 
	   ps.setInt(2, e.getAge()); 
	   ps.setString(3, e.getCode()); 
	   ps.executeUpdate(); 
	   ps.close(); 
	    
	  } catch (Exception ex) {} 
	 } 
	 
	 @Override 
	 public List<Etudiant> findByVille(String v) { 
	  String req = "select * from etudiants where ville=?"; 
	  List<Etudiant> etudiantsList = new ArrayList<>(); 
	  try { 
	   Connection cnx = ConnectionFactory.getConnection(); 
	   PreparedStatement ps = cnx.prepareStatement(req); 
	   ps.setString(1, v); 
	   ResultSet rs = ps.executeQuery(); 
	   Etudiant e = null; 
	   while (rs.next()){ 
	   e = new Etudiant(); 
	   e.setCode(rs.getString(1)); 
	   e.setNom(rs.getString(2)); 
	   e.setVille(rs.getString(3)); 
	   e.setAge(rs.getInt(4)); 
	   etudiantsList.add(e);} 
	 
	  } catch (Exception ex) {} 
	  return etudiantsList; 
	 } 
	 
	 @Override 
	 public Etudiant findByCode(String c) { 
	  String req = "select * from etudiants where code=?"; 
	  Etudiant e = null; 
	  try { 
	   Connection cnx = ConnectionFactory.getConnection(); 
	   PreparedStatement ps = cnx.prepareStatement(req); 
	   ps.setString(1, c); 
	   ResultSet rs = ps.executeQuery(); 
	 
	   if (rs.next()){ 
	   e = new Etudiant(); 
	   e.setCode(rs.getString(1)); 
	   e.setNom(rs.getString(2)); 
	   e.setVille(rs.getString(3)); 
	   e.setAge(rs.getInt(4)); 
	    
	   } 
	 
	  } catch (Exception ex) 
	{System.out.println(ex.getMessage());} 
	  return e; 
	 
	 } 
	 
	} 

	   