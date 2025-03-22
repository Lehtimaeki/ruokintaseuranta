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

import java.util.Optional;
import java.util.List;

import kissat.ruokintaseuranta.domain.Ruoka;
import kissat.ruokintaseuranta.domain.Ruokinta;
import kissat.ruokintaseuranta.service.RuokintaService;
import kissat.ruokintaseuranta.service.RuokaService;

@Controller
public class RuokintaController {

@Autowired
private RuokintaService ruokintaService;

@Autowired
private RuokaService ruokaService;

@GetMapping("/ruokinnat")
public ResponseEntity<List<Ruokinta>> haeKaikkiRuokinnat() {
    List<Ruokinta> ruokinnat = ruokintaService.haeKaikkiRuokinnat();
    return ResponseEntity.ok(ruokinnat);
}

// Lisää uusi ruokinta
@PostMapping("/ruokinnat")
public ResponseEntity<Ruokinta> uusiRuokinta(@RequestBody Ruokinta uusiRuokinta) {
    if (uusiRuokinta.getRuoka() == null) {
        throw new RuntimeException("Ruokaa ei löydy");
    }
    Optional<Ruoka> ruoka = ruokaService.haeRuokaId(uusiRuokinta.getRuoka().getRuokaId());
    if (ruoka.isPresent()) {
        uusiRuokinta.setRuoka(ruoka.get());
    } else {
        throw new RuntimeException("Ruokaa ei löydy");
    }
    Ruokinta tallennettuRuokinta = ruokintaService.uusiRuokinta(uusiRuokinta);
    return ResponseEntity.status(HttpStatus.CREATED).body(tallennettuRuokinta);
}

// Hae yksittäinen ruokinta ID:n perusteella
@GetMapping("/ruokinnat/{id}")
public ResponseEntity<Ruokinta> haeRuokintaId(@PathVariable("id") Long ruokintaId) {
    Optional<Ruokinta> ruokinta = ruokintaService.haeRuokintaId(ruokintaId);
    return ruokinta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}

// Päivitä ruokinta
@PutMapping("/ruokinnat/{id}")
public ResponseEntity<Ruokinta> paivitaRuokinta(@PathVariable("id") Long ruokintaId, @RequestBody Ruokinta ruokintaTiedot) {
    Optional<Ruokinta> ruokinta = ruokintaService.paivitaRuokinta(ruokintaId, ruokintaTiedot);
    return ruokinta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}

// Poista ruokinta
@DeleteMapping("/ruokinnat/{id}")
public ResponseEntity<Void> poistaRuokinta(@PathVariable("id") Long ruokintaId) {
    if (ruokintaService.poistaRuokinta(ruokintaId)) {
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}

}