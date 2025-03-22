package kissat.ruokintaseuranta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import kissat.ruokintaseuranta.domain.Ruokinta;
import kissat.ruokintaseuranta.domain.RuokintaRepository;

@Service
@Transactional
public class RuokintaService {

    @Autowired
    private RuokintaRepository ruokintarepo;

    public List<Ruokinta> haeKaikkiRuokinnat() {
        Iterable<Ruokinta> ruokinnat = ruokintarepo.findAll();
        return StreamSupport.stream(ruokinnat.spliterator(), false).collect(Collectors.toList());
    }

    @Transactional
    public Ruokinta uusiRuokinta(Ruokinta uusiRuokinta) {
        return ruokintarepo.save(uusiRuokinta);
    }

    public Optional<Ruokinta> haeRuokintaId(Long ruokintaId) {
        return ruokintarepo.findById(ruokintaId);
    }

    @Transactional
    public Optional<Ruokinta> paivitaRuokinta(Long ruokintaId, Ruokinta ruokintaTiedot) {
        Optional<Ruokinta> ruokinta = ruokintarepo.findById(ruokintaId);
        if (ruokinta.isPresent()) {
            Ruokinta paivitettyRuokinta = ruokinta.get();
            paivitettyRuokinta.setRuokintaAika(ruokintaTiedot.getRuokintaAika());
            paivitettyRuokinta.setTaimiMaistui(ruokintaTiedot.isTaimiMaistui());
            paivitettyRuokinta.setLempiMaistui(ruokintaTiedot.isLempiMaistui());
            return Optional.of(ruokintarepo.save(paivitettyRuokinta));
        } else {
            return Optional.empty();
        }
    }

    public boolean poistaRuokinta(Long ruokintaId) {
        if (ruokintarepo.existsById(ruokintaId)) {
            ruokintarepo.deleteById(ruokintaId);
            return true;
        } else {
            return false;
        }
    }
}