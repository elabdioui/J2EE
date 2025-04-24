package DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domaine.Etudiant;

public class EtudiantDAO implements IEtudiantDAO {
	
	static Map<String ,Etudiant> etudiants= new HashMap<>(); 
	static{ 
	etudiants.put("A1", new Etudiant("A1", "nom1", "ville1", 20)); 
	etudiants.put("A2", new Etudiant("A2", "nom2", "ville2", 21)); 
	etudiants.put("B1", new Etudiant("B1", "nom3", "ville3", 20)); 
	etudiants.put("B2", new Etudiant("B2", "nom4", "ville4", 20)); 
	etudiants.put("C1", new Etudiant("C1", "nom5", "ville1", 20)); 
	} 

	@Override
	public void add(Etudiant e) {
		etudiants.put(e.getCode(), e); 

	}

	@Override
	public void delete(String code) {
		etudiants.remove(code); 

	}

	@Override
	public List<Etudiant> findAll() {
		List<Etudiant>  l=new ArrayList<>(etudiants.values()); 
		return l; 
	}

	@Override
	public void update(Etudiant e) {
		etudiants.put(e.getCode(), e);

	}

	@Override
	public List<Etudiant> findByVille(String v) {
		List<Etudiant>  l=new ArrayList<>(etudiants.values()); 
		for (Etudiant e: etudiants.values()) {
			if (e.getVille().equals(v)) l.add(e);
		}
		return l;
		
	}

	@Override
	public Etudiant findByCode(String c) {
		
		return etudiants.get(c);
		
	}

}
