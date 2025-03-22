package kissat.ruokintaseuranta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;

@Entity
@Table(name="Raakaaine")
public class Raakaaine {

    @Id
    @Column(name = "raakaaine_id")
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long raakaaine_id;
    
    @Column(name="raakaaine_nimi", nullable = false)
    private String raakaaine_nimi;
    
    @ManyToMany
    @JoinTable(
        name = "Ruoka_Raakaaine",
        joinColumns = @JoinColumn(name = "raakaaine_id"),
        inverseJoinColumns = @JoinColumn(name = "ruoka_id")
    )
    @JsonIgnore
    private Set<Ruoka> ruoat = new HashSet<>();

    public Raakaaine () {

    }

    public Raakaaine (String raakaaine_nimi) {
        super ();
        this.raakaaine_nimi = raakaaine_nimi;
    }

    public Long getRaakaaineId() {
        return raakaaine_id;
    }

    public void setRaakaaineId(Long raakaaine_id) {
        this.raakaaine_id = raakaaine_id; 
    }

    public String getRaakaaineNimi() {
        return raakaaine_nimi;
    }

    public void setRaakaaineNimi(String raakaaine_nimi) {
        this.raakaaine_nimi = raakaaine_nimi;
    }

    public Set<Ruoka> getRuoat() {
        return ruoat;
    }

    public void setRuoat(Set<Ruoka> ruoat) {
        this.ruoat = ruoat;
        }

    @Override
    public String toString() {
        return "Raakaaine{" +
                "raakaaine_id=" + raakaaine_id +
                ", raakaaine_nimi='" + raakaaine_nimi + '\'' +
                '}';
    }

}
