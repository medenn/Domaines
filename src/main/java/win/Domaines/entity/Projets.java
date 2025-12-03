package win.Domaines.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projets")
public class Projets {

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

    // Relation avec les images
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProjetImage> images = new ArrayList<>();

    public Projets() {}

    public Projets(String titrearabe, String titrefrancais, String descarabe,
                   String descfrancais, String lien) {
        this.titrearabe = titrearabe;
        this.titrefrancais = titrefrancais;
        this.descarabe = descarabe;
        this.descfrancais = descfrancais;
        this.lien = lien;
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

    public List<ProjetImage> getImages() { return images; }
    public void setImages(List<ProjetImage> images) { this.images = images; }

    public void addImage(ProjetImage image) {
        images.add(image);
        image.setProjet(this);
    }

    public void removeImage(ProjetImage image) {
        images.remove(image);
        image.setProjet(null);
    }
}
