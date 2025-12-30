package win.Domaines.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "actualites")
public class Actualites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titrearabe", length = 255)
    private String titrearabe;

    @Column(name = "titrefrancais", length = 255)
    private String titrefrancais;

    @Lob
    @Column(name = "textarabe", columnDefinition = "TEXT")
    private String textarabe;

    @Lob
    @Column(name = "textfrancais", columnDefinition = "TEXT")
    private String textfrancais;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image")
    private byte[] image;

    @Column(name = "datepublication")
    private LocalDateTime datepublication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorieid")
    private ActualitesCategories categorie;


     @Column(name = "lienar", length = 255)
    private String lienar;

    @Column(name = "lienfr", length = 255)
    private String lienfr;

    public Actualites() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitrearabe() { return titrearabe; }
    public void setTitrearabe(String titrearabe) { this.titrearabe = titrearabe; }

    public String getTitrefrancais() { return titrefrancais; }
    public void setTitrefrancais(String titrefrancais) { this.titrefrancais = titrefrancais; }

    public String getTextarabe() { return textarabe; }
    public void setTextarabe(String textarabe) { this.textarabe = textarabe; }

    public String getTextfrancais() { return textfrancais; }
    public void setTextfrancais(String textfrancais) { this.textfrancais = textfrancais; }

    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }

    public LocalDateTime getDatepublication() { return datepublication; }
    public void setDatepublication(LocalDateTime datepublication) { this.datepublication = datepublication; }

    public ActualitesCategories getCategorie() { return categorie; }
    public void setCategorie(ActualitesCategories categorie) { this.categorie = categorie; }

    public String getLienar() {
        return lienar;
    }

    public void setLienar(String lienar) {
        this.lienar = lienar;
    }

    public String getLienfr() {
        return lienfr;
    }

    public void setLienfr(String lienfr) {
        this.lienfr = lienfr;
    }

    
}
