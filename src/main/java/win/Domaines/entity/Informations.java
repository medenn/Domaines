package win.Domaines.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "informations")
public class Informations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomsite", length = 255)
    private String nomsite;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "adresse", length = 500)
    private String adresse;

    @Column(name = "telephone", length = 50)
    private String telephone;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "horaires", length = 255)
    private String horaires;

    public Informations() {}

    public Informations(String nomsite, byte[] logo, String adresse, String telephone, String email, String horaires) {
        this.nomsite = nomsite;
        this.logo = logo;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.horaires = horaires;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomsite() { return nomsite; }
    public void setNomsite(String nomsite) { this.nomsite = nomsite; }

    public byte[] getLogo() { return logo; }
    public void setLogo(byte[] logo) { this.logo = logo; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getHoraires() { return horaires; }
    public void setHoraires(String horaires) { this.horaires = horaires; }
}
