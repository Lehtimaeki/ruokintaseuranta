package kissat.ruokintaseuranta.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Ateria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ateriaId;
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

    @Override
    public String toString() {
        return "Ateria{" +
        "ateriaId=" + ateriaId +
        ", ateriaNimi='" + ateriaNimi + '\'' +
        '}';
    } 

}
