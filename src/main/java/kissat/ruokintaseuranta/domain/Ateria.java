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
    private Long ateria_id;

    @Column(name="ateria_nimi", nullable = false)
    private String ateria_nimi;

    @JsonIgnore
    @OneToMany(mappedBy = "ateria")
    private Set<Ruokinta> ruokinnat;

    public Ateria() {

    }

    public Ateria(String ateria_nimi) {
        super ();
        this.ateria_nimi = ateria_nimi;
    }

    public Long getAteria_id() {
        return ateria_id;
    }

    public void setAteria_id(Long ateria_id) {
        this.ateria_id = ateria_id;
    }

    public String getAteria_nimi() {
        return ateria_nimi;
    }

    public void setAteria_nimi(String ateria_nimi) {
        this.ateria_nimi = ateria_nimi;
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
        "ateria_id=" + ateria_id +
        ", ateria_nimi='" + ateria_nimi + '\'' +
        '}';
    } 

}
