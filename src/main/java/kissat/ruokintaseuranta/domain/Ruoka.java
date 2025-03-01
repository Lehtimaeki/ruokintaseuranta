package kissat.ruokintaseuranta.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;


@Entity
public class Ruoka {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="valmistajaId")
    private Valmistaja valmistaja;

    public Valmistaja getValmistaja() {    
        return valmistaja;
    }
    public void setValmistaja(Valmistaja valmistaja) {
        this.valmistaja = valmistaja;
    }

    @ManyToMany
    private Set<Raakaaine> raakaaineet = new HashSet<Raakaaine>();
    
    public Set<Raakaaine> getRaakaaineet() {
        return raakaaineet;
    }

    public void setRaakaaineet(Set<Raakaaine> raakaaineet) {
    this.raakaaineet = raakaaineet;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ruokaId;
    private String ruokaNimi;

    public Ruoka () {

    }

    public Ruoka(String ruokaNimi, Valmistaja valmistaja, Set<Raakaaine> raakaaineet) {
        super();
        this.ruokaNimi = ruokaNimi;
        this.valmistaja = valmistaja;
        this.raakaaineet = raakaaineet;
    }

    public Long getRuokaId() {
        return ruokaId;
    }

    public void setRuokaId(Long ruokaId) {
        this.ruokaId = ruokaId;
    }

    public String getRuokaNimi() {
        return ruokaNimi;
    }

    public void setRuokaNimi(String ruokaNimi) {
        this.ruokaNimi = ruokaNimi;
    }

    @Override
    public String toString() {
        return "Ruoka{" +
                "ruokaId=" + ruokaId +
                ", ruokaNimi='" + ruokaNimi + '\'' +
                '}';
    }

}
