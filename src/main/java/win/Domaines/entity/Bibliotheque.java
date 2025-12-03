package win.Domaines.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "bibliotheque")
public class Bibliotheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "descriptionarabe", columnDefinition = "TEXT")
    private String descriptionarabe;

    @Lob
    @Column(name = "descriptionfrancais", columnDefinition = "TEXT")
    private String descriptionfrancais;

    @OneToMany(mappedBy = "bibliotheque", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BibliothequeFichier> fichiers;

    @OneToMany(mappedBy = "bibliotheque", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
private List<BibliothequeLien> liens;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private BibliothequeCategorie categorie;

    @Column(name = "ordre")
    private Integer ordre;

    @Column(name = "etat")
    private Boolean etat;

    public Bibliotheque() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescriptionarabe() { return descriptionarabe; }
    public void setDescriptionarabe(String descriptionarabe) { this.descriptionarabe = descriptionarabe; }

    public String getDescriptionfrancais() { return descriptionfrancais; }
    public void setDescriptionfrancais(String descriptionfrancais) { this.descriptionfrancais = descriptionfrancais; }

    public List<BibliothequeFichier> getFichiers() { return fichiers; }
    public void setFichiers(List<BibliothequeFichier> fichiers) { this.fichiers = fichiers; }

    public BibliothequeCategorie getCategorie() { return categorie; }
    public void setCategorie(BibliothequeCategorie categorie) { this.categorie = categorie; }

    public Integer getOrdre() { return ordre; }
    public void setOrdre(Integer ordre) { this.ordre = ordre; }

    public Boolean getEtat() { return etat; }
    public void setEtat(Boolean etat) { this.etat = etat; }

    public List<BibliothequeLien> getLiens() { return liens; }
public void setLiens(List<BibliothequeLien> liens) { this.liens = liens; }
}
