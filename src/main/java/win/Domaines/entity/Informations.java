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

     @Column(name = "nomsitear", length = 255)
    private String nomsitear;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "adresse", length = 500)
    private String adresse;

    @Column(name = "adressear", length = 500)
    private String adressear;

    @Column(name = "telephone", length = 50)
    private String telephone;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "horaires", length = 255)
    private String horaires;

    @Column(name = "horairesar", length = 255)
    private String horairesar;

    public Informations() {}

   

    public Informations(String nomsite, String nomsitear, byte[] logo, String adresse, String adressear,
            String telephone, String email, String horaires, String horairesar) {
        this.nomsite = nomsite;
        this.nomsitear = nomsitear;
        this.logo = logo;
        this.adresse = adresse;
        this.adressear = adressear;
        this.telephone = telephone;
        this.email = email;
        this.horaires = horaires;
        this.horairesar = horairesar;
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



    public String getNomsitear() {
        return nomsitear;
    }



    public void setNomsitear(String nomsitear) {
        this.nomsitear = nomsitear;
    }



    public String getAdressear() {
        return adressear;
    }



    public void setAdressear(String adressear) {
        this.adressear = adressear;
    }



    public String getHorairesar() {
        return horairesar;
    }



    public void setHorairesar(String horairesar) {
        this.horairesar = horairesar;
    }


    
}
