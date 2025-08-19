package win.Domaines.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", length = 255)
    private String nom;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "objet", length = 255)
    private String objet;

    @Lob
    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    public Messages() {}

    public Messages(String nom, String email, String objet, String message) {
        this.nom = nom;
        this.email = email;
        this.objet = objet;
        this.message = message;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getObjet() { return objet; }
    public void setObjet(String objet) { this.objet = objet; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
