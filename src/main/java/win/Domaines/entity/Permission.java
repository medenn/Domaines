package win.Domaines.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    // Nom de la colonne en base "roles"
    @Column(name = "role")
    private String role;

    @Column(name = "url_pattern")
    private String urlPattern;

    @Column(name = "methode")
    private String methode; // GET, POST, PUT, DELETE, etc.

    public Permission() {
    }

    public Permission(String name, String role, String urlPattern, String methode) {
        this.name = name;
        this.role = role;
        this.urlPattern = urlPattern;
        this.methode = methode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethode() {
        return methode;
    }

    public void setMethode(String methode) {
        this.methode = methode;
    }
}
