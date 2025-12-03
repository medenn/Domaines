package win.Domaines.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "projets_images")
public class ProjetImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image")
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projet_id")
    private Projets projet;

    public ProjetImage() {}

    public ProjetImage(byte[] image) {
        this.image = image;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }

    public Projets getProjet() { return projet; }
    public void setProjet(Projets projet) { this.projet = projet; }
}
