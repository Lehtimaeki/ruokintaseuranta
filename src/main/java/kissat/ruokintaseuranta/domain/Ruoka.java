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

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name="Ruoka")
@Transactional
public class Ruoka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ruoka_id")
    private Long ruokaId;
    
    @NotBlank(message = "Ruoan nimi ei voi olla tyhjä")
    @Size(max = 500, message = "Ruoan nimi saa olla enintään 500 merkkiä pitkä")
    @Column(name="ruoka_nimi", nullable = false)
    private String ruokaNimi;

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
                '}';
    }

}
