package form.beans;

import java.util.Vector;

public class NoteForm {
	
	private String numIns=""; 
	private Vector lesNotes=new Vector(); 
	public Vector getLesNotes() { 
	return lesNotes; 
	} 
	public void setLesNotes(Vector lesNotes) { 
	this.lesNotes = lesNotes; 
	} 
	public String getNumIns() { 
	return numIns; 
	} 
	public void setNumIns(String numIns) { 
	this.numIns = numIns; 
	} 

}
