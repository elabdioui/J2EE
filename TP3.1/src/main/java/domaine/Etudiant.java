import jakarta.persistence.*;

@Entity
@Table(name = "etudiants")
public class Etudiant {
    
    @Id
    @Column(name = "code", length = 10)
    private String code;
    
    @Column(name = "nom", length = 50, nullable = false)
    private String nom;
    
    @Column(name = "ville", length = 50, nullable = false)
    private String ville;
    
    @Column(name = "age", nullable = false)
    private int age;
    
    // Constructeur par défaut nécessaire pour JPA
    public Etudiant() {}
    
    // Constructeur avec paramètres
    public Etudiant(String code, String nom, String ville, int age) {
        this.code = code;
        this.nom = nom;
        this.ville = ville;
        this.age = age;
    }
    
    // Getters et Setters
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getVille() {
        return ville;
    }
    
    public void setVille(String ville) {
        this.ville = ville;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
}