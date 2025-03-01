package kissat.ruokintaseuranta.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;


@Entity
public class Ruokinta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ruokintaId;
    private LocalDate ruokintaAika;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ateria")
    private Ateria ateria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ruoka")
    private Ruoka ruoka;

    public Ruokinta () {

    }

    public Ruokinta(LocalDate ruokintaAika, Ateria ateria, Ruoka ruoka) {
        super();
        this.ruokintaAika = ruokintaAika;
        this.ateria = ateria;
        this.ruoka = ruoka;
    }

    public Long getRuokintaId() {
        return ruokintaId;
    }

    public void setRuokintaId(Long ruokintaId) {
        this.ruokintaId = ruokintaId;
    }

    public LocalDate getRuokintaAika() {
        return ruokintaAika;
    }

    public void setRuokintaAika(LocalDate ruokintaAika) {
        this.ruokintaAika = ruokintaAika;
    }

    @Override
    public String toString() {
        return "Ruokinta{" +
        "ruokintaId=" + ruokintaId +
        ", ruokintaAika='" + ruokintaAika + '\'' +
        '}';
    }
}


