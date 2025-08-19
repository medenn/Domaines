package win.Domaines.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "liste_taches_ministere")
public class ListeTachesMinistere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tacheid", nullable = false)
    private TachesMinistere tachesMinistere; // référence à la tâche principale

    @Column(name = "titrearabe", length = 255)
    private String titrearabe;

    @Column(name = "titrefrancais", length = 255)
    private String titrefrancais;

    public ListeTachesMinistere() {}

    public ListeTachesMinistere(TachesMinistere tachesMinistere, String titrearabe, String titrefrancais) {
        this.tachesMinistere = tachesMinistere;
        this.titrearabe = titrearabe;
        this.titrefrancais = titrefrancais;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TachesMinistere getTachesMinistere() { return tachesMinistere; }
    public void setTachesMinistere(TachesMinistere tachesMinistere) { this.tachesMinistere = tachesMinistere; }

    public String getTitrearabe() { return titrearabe; }
    public void setTitrearabe(String titrearabe) { this.titrearabe = titrearabe; }

    public String getTitrefrancais() { return titrefrancais; }
    public void setTitrefrancais(String titrefrancais) { this.titrefrancais = titrefrancais; }
}
