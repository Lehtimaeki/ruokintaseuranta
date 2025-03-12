package kissat.ruokintaseuranta.dto;

import java.time.LocalDate;


public class RuokintaDTO {
    private Long ruokintaId;
    private LocalDate ruokintaAika;
    private boolean taimiMaistui;
    private boolean lempiMaistui;
    private Long ateriaId;
    private Long ruokaId;

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

    public Long getAteriaId() {
        return ateriaId;
    }

    public void setAteriaId(Long ateriaId) {
        this.ateriaId = ateriaId;
    }

    public Long getRuokaId() {
        return ruokaId;
    }

    public void setRuokaId(Long ruokaId) {
        this.ruokaId = ruokaId;
    }
}