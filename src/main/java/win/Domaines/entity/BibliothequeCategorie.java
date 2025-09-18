package win.Domaines.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "bibliothequecategorie")
public class BibliothequeCategorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomarabe", length = 255)
    private String nomarabe;

    @Column(name = "nomfrancais", length = 255)
    private String nomfrancais;

    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
    private List<Bibliotheque> bibliotheques;

    public BibliothequeCategorie() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomarabe() { return nomarabe; }
    public void setNomarabe(String nomarabe) { this.nomarabe = nomarabe; }

    public String getNomfrancais() { return nomfrancais; }
    public void setNomfrancais(String nomfrancais) { this.nomfrancais = nomfrancais; }

    public List<Bibliotheque> getBibliotheques() { return bibliotheques; }
    public void setBibliotheques(List<Bibliotheque> bibliotheques) { this.bibliotheques = bibliotheques; }
}
