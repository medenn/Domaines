package win.Domaines.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "actualites_categories")
public class ActualitesCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomarabe", length = 255, nullable = false)
    private String nomarabe;

    @Column(name = "nomfrancais", length = 255, nullable = false)
    private String nomfrancais;

    public ActualitesCategories() {}

    public ActualitesCategories(String nomarabe, String nomfrancais) {
        this.nomarabe = nomarabe;
        this.nomfrancais = nomfrancais;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomarabe() { return nomarabe; }
    public void setNomarabe(String nomarabe) { this.nomarabe = nomarabe; }

    public String getNomfrancais() { return nomfrancais; }
    public void setNomfrancais(String nomfrancais) { this.nomfrancais = nomfrancais; }
}
