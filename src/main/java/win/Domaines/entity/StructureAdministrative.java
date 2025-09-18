package win.Domaines.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "structureadministrative")
public class StructureAdministrative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titrearabe", length = 255)
    private String titrearabe;

    @Column(name = "titrefrancais", length = 255)
    private String titrefrancais;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "fichierarabe")
    private byte[] fichierarabe;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "fichierfrancais")
    private byte[] fichierfrancais;

    @Column(name = "textarabe", columnDefinition = "TEXT")
    private String textArabe;

    @Column(name = "textfrancais", columnDefinition = "TEXT")
    private String textFrancais;

    public StructureAdministrative() {}

    public StructureAdministrative(String titrearabe, String titrefrancais, byte[] fichierarabe, byte[] fichierfrancais) {
        this.titrearabe = titrearabe;
        this.titrefrancais = titrefrancais;
        this.fichierarabe = fichierarabe;
        this.fichierfrancais = fichierfrancais;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitrearabe() { return titrearabe; }
    public void setTitrearabe(String titrearabe) { this.titrearabe = titrearabe; }

    public String getTitrefrancais() { return titrefrancais; }
    public void setTitrefrancais(String titrefrancais) { this.titrefrancais = titrefrancais; }

    public byte[] getFichierarabe() { return fichierarabe; }
    public void setFichierarabe(byte[] fichierarabe) { this.fichierarabe = fichierarabe; }

    public byte[] getFichierfrancais() { return fichierfrancais; }
    public void setFichierfrancais(byte[] fichierfrancais) { this.fichierfrancais = fichierfrancais; }

    public String getTextArabe() { return textArabe; }
    public void setTextArabe(String textArabe) { this.textArabe = textArabe; }

    public String getTextFrancais() { return textFrancais; }
    public void setTextFrancais(String textFrancais) { this.textFrancais = textFrancais; }
}
