package win.Domaines.entity;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "slides")
public class Slides {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titre_arabe", length = 255)
    private String titreArabe;

    @Column(name = "titre_francais", length = 255)
    private String titreFrancais;

    @Column(name = "text_arabe", columnDefinition = "TEXT")
    private String textArabe;

    @Column(name = "text_francais", columnDefinition = "TEXT")
    private String textFrancais;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image")
    private byte[] image;

    @OneToMany(mappedBy = "slide", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SlideButton> buttons = new ArrayList<>();

    public Slides() {}

    public Slides(String titreArabe, String titreFrancais, String textArabe, String textFrancais, byte[] image) {
        this.titreArabe = titreArabe;
        this.titreFrancais = titreFrancais;
        this.textArabe = textArabe;
        this.textFrancais = textFrancais;
        this.image = image;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitreArabe() { return titreArabe; }
    public void setTitreArabe(String titreArabe) { this.titreArabe = titreArabe; }

    public String getTitreFrancais() { return titreFrancais; }
    public void setTitreFrancais(String titreFrancais) { this.titreFrancais = titreFrancais; }

    public String getTextArabe() { return textArabe; }
    public void setTextArabe(String textArabe) { this.textArabe = textArabe; }

    public String getTextFrancais() { return textFrancais; }
    public void setTextFrancais(String textFrancais) { this.textFrancais = textFrancais; }

    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }

    public List<SlideButton> getButtons() { return buttons; }
    public void setButtons(List<SlideButton> buttons) { this.buttons = buttons; }
}

