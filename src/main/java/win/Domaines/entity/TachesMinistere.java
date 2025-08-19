package win.Domaines.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tachesministere")
public class TachesMinistere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titrearabe", length = 255)
    private String titrearabe;

    @Column(name = "titrefrancais", length = 255)
    private String titrefrancais;

    public TachesMinistere() {}

    public TachesMinistere(String titrearabe, String titrefrancais) {
        this.titrearabe = titrearabe;
        this.titrefrancais = titrefrancais;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitrearabe() { return titrearabe; }
    public void setTitrearabe(String titrearabe) { this.titrearabe = titrearabe; }

    public String getTitrefrancais() { return titrefrancais; }
    public void setTitrefrancais(String titrefrancais) { this.titrefrancais = titrefrancais; }
}
