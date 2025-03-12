package kissat.ruokintaseuranta.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
@Table(name="ruoka")
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

    @JsonIgnore
    @OneToMany(mappedBy = "ruoka")
    private Set<Ruokinta> ruokinnat;


    public void setRaakaaineet(Set<Raakaaine> raakaaineet) {
    this.raakaaineet = raakaaineet;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ruokaId;
    @Column(name="ruokanimi")
    private String ruokaNimi;
    @Column(name="ruokapisteet")
    private double ruokaPisteet;

    public Ruoka () {

    }

    public Ruoka(String ruokaNimi, Valmistaja valmistaja, Set<Raakaaine> raakaaineet, double ruokaPisteet) {
        super();
        this.ruokaNimi = ruokaNimi;
        this.valmistaja = valmistaja;
        this.raakaaineet = raakaaineet;
        this.ruokaPisteet = 0;
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

    public double getRuokaPisteet() {
        return ruokaPisteet;
    }

    public void setRuokaPisteet(double ruokaPisteet) {
        this.ruokaPisteet = ruokaPisteet;
    }

    public void addRuokaPisteet(double ruokaPisteet) {
        this.ruokaPisteet += ruokaPisteet;
    }

    @Override
    public String toString() {
        return "Ruoka{" +
                "ruokaId=" + ruokaId +
                ", ruokaNimi='" + ruokaNimi + '\'' +
                ", ruokaPisteet=" + ruokaPisteet +
                '}';
    }

}
