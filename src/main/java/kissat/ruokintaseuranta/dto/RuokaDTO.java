package kissat.ruokintaseuranta.dto;

import java.util.List;

public class RuokaDTO {
    private Long ruokaId;
    private String ruokaNimi;
    private List<String> raakaaineet;
    private String valmistaja;
    private double ruokaPisteet;

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

    public List<String> getRaakaaineet() {
        return raakaaineet;
    }

    public void setRaakaaineet(List<String> raakaaineet) {
        this.raakaaineet = raakaaineet;
    }

    public String getValmistaja() {
        return valmistaja;
    }

    public void setValmistaja(String valmistaja) {
        this.valmistaja = valmistaja;
    }

    public double getRuokaPisteet() {
        return ruokaPisteet;
    }

    public void setRuokaPisteet(double ruokaPisteet) {
        this.ruokaPisteet = ruokaPisteet;
    }
}
