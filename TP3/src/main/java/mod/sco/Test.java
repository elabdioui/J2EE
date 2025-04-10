package mod.sco; 
import java.util.Iterator; 
import java.util.Vector; 


public class Test { 
	public static void main(String[] args) { 
		
			Scolarite sco=new Scolarite(); 
			Vector lesNotes=sco.getNotes("HE1"); 
			Iterator it=lesNotes.iterator(); 
			while(it.hasNext()){ 
					Note n=(Note)it.next(); 
					System.out.println(n.getMatiere()+"--"+ 
					n.getNote());
			}
	}
}
