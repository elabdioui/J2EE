
public class Imc {
	
	private double poids;
	private double taille;
	
	public Imc(double taille, double poids) {
		
		this.poids=poids;
		this.taille=taille;
	}
	
	public double calcul() {
		
		return this.poids/this.taille * this.taille;
	}

}
