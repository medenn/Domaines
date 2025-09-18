package win.Domaines.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "annonces")
public class Annonces {

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

    @Column(name = "datepublication")
    private LocalDateTime datepublication;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image")
    private byte[] image;

    public Annonces() {}

    

    public Annonces(String titrearabe, String titrefrancais, String textarabe, String textfrancais,
            LocalDateTime datepublication, byte[] image) {
        this.titrearabe = titrearabe;
        this.titrefrancais = titrefrancais;
        this.textarabe = textarabe;
        this.textfrancais = textfrancais;
        this.datepublication = datepublication;
        this.image = image;
    }



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

    public LocalDateTime getDatepublication() { return datepublication; }
    public void setDatepublication(LocalDateTime datepublication) { this.datepublication = datepublication; }

    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }
}
