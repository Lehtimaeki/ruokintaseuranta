package kissat.ruokintaseuranta.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import kissat.ruokintaseuranta.domain.RuokaRepository;
import kissat.ruokintaseuranta.domain.RuokintaRepository;
import kissat.ruokintaseuranta.dto.RuokintaDTO;
import kissat.ruokintaseuranta.domain.Ruoka;
import kissat.ruokintaseuranta.domain.Ruokinta;

@Service
public class RuokintaService {
    @Autowired
    private RuokintaRepository ruokintaRepository;

    @Autowired
    private RuokaRepository ruokaRepository;

    public void tallennaRuokinta(RuokintaDTO ruokintaDTO) {
        Ruokinta ruokinta = new Ruokinta();
        ruokinta.setRuokintaAika(ruokintaDTO.getRuokintaAika());
        ruokinta.setTaimiMaistui(ruokintaDTO.isTaimiMaistui());
        ruokinta.setLempiMaistui(ruokintaDTO.isLempiMaistui());

        Ruoka ruoka = ruokaRepository.findById(ruokintaDTO.getRuokaId()).orElseThrow(() -> new RuntimeException("Ruoka not found"));
        ruokinta.setRuoka(ruoka);

        // Päivitä ruoan pisteet
        if (ruokintaDTO.isTaimiMaistui() && ruokintaDTO.isLempiMaistui()) {
            ruoka.addRuokaPisteet(1);
        } else if (ruokintaDTO.isTaimiMaistui() || ruokintaDTO.isLempiMaistui()) {
            ruoka.addRuokaPisteet(0.5);
        }

        ruokaRepository.save(ruoka);
        ruokintaRepository.save(ruokinta);
    }

    public List<RuokintaDTO> haeKaikkiRuokinnat() {
        List<Ruokinta> ruokinnat = StreamSupport
                .stream(ruokintaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return ruokinnat.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private RuokintaDTO convertToDTO(Ruokinta ruokinta) {
        RuokintaDTO dto = new RuokintaDTO();
        dto.setRuokintaId(ruokinta.getRuokintaId());
        dto.setRuokintaAika(ruokinta.getRuokintaAika());
        dto.setTaimiMaistui(ruokinta.isTaimiMaistui());
        dto.setLempiMaistui(ruokinta.isLempiMaistui());
        dto.setAteriaId(ruokinta.getAteria().getAteriaId());
        dto.setRuokaId(ruokinta.getRuoka().getRuokaId());
        return dto;
    }
}