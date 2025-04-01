package kissat.ruokintaseuranta.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import kissat.ruokintaseuranta.domain.Ateria;
import kissat.ruokintaseuranta.domain.Ruoka;
import kissat.ruokintaseuranta.domain.Ruokinta;
import kissat.ruokintaseuranta.domain.RuokintaRepository;

@Service
public class RuokintaService {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private RuokintaRepository ruokintarepo;

    //Hae kaikki ruokinnat
    public List<Ruokinta> haeKaikkiRuokinnat() {
        Iterable<Ruokinta> ruokinnat = ruokintarepo.findAll();
        return StreamSupport.stream(ruokinnat.spliterator(), false).collect(Collectors.toList());
    }

    //Luo uusi ruokinta
    @Transactional
    public Ruokinta uusiRuokinta(Ruokinta uusiRuokinta) {
        Ateria ateria = uusiRuokinta.getAteria();
        if (ateria != null && ateria.getAteriaId() != null) {
            Ateria loydettyAteria = entityManager.find(Ateria.class, ateria.getAteriaId());
            uusiRuokinta.setAteria(loydettyAteria);
        } else {
            throw new IllegalArgumentException("AteriaId must not be null");
        }
        Ruoka ruoka = uusiRuokinta.getRuoka();
        if (ruoka != null && ruoka.getRuokaId() != null) {
            Ruoka loydettyRuoka = entityManager.find(Ruoka.class, ruoka.getRuokaId());
            uusiRuokinta.setRuoka(loydettyRuoka);
        } else {
            throw new IllegalArgumentException("AteriaId must not be null");
        }
        uusiRuokinta.setTaimiMaistui(false);
        uusiRuokinta.setLempiMaistui(false);
        uusiRuokinta.setRuokintaAika(uusiRuokinta.getRuokintaAika());

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

            if (ruokintaTiedot.getAteria() != null && ruokintaTiedot.getAteria().getAteriaId() != null) {
                paivitettyRuokinta.setAteria(ruokintaTiedot.getAteria());
            } else {
                throw new IllegalArgumentException("AteriaId must not be null");
            }

            if (ruokintaTiedot.getRuoka() != null && ruokintaTiedot.getRuoka().getRuokaId() != null) {
                paivitettyRuokinta.setRuoka(ruokintaTiedot.getRuoka());
            } else {
                throw new IllegalArgumentException("RuokaId must not be null");
            }

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