package mod.sco;

import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.Vector; 

public class Scolarite {
	
	public Vector getNotes(String numIns) {
		Vector lesNotes = new Vector();
		
		Connection conn = Utilitaire.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from NOTES where NUM_INS=?");
			ps.setString(1, numIns);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Note n = new Note();
				n.setIdnote(rs.getInt("ID_NOTE"));
				n.setNumInscr(rs.getString("NUM_INS")); 
				n.setMatiere(rs.getString("MATIERE")); 
				n.setNote(rs.getFloat("NOTE")); 
				lesNotes.add(n); 
			}
		} catch (SQLException e) { 
		
			e.printStackTrace(); 
			} 
		
		return lesNotes;
	}
	

}
