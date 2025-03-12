package kissat.ruokintaseuranta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name="raakaaine")
public class Raakaaine {

    @ManyToMany(mappedBy = "raakaaineet")
    private Set<Ruoka> ruoat = new HashSet<Ruoka>();
    public Set<Ruoka> getRuoat() {
        return ruoat;
    }
    public void setRuoat(Set<Ruoka> ruoat) {
    this.ruoat = ruoat;
    }

    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long raakaaineId;
    
    @Column(name="raakaainenimi")
    private String raakaaineNimi;

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

    @Override
    public String toString() {
        return "Raakaaine{" +
                "raakaaineId=" + raakaaineId +
                ", raakaaineNimi='" + raakaaineNimi + '\'' +
                '}';
    }

}
