package kissat.ruokintaseuranta.domain;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="valmistaja")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Valmistaja {

    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long valmistajaId;
    @Column(name="valmistajanimi")
    private String valmistajaNimi;

    @JsonIgnore
    @OneToMany(mappedBy = "valmistaja")
    private Set<Ruoka> ruoat;

    public Valmistaja () {
    }

    public Valmistaja (String valmistajaNimi) {
        super ();
        this.valmistajaNimi = valmistajaNimi;
    }

    public Long getValmistajaId() {
        return valmistajaId;
    }

    public void setValmistajaId(Long valmistajaId) {
        this.valmistajaId = valmistajaId; 
    }

    public String getValmistajaNimi() {
        return valmistajaNimi;
    }

    public void setValmistajaNimi(String valmistajaNimi) {
        this.valmistajaNimi = valmistajaNimi;
    }

    @Override
    public String toString() {
        return "Valmistaja{" +
                "valmistajaId=" + valmistajaId +
                ", valmistajaNimi='" + valmistajaNimi + '\'' +
                '}';
    }

}
