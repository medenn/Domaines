package win.Domaines.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mot_ministre")
public class MotMinistre {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(name = "titrearabe", length = 255)
private String titrearabe;

@Column(name = "titrefrancais", length = 255)
private String titrefrancais;

@Lob
@Column(name = "textarabe", columnDefinition = "TEXT")
private String textarabe;

@Lob
@Column(name = "textfrancais", columnDefinition = "TEXT")
private String textfrancais;

public MotMinistre() {}

public MotMinistre(String titrearabe, String titrefrancais, String textarabe, String textfrancais) {
    this.titrearabe = titrearabe;
    this.titrefrancais = titrefrancais;
    this.textarabe = textarabe;
    this.textfrancais = textfrancais;
}

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public String getTitrearabe() { return titrearabe; }
public void setTitrearabe(String titrearabe) { this.titrearabe = titrearabe; }

public String getTitrefrancais() { return titrefrancais; }
public void setTitrefrancais(String titrefrancais) { this.titrefrancais = titrefrancais; }

public String getTextarabe() { return textarabe; }
public void setTextarabe(String textarabe) { this.textarabe = textarabe; }

public String getTextfrancais() { return textfrancais; }
public void setTextfrancais(String textfrancais) { this.textfrancais = textfrancais; }


}
