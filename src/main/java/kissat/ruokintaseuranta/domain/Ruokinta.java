package kissat.ruokintaseuranta.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="Ruokinta")
public class Ruokinta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ruokinta_id")
    private Long ruokintaId;
    
    @Column(name="ruokinta_aika", nullable = false)
    private LocalDate ruokintaAika;
    
    @Column(name="taimi_maistui", nullable = false)
    private boolean taimiMaistui;
    
    @Column(name="lempi_maistui", nullable = false)
    private boolean lempiMaistui;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ateria_id")
    private Ateria ateria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ruoka_id")
    private Ruoka ruoka;

    public Ruokinta () {

    }

    public Ruokinta(LocalDate ruokintaAika, Ateria ateria, Ruoka ruoka, boolean taimiMaistui, boolean lempiMaistui) {
        super();
        this.ruokintaAika = ruokintaAika;
        this.ateria = ateria;
        this.ruoka = ruoka;
        this.taimiMaistui = taimiMaistui;
        this.lempiMaistui = lempiMaistui;
        this.paivitaRuokaPisteet();
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

    public Ateria getAteria() {
        return ateria;
    }

    public void setAteria (Ateria ateria) {
        this.ateria = ateria;
    }

    public Ruoka getRuoka() {
        return ruoka;
    }

    public void setRuoka (Ruoka ruoka) {
        this.ruoka = ruoka;
    }

    public boolean isTaimiMaistui() {
        return taimiMaistui;
    }

    public void setTaimiMaistui(boolean taimiMaistui) {
        this.taimiMaistui = taimiMaistui;
        this.paivitaRuokaPisteet();
    }

    public boolean isLempiMaistui() {
        return lempiMaistui;
    }

    public void setLempiMaistui(boolean lempiMaistui) {
        this.lempiMaistui = lempiMaistui;
        this.paivitaRuokaPisteet();
    }

    private void paivitaRuokaPisteet() {
        if (this.ruoka != null) {
        double ruokaPisteet = 0;
        if (taimiMaistui && lempiMaistui) {
            ruokaPisteet = 1;
        } else if (taimiMaistui || lempiMaistui) {
            ruokaPisteet = 0.5;
        }
        this.ruoka.addRuokaPisteet(ruokaPisteet);
    }
}

    @Override
    public String toString() {
        return "Ruokinta{" +
                "ruokintaId=" + ruokintaId +
                ", ruokintaAika=" + ruokintaAika +
                ", taimiMaistui=" + taimiMaistui +
                ", lempiMaistui=" + lempiMaistui +
                '}';
    }
}


