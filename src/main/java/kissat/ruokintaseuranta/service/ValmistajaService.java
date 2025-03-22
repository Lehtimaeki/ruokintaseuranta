package kissat.ruokintaseuranta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import kissat.ruokintaseuranta.domain.Valmistaja;
import kissat.ruokintaseuranta.domain.ValmistajaRepository;

@Service
public class ValmistajaService {

@Autowired
private ValmistajaRepository valmistajaRepo;

public List<Valmistaja> haeKaikkiValmistajat() {
    Iterable<Valmistaja> valmistajat = valmistajaRepo.findAll();
    return StreamSupport.stream(valmistajat.spliterator(), false).collect(Collectors.toList());
}

public Valmistaja uusiValmistaja(Valmistaja uusiValmistaja) {
    return valmistajaRepo.save(uusiValmistaja);
}

public Optional<Valmistaja> haeValmistajaId(Long valmistajaId) {
    return valmistajaRepo.findById(valmistajaId);
}

public Optional<Valmistaja> paivitaValmistaja(Long valmistajaId, Valmistaja valmistajaTiedot) {
    Optional<Valmistaja> valmistaja = valmistajaRepo.findById(valmistajaId);
    if (valmistaja.isPresent()) {
        Valmistaja paivitettyValmistaja = valmistaja.get();
        paivitettyValmistaja.setValmistajaNimi(valmistajaTiedot.getValmistajaNimi());
        return Optional.of(valmistajaRepo.save(paivitettyValmistaja));
    } else {
        return Optional.empty();
    }
}

public boolean poistaValmistaja(Long valmistajaId) {
    if (valmistajaRepo.existsById(valmistajaId)) {
        valmistajaRepo.deleteById(valmistajaId);
        return true;
    } else {
        return false;
    }
}

}
