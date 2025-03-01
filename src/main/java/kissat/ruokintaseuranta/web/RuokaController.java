package kissat.ruokintaseuranta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Optional;

import kissat.ruokintaseuranta.domain.Ruoka;
import kissat.ruokintaseuranta.domain.RuokaRepository;


@RestController
public class RuokaController {

@Autowired
private RuokaRepository ruokarepo;

//Lisää uusi ruoka
@PostMapping("/ruoat")
public ResponseEntity<Ruoka> uusiRuoka(@RequestBody Ruoka uusiRuoka) {
    Ruoka tallennettuRuoka = ruokarepo.save(uusiRuoka);
    return ResponseEntity.status(HttpStatus.CREATED).body(tallennettuRuoka);
}

//Hae kaikki ruoat
@GetMapping("/ruoat")
public List<Ruoka> haeKaikkiRuoat() {
    Iterable<Ruoka> ruoat = ruokarepo.findAll();
    return StreamSupport.stream(ruoat.spliterator(), 
    false).collect(Collectors.toList());
}

// Hae yksittäinen ruoka ID:n perusteella
@GetMapping("/ruoat/{id}")
public ResponseEntity<Ruoka> haeRuokaId(@PathVariable("id") Long ruokaId) {
        Optional<Ruoka> ruoka = ruokarepo.findById(ruokaId);
        return ruoka.map(ResponseEntity::ok).orElseGet(() -> 
        ResponseEntity.notFound().build());
}

// Päivitä ruoka
@PutMapping("/ruoat/{id}")
public ResponseEntity<Ruoka> paivitaRuoka(@PathVariable("id") Long ruokaId, @RequestBody Ruoka ruokaTiedot) {
    Optional<Ruoka> ruoka = ruokarepo.findById(ruokaId);
    if (ruoka.isPresent()) {
        Ruoka paivitettyRuoka = ruoka.get();
        paivitettyRuoka.setRuokaNimi(ruokaTiedot.getRuokaNimi());
        paivitettyRuoka.setValmistaja(ruokaTiedot.getValmistaja());
        paivitettyRuoka.setRaakaaineet(ruokaTiedot.getRaakaaineet());
        ruokarepo.save(paivitettyRuoka);
        return ResponseEntity.ok(paivitettyRuoka);
        } else {
        return ResponseEntity.notFound().build();
        }
}

// Poista ruoka
@DeleteMapping("/ruoat/{id}")
public ResponseEntity<Void> poistaRuoka(@PathVariable("id") Long ruokaId) {
    if (ruokarepo.existsById(ruokaId)) {
        ruokarepo.deleteById(ruokaId);
        return ResponseEntity.noContent().build();
        } else {
        return ResponseEntity.notFound().build();
        }
}


}
