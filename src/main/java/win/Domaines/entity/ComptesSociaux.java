package win.Domaines.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "comptessociaux")
public class ComptesSociaux {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", length = 255)
    private String nom;

    @Column(name = "lien", length = 500)
    private String lien;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "icon")
    private byte[] icon;

    public ComptesSociaux() {}

    public ComptesSociaux(String nom, String lien, byte[] icon) {
        this.nom = nom;
        this.lien = lien;
        this.icon = icon;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getLien() { return lien; }
    public void setLien(String lien) { this.lien = lien; }

    public byte[] getIcon() { return icon; }
    public void setIcon(byte[] icon) { this.icon = icon; }
}
