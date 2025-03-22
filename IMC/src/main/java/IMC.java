
public class IMC {
	
	private Double poids ;
	private Double taille;
	
	
	public IMC(Double poids,Double taille) {
		this.poids=poids;
		this.taille=taille;
	}
	
	public Double CalculImc() {
		
		return poids / taille * taille;
	}

}
