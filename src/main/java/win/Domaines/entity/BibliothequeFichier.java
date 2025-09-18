package win.Domaines.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bibliotheque_fichier")
public class BibliothequeFichier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "fichier")
    private byte[] fichier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bibliotheque_id")
    private Bibliotheque bibliotheque;

    public BibliothequeFichier() {}

    public BibliothequeFichier(byte[] fichier, Bibliotheque bibliotheque) {
        this.fichier = fichier;
        this.bibliotheque = bibliotheque;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public byte[] getFichier() { return fichier; }
    public void setFichier(byte[] fichier) { this.fichier = fichier; }

    public Bibliotheque getBibliotheque() { return bibliotheque; }
    public void setBibliotheque(Bibliotheque bibliotheque) { this.bibliotheque = bibliotheque; }
}
