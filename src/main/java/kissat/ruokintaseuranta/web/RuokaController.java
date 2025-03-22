package kissat.ruokintaseuranta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import kissat.ruokintaseuranta.domain.Ruoka;
import kissat.ruokintaseuranta.service.RuokaService;


@Controller
public class RuokaController {

@Autowired
private RuokaService ruokaService;

//Hae kaikki ruoat
@GetMapping("/ruoat")
public ResponseEntity<List<Ruoka>> haeKaikkiRuoat() {
    List<Ruoka> ruoat = ruokaService.haeKaikkiRuoat();
    return ResponseEntity.ok(ruoat);
}

//Lisää uusi ruoka
@PostMapping("/ruoat")
public ResponseEntity<Ruoka> uusiRuoka(@RequestBody Ruoka uusiRuoka) {
    Ruoka tallennettuRuoka = ruokaService.tallennaRuoka(uusiRuoka);
    return ResponseEntity.status(HttpStatus.CREATED).body(tallennettuRuoka);
}

// Hae yksittäinen ruoka ID:n perusteella
@GetMapping("/ruoat/{id}")
public ResponseEntity<Ruoka> haeRuokaId(@PathVariable("id") Long ruokaId) {
    Optional<Ruoka> ruoka = ruokaService.haeRuokaId(ruokaId);
    return ruoka.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}

// Päivitä ruoka
@PutMapping("/ruoat/{id}")
public ResponseEntity<Ruoka> paivitaRuoka(@PathVariable("id") Long ruokaId, @RequestBody Ruoka ruokaTiedot) {
    Optional<Ruoka> ruoka = ruokaService.haeRuokaId(ruokaId);
    if (ruoka.isPresent()) {
        Ruoka paivitettyRuoka = ruoka.get();
        paivitettyRuoka.setRuokaNimi(ruokaTiedot.getRuokaNimi());
        paivitettyRuoka.setValmistaja(ruokaTiedot.getValmistaja());
        paivitettyRuoka.setRaakaaineet(ruokaTiedot.getRaakaaineet());
        ruokaService.tallennaRuoka(paivitettyRuoka);
        return ResponseEntity.ok(paivitettyRuoka);
        } else {
        return ResponseEntity.notFound().build();
        }
}

// Poista ruoka
@DeleteMapping("/ruoat/{id}")
public ResponseEntity<Void> poistaRuoka(@PathVariable("id") Long ruokaId) {
    Optional<Ruoka> ruoka = ruokaService.haeRuokaId(ruokaId);
    if (ruoka.isPresent()) {
        ruokaService.poistaRuoka(ruokaId);
        return ResponseEntity.noContent().build();
        } else {
        return ResponseEntity.notFound().build();
        }
}

}
