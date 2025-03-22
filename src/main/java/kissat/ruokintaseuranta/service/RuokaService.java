package kissat.ruokintaseuranta.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import kissat.ruokintaseuranta.domain.RuokaRepository;
import kissat.ruokintaseuranta.domain.Valmistaja;
import kissat.ruokintaseuranta.domain.Raakaaine;
import kissat.ruokintaseuranta.domain.Ruoka;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Optional;
import java.util.Set;

@Service
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

    public Ruoka tallennaRuoka(Ruoka ruoka) {
        return ruokaRepository.save(ruoka);
    }

    public Optional<Ruoka> haeRuokaId(Long ruokaId) {
        return ruokaRepository.findById(ruokaId);
    }

    public Optional<Ruoka> paivitaRuoka(Long ruokaId, String ruokaNimi, Valmistaja valmistaja, Set<Raakaaine> raakaaineet, double ruokaPisteet) {
        return ruokaRepository.findById(ruokaId).map(ruoka -> {
            ruoka.setRuokaNimi(ruoka.getRuokaNimi());
            return ruokaRepository.save(ruoka);
        });
    }

    public boolean poistaRuoka(Long ruokaId) {
        if (ruokaRepository.existsById(ruokaId)) {
            ruokaRepository.deleteById(ruokaId);
            return true;
        } else {
            return false;
        }
    }

}
