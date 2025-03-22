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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
@Table(name="Ruoka")
public class Ruoka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ruoka_id")
    private Long ruokaId;
    
    @Column(name="ruoka_nimi", nullable = false)
    private String ruokaNimi;
    
    @Column(name="ruoka_pisteet", nullable = false)
    private double ruokaPisteet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="valmistaja_id")
    @JsonIgnore
    private Valmistaja valmistaja;

    @ManyToMany
    @JoinTable(
        name = "Ruoka_Raakaaine",
        joinColumns = @JoinColumn(name = "ruoka_id"),
        inverseJoinColumns = @JoinColumn(name = "raakaaine_id")
    )
    @JsonIgnore
    private Set<Raakaaine> raakaaineet = new HashSet<>();

    @OneToMany(mappedBy = "ruoka")
    @JsonIgnore
    private Set<Ruokinta> ruokinnat;


    public Ruoka () {

    }

    public Ruoka(String ruokaNimi, Valmistaja valmistaja, Set<Raakaaine> raakaaineet, double ruokaPisteet) {
        super();
        this.ruokaNimi = ruokaNimi;
        this.valmistaja = valmistaja;
        this.raakaaineet = raakaaineet;
        this.ruokaPisteet = ruokaPisteet;
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

    public Valmistaja getValmistaja() {
        return valmistaja;
    }

    public void setValmistaja(Valmistaja valmistaja) {
        this.valmistaja = valmistaja;
    }

    public Set<Raakaaine> getRaakaaineet() {
        return raakaaineet;
    }

    public void setRaakaaineet(Set<Raakaaine> raakaaineet) {
        this.raakaaineet = raakaaineet;
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
