package kissat.ruokintaseuranta.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import kissat.ruokintaseuranta.domain.RuokaRepository;
import kissat.ruokintaseuranta.domain.Valmistaja;
import kissat.ruokintaseuranta.domain.Raakaaine;
import kissat.ruokintaseuranta.domain.Ruoka;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Optional;
import java.util.Set;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Service
@Transactional
public class RuokaService {
    @Autowired
    private RuokaRepository ruokaRepository;

    public List<Ruoka> haeKaikkiRuoat() {
        List<Ruoka> ruoat = StreamSupport
        .stream(ruokaRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
    return ruoat.stream()
        .collect(Collectors.toList());

    }

    @Transactional
    public Ruoka tallennaRuoka(@Valid Ruoka ruoka) {
        return ruokaRepository.save(ruoka);
    }

    public Optional<Ruoka> haeRuokaId(Long ruokaId) {
        return ruokaRepository.findById(ruokaId);
    }

    @Transactional
    public Optional<Ruoka> paivitaRuoka(Long ruokaId,
                                        @Valid @NotBlank(message = "Ruoan nimi ei voi olla tyhjä") 
                                        @Size(max = 500, message = "Ruoan nimi saa olla enintään 500 merkkiä pitkä") 
                                        String ruokaNimi, Valmistaja valmistaja, Set<Raakaaine> raakaaineet) {
        return ruokaRepository.findById(ruokaId).map(ruoka -> {
            ruoka.setRuokaNimi(ruokaNimi);
            ruoka.setValmistaja(valmistaja);
            ruoka.setRaakaaineet(raakaaineet);
            return ruokaRepository.save(ruoka);
        });
    }
   
    @Transactional
    public boolean poistaRuoka(Long ruokaId) {
        if (ruokaRepository.existsById(ruokaId)) {
            ruokaRepository.deleteById(ruokaId);
            return true;
        } else {
            return false;
        }
    }

}
