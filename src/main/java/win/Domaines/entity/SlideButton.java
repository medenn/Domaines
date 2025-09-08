package win.Domaines.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "slidebuttons")
public class SlideButton {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titre_arabe", length = 255)
    private String titreArabe;

    @Column(name = "titre_francais", length = 255)
    private String titreFrancais;

    @Column(name = "lien", length = 500)
    private String lien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slide_id")
    private Slides slide;

    public SlideButton() {}

    public SlideButton(String titreArabe, String titreFrancais, String lien, Slides slide) {
        this.titreArabe = titreArabe;
        this.titreFrancais = titreFrancais;
        this.lien = lien;
        this.slide = slide;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitreArabe() { return titreArabe; }
    public void setTitreArabe(String titreArabe) { this.titreArabe = titreArabe; }

    public String getTitreFrancais() { return titreFrancais; }
    public void setTitreFrancais(String titreFrancais) { this.titreFrancais = titreFrancais; }

    public String getLien() { return lien; }
    public void setLien(String lien) { this.lien = lien; }

    public Slides getSlide() { return slide; }
    public void setSlide(Slides slide) { this.slide = slide; }
}
