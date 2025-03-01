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
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Optional;


import kissat.ruokintaseuranta.domain.Ruokinta;
import kissat.ruokintaseuranta.domain.RuokintaRepository;


@Controller
public class RuokintaController {

@Autowired
private RuokintaRepository ruokintarepo;

@GetMapping("/ruokinnat")
public String haeKaikkiRuokinnat(Model model) {
    Iterable<Ruokinta> ruokinnat = ruokintarepo.findAll();
    List<Ruokinta> ruokintaList = StreamSupport.stream(ruokinnat.spliterator(), false).collect(Collectors.toList());
    model.addAttribute("ruokinnat", ruokintaList);
    return "ruokinnat";
 }

//Lisää uusi Ruokinta
@PostMapping("/ruokinnat")
public ResponseEntity<Ruokinta> uusiRuokinta(@RequestBody Ruokinta uusiRuokinta) {
    Ruokinta tallennettuRuokinta = ruokintarepo.save(uusiRuokinta);
    return ResponseEntity.status(HttpStatus.CREATED).body(tallennettuRuokinta);
}

// Hae yksittäinen ruokinta ID:n perusteella
@GetMapping("/ruokinnat/{id}")
public ResponseEntity<Ruokinta> haeRuokintaId(@PathVariable("id") Long ruokintaId) {
        Optional<Ruokinta> ruokinta = ruokintarepo.findById(ruokintaId);
        return ruokinta.map(ResponseEntity::ok).orElseGet(() -> 
        ResponseEntity.notFound().build());
}

// Päivitä ruokinta
@PutMapping("/ruokinnat/{id}")
public ResponseEntity<Ruokinta> paivitaRuokinta(@PathVariable("id") Long ruokintaId, @RequestBody Ruokinta ruokintaTiedot) {
    Optional<Ruokinta> ruokinta = ruokintarepo.findById(ruokintaId);
    if (ruokinta.isPresent()) {
        Ruokinta paivitettyRuokinta = ruokinta.get();
        paivitettyRuokinta.setRuokintaAika(ruokintaTiedot.getRuokintaAika());
        paivitettyRuokinta.setTaimiMaistui(ruokintaTiedot.isTaimiMaistui());
        paivitettyRuokinta.setLempiMaistui(ruokintaTiedot.isLempiMaistui());
        ruokintarepo.save(paivitettyRuokinta);
        return ResponseEntity.ok(paivitettyRuokinta);
        } else {
        return ResponseEntity.notFound().build();
        }
}

// Poista ruokinta
@DeleteMapping("/ruokinnat/{id}")
public ResponseEntity<Void> poistaRuokinta(@PathVariable("id") Long ruokintaId) {
    if (ruokintarepo.existsById(ruokintaId)) {
        ruokintarepo.deleteById(ruokintaId);
        return ResponseEntity.noContent().build();
        } else {
        return ResponseEntity.notFound().build();
        }
}

}