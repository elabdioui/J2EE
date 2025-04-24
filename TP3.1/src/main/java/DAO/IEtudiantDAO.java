package DAO;

import java.util.List;

import domaine.Etudiant;

public interface IEtudiantDAO {
	
	void add(Etudiant e); 
	
	void delete(String code);
	List<Etudiant> findAll(); 
	void update(Etudiant e); 
	List<Etudiant> findByVille(String v); 
	Etudiant findByCode(String c); 

}
