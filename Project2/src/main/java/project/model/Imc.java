package project.model;

public class Imc {
    private double taille;
    private double poids;  

    public Imc(double taille, double poids) {
        this.taille = taille;
        this.poids = poids;
    }

    public double calcul() {
        if (taille > 0) {
            return poids / (taille * taille);
        } else {
            throw new IllegalArgumentException("La taille doit être supérieure à zéro.");
        }
    }

	public Object getTaille() {
		return taille;
	}
	public Object getPoids() {
		return poids;
	}
}

