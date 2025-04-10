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
import jakarta.persistence.CascadeType;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name="Ruokinta")
@Transactional
public class Ruokinta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ruokinta_id")
    private Long ruokintaId;

    @NotNull(message = "Ruokinta-aika ei saa olla tyhjä")
    @PastOrPresent(message = "Ruokinta-aika ei voi olla tulevaisuudessa")
    @Column(name="ruokinta_aika", nullable = false)
    private LocalDate ruokintaAika;
    
    @Column(name="taimi_maistui", nullable = false)
    private boolean taimiMaistui;
    
    @Column(name="lempi_maistui", nullable = false)
    private boolean lempiMaistui;

    
    @NotNull(message = "Ateria ei saa olla tyhjä")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ateria_id")
    private Ateria ateria;

    
    @NotNull(message = "Ruoka ei saa olla tyhjä")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ruoka_id")
    private Ruoka ruoka;

    public Ruokinta () {

    }

    public Ruokinta(LocalDate ruokintaAika, Ateria ateria, Ruoka ruoka, boolean taimiMaistui, boolean lempiMaistui) {
        super();
        this.ruokintaAika = ruokintaAika;
        this.ateria = ateria;
        this.ruoka = ruoka != null ? ruoka : new Ruoka();
        this.taimiMaistui = taimiMaistui;
        this.lempiMaistui = lempiMaistui;
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
    }

    public boolean isLempiMaistui() {
        return lempiMaistui;
    }

    public void setLempiMaistui(boolean lempiMaistui) {
        this.lempiMaistui = lempiMaistui;
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


