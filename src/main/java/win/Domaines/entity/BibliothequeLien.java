package win.Domaines.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "bibliotheque_lien")
public class BibliothequeLien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "bibliotheque_id")
    private Bibliotheque bibliotheque;

    public BibliothequeLien() {}

    public BibliothequeLien(String url, Bibliotheque bibliotheque) {
        this.url = url;
        this.bibliotheque = bibliotheque;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Bibliotheque getBibliotheque() { return bibliotheque; }
    public void setBibliotheque(Bibliotheque bibliotheque) { this.bibliotheque = bibliotheque; }
}
