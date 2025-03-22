package kissat.ruokintaseuranta.dto;

public class RaakaaineDTO {
    private Long raakaaine_id;
    private String raakaaine_nimi;

    public RaakaaineDTO() {

    }

    public RaakaaineDTO(Long raakaaine_id, String raakaaine_nimi) {
        this.raakaaine_id = raakaaine_id;
        this.raakaaine_nimi = raakaaine_nimi;
    }
    
    public Long getRaakaaineId() {
        return raakaaine_id;
    }

    public void setRaakaaineId(Long raakaaine_id) {
        this.raakaaine_id = raakaaine_id;
    }

    public String getRaakaaineNimi() {
        return raakaaine_nimi;
    }

    public void setRaakaaineNimi(String raakaaine_nimi) {
        this.raakaaine_nimi = raakaaine_nimi;
    }

    @Override
    public String toString() {
        return "RaakaaineDTO{" +
                "raakaaine_id=" +raakaaine_id +
                ", raakaaine_nimi=" + raakaaine_nimi +
                '}';
    }

}
