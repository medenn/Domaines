package win.Domaines.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lois")
public class Lois {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titrearabe", length = 255)
    private String titrearabe;

    @Column(name = "titrefrancais", length = 255)
    private String titrefrancais;

    @Column(name = "datesortie")
    private LocalDate datesortie;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "fichierarabe")
    private byte[] fichierarabe;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "fichierfrancais")
    private byte[] fichierfrancais;

    public Lois() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitrearabe() { return titrearabe; }
    public void setTitrearabe(String titrearabe) { this.titrearabe = titrearabe; }

    public String getTitrefrancais() { return titrefrancais; }
    public void setTitrefrancais(String titrefrancais) { this.titrefrancais = titrefrancais; }

    public LocalDate getDatesortie() { return datesortie; }
    public void setDatesortie(LocalDate datesortie) { this.datesortie = datesortie; }

    public byte[] getFichierarabe() { return fichierarabe; }
    public void setFichierarabe(byte[] fichierarabe) { this.fichierarabe = fichierarabe; }

    public byte[] getFichierfrancais() { return fichierfrancais; }
    public void setFichierfrancais(byte[] fichierfrancais) { this.fichierfrancais = fichierfrancais; }
}
