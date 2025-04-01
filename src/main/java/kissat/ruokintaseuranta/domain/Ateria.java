package kissat.ruokintaseuranta.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
@Table(name="Ateria")
public class Ateria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ateria_id")
    private Long ateriaId;

    @Column(name="ateria_nimi", nullable = false)
    private String ateriaNimi;

    @JsonIgnore
    @OneToMany(mappedBy = "ateria")
    private Set<Ruokinta> ruokinnat;

    public Ateria() {

    }

    public Ateria(String ateriaNimi) {
        super ();
        this.ateriaNimi = ateriaNimi;
    }

    public Long getAteriaId() {
        return ateriaId;
    }

    public void setAteriaId(Long ateriaId) {
        this.ateriaId = ateriaId;
    }

    public String getAteriaNimi() {
        return ateriaNimi;
    }

    public void setAteriaNimi(String ateriaNimi) {
        this.ateriaNimi = ateriaNimi;
    }

    public Set<Ruokinta> getRuokinnat() {
        return ruokinnat;
    }

    public void setRuokinnat(Set<Ruokinta> ruokinnat) {
        this.ruokinnat = ruokinnat;
    }

    @Override
    public String toString() {
        return "Ateria{" +
        "ateriaId=" + ateriaId +
        ", ateriaNimi='" + ateriaNimi + '\'' +
        '}';
    } 

}
