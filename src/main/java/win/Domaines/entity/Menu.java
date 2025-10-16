package win.Domaines.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomarabe", length = 255)
    private String nomarabe;

    @Column(name = "nomfrancais", length = 255)
    private String nomfrancais;

    @Column(name = "lien", length = 500)
    private String lien;

    @Column(name = "ordre")
    private Integer ordre;

    @Column(name = "etat")
    private Boolean etat;

    // relation auto-référentielle (menu parent / sous-menus)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Menu parent;

   
    public Menu() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomarabe() { return nomarabe; }
    public void setNomarabe(String nomarabe) { this.nomarabe = nomarabe; }

    public String getNomfrancais() { return nomfrancais; }
    public void setNomfrancais(String nomfrancais) { this.nomfrancais = nomfrancais; }

    public String getLien() { return lien; }
    public void setLien(String lien) { this.lien = lien; }

    public Integer getOrdre() { return ordre; }
    public void setOrdre(Integer ordre) { this.ordre = ordre; }

    public Boolean getEtat() { return etat; }
    public void setEtat(Boolean etat) { this.etat = etat; }

    public Menu getParent() { return parent; }
    public void setParent(Menu parent) { this.parent = parent; }

  
}
