package kissat.ruokintaseuranta.dto;

import java.util.Set;

public class RuokaDTO {

    private Long ruokaId;
    private String ruokaNimi;
    private double ruokaPisteet;
    private Long valmistajaId;
    private Set<Long> raakaaineIds;

    public RuokaDTO() {
    }

    public RuokaDTO(Long ruokaId, String ruokaNimi, double ruokaPisteet, Long valmistajaId, Set<Long> raakaaineIds) {
        this.ruokaId = ruokaId;
        this.ruokaNimi = ruokaNimi;
        this.ruokaPisteet = ruokaPisteet;
        this.valmistajaId = valmistajaId;
        this.raakaaineIds = raakaaineIds;
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

    public Long getValmistajaId() {
        return valmistajaId;
    }

    public void setValmistajaId(Long valmistajaId) {
        this.valmistajaId = valmistajaId;
    }

    public Set<Long> getRaakaaineIds() {
        return raakaaineIds;
    }

    public void setRaakaaineIds(Set<Long> raakaaineIds) {
        this.raakaaineIds = raakaaineIds;
    }

    @Override
    public String toString() {
        return "RuokaDTO{" +
                "ruokaId=" + ruokaId +
                ", ruokaNimi='" + ruokaNimi + '\'' +
                ", ruokaPisteet=" + ruokaPisteet +
                ", valmistajaId=" + valmistajaId +
                ", raakaaineIds=" + raakaaineIds +
                '}';
    }
}
