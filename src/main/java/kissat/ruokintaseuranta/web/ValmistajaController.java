package kissat.ruokintaseuranta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.List;

import kissat.ruokintaseuranta.domain.Valmistaja;
import kissat.ruokintaseuranta.service.ValmistajaService;

@Controller
@RequestMapping("/valmistajat")
public class ValmistajaController {

@Autowired
private ValmistajaService valmistajaService;

@GetMapping
public String haeKaikkiValmistajat(Model model) {
    List<Valmistaja> valmistajat = valmistajaService.haeKaikkiValmistajat();
    model.addAttribute("valmistajat", valmistajat);
    model.addAttribute("valmistaja", new Valmistaja());
    return "valmistajatLista";
}

// Lisää uusi valmistaja
@PostMapping("/lisaa")
public String lisaaValmistaja(@ModelAttribute Valmistaja uusiValmistaja) {
    valmistajaService.uusiValmistaja(uusiValmistaja);
    return "redirect:/valmistajat";
}

// Hae yksittäinen valmistaja ID:n perusteella
@GetMapping("/valmistajat/{id}")
public ResponseEntity<Valmistaja> haeValmistajaId(@PathVariable("id") Long valmistajaId) {
    Optional<Valmistaja> valmistaja = valmistajaService.haeValmistajaId(valmistajaId);
    return valmistaja.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}

// Näytä muokkauslomake
@GetMapping("/muokkaa/{id}")
public String naytaMuokkausLomake(@PathVariable("id") Long valmistajaId, Model model) {
    Optional<Valmistaja> valmistaja = valmistajaService.haeValmistajaId(valmistajaId);
    if (valmistaja.isPresent()) {
        model.addAttribute("muokattavaValmistaja", valmistaja.get());
        return "valmistajatMuokkaa";
    } else {
        return "redirect:/valmistajat";
    }
}
//muokkaa valmistajaa
@PatchMapping("/muokkaa/{id}")
public String paivitaValmistaja(@PathVariable("id") Long valmistajaId, @ModelAttribute Valmistaja muokattavaValmistaja) {
    muokattavaValmistaja.setValmistajaId(valmistajaId);
    valmistajaService.paivitaValmistaja(valmistajaId, muokattavaValmistaja);
    return "redirect:/valmistajat";
}


// Poista valmistaja
@DeleteMapping("/poista/{id}")
public String poistaValmistaja(@PathVariable("id") Long valmistajaId, Model model) {
    if (valmistajaService.poistaValmistaja(valmistajaId)) {
        return "redirect:/valmistajat";
    } else {
        model.addAttribute("errorMessage", "Valmistajaa ei löydy");
        return "valmistajatLista";
    }
}


}
