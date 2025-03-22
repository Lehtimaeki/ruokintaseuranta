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

import kissat.ruokintaseuranta.domain.Valmistaja;
import kissat.ruokintaseuranta.service.ValmistajaService;

@Controller
public class ValmistajaController {

@Autowired
private ValmistajaService valmistajaService;

@GetMapping("/valmistajat")
public ResponseEntity<List<Valmistaja>> haeKaikkiValmistajat() {
    List<Valmistaja> valmistajat = valmistajaService.haeKaikkiValmistajat();
    return ResponseEntity.ok(valmistajat);
}

// Lisää uusi valmistaja
@PostMapping("/valmistajat")
public ResponseEntity<Valmistaja> uusiValmistaja(@RequestBody Valmistaja uusiValmistaja) {
    Valmistaja tallennettuValmistaja = valmistajaService.uusiValmistaja(uusiValmistaja);
    return ResponseEntity.status(HttpStatus.CREATED).body(tallennettuValmistaja);
}

// Hae yksittäinen valmistaja ID:n perusteella
@GetMapping("/valmistajat/{id}")
public ResponseEntity<Valmistaja> haeValmistajaId(@PathVariable("id") Long valmistajaId) {
    Optional<Valmistaja> valmistaja = valmistajaService.haeValmistajaId(valmistajaId);
    return valmistaja.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}

// Päivitä valmistaja
@PutMapping("/valmistajat/{id}")
public ResponseEntity<Valmistaja> paivitaValmistaja(@PathVariable("id") Long valmistajaId, @RequestBody Valmistaja valmistajaTiedot) {
    Optional<Valmistaja> valmistaja = valmistajaService.paivitaValmistaja(valmistajaId, valmistajaTiedot);
    return valmistaja.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}

// Poista valmistaja
@DeleteMapping("/valmistajat/{id}")
public ResponseEntity<Void> poistaValmistaja(@PathVariable("id") Long valmistajaId) {
    if (valmistajaService.poistaValmistaja(valmistajaId)) {
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}

}
