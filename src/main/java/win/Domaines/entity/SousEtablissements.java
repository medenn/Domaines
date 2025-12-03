package win.Domaines.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sous_etablissements")
public class SousEtablissements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titrearabe", length = 255)
    private String titrearabe;

    @Column(name = "titrefrancais", length = 255)
    private String titrefrancais;

    @Lob
    @Column(name = "descarabe", columnDefinition = "TEXT")
    private String descarabe;

    @Lob
    @Column(name = "descfrancais", columnDefinition = "TEXT")
    private String descfrancais;

    @Column(name = "lien", length = 500)
    private String lien;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "logo")
    private byte[] logo;

    public SousEtablissements() {}

    public SousEtablissements(String titrearabe, String titrefrancais, String descarabe, String descfrancais,
                              String lien, byte[] logo) {
        this.titrearabe = titrearabe;
        this.titrefrancais = titrefrancais;
        this.descarabe = descarabe;
        this.descfrancais = descfrancais;
        this.lien = lien;
        this.logo = logo;
    }

    // Getters & Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitrearabe() { return titrearabe; }
    public void setTitrearabe(String titrearabe) { this.titrearabe = titrearabe; }

    public String getTitrefrancais() { return titrefrancais; }
    public void setTitrefrancais(String titrefrancais) { this.titrefrancais = titrefrancais; }

    public String getDescarabe() { return descarabe; }
    public void setDescarabe(String descarabe) { this.descarabe = descarabe; }

    public String getDescfrancais() { return descfrancais; }
    public void setDescfrancais(String descfrancais) { this.descfrancais = descfrancais; }

    public String getLien() { return lien; }
    public void setLien(String lien) { this.lien = lien; }

    public byte[] getLogo() { return logo; }
    public void setLogo(byte[] logo) { this.logo = logo; }
}
