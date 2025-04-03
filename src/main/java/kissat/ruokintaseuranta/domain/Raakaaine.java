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
    private Long raakaaineId;
    
    @Column(name="raakaaine_nimi", nullable = false)
    private String raakaaineNimi;
    
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

    public Raakaaine (String raakaaineNimi) {
        super ();
        this.raakaaineNimi = raakaaineNimi;
    }

    public Long getRaakaaineId() {
        return raakaaineId;
    }

    public void setRaakaaineId(Long raakaaineId) {
        this.raakaaineId = raakaaineId; 
    }

    public String getRaakaaineNimi() {
        return raakaaineNimi;
    }

    public void setRaakaaineNimi(String raakaaineNimi) {
        this.raakaaineNimi = raakaaineNimi;
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
                "raakaaineId=" + raakaaineId +
                ", raakaaineNimi='" + raakaaineNimi + '\'' +
                '}';
    }

}
