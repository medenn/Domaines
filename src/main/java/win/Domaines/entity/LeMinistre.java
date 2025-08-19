package win.Domaines.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "leministre")
public class LeMinistre {

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

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "cvarabe")
    private byte[] cvarabe; // CV arabe en PDF ou image

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "cvfrancais")
    private byte[] cvfrancais; // CV français en PDF ou image

    public LeMinistre() {}

    public LeMinistre(String titrearabe, String titrefrancais, String textarabe, String textfrancais, byte[] image, byte[] cvarabe, byte[] cvfrancais) {
        this.titrearabe = titrearabe;
        this.titrefrancais = titrefrancais;
        this.textarabe = textarabe;
        this.textfrancais = textfrancais;
        this.image = image;
        this.cvarabe = cvarabe;
        this.cvfrancais = cvfrancais;
    }

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

    public byte[] getCvarabe() { return cvarabe; }
    public void setCvarabe(byte[] cvarabe) { this.cvarabe = cvarabe; }

    public byte[] getCvfrancais() { return cvfrancais; }
    public void setCvfrancais(byte[] cvfrancais) { this.cvfrancais = cvfrancais; }
}
