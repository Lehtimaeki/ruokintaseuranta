package kissat.ruokintaseuranta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Map;

import kissat.ruokintaseuranta.domain.Ruoka;
import kissat.ruokintaseuranta.domain.Ruokinta;
import kissat.ruokintaseuranta.domain.Ateria;
import kissat.ruokintaseuranta.service.RuokintaService;
import kissat.ruokintaseuranta.service.RuokaService;
import kissat.ruokintaseuranta.service.AteriaService;

@Controller
@RequestMapping("/")
public class RuokintaController {

    @Autowired
    private RuokintaService ruokintaService;

    @Autowired
    private RuokaService ruokaService;

    @Autowired
    private AteriaService ateriaService;

    @GetMapping
    public String haeKaikkiRuokinnat(@AuthenticationPrincipal User user, Model model) {
        List<Ruokinta> ruokinnat = ruokintaService.haeKaikkiRuokinnat();
        Map<LocalDate, List<Ruokinta>> ruokinnatPaivamaaranMukaan = ruokinnat.stream()
            .collect(Collectors.groupingBy(Ruokinta::getRuokintaAika));
        
        model.addAttribute("ruokinnatPaivamaaranMukaan", ruokinnatPaivamaaranMukaan);
        model.addAttribute("ruokinta", new Ruokinta(LocalDate.now(), new Ateria(), new Ruoka(), false, false)); // Initialize nested objects
        model.addAttribute("ateriat", ateriaService.haeKaikkiAteriat());
        model.addAttribute("ruoat", ruokaService.haeKaikkiRuoat());
        
        if (user != null) {
            return "index-kirjautunut";
        } else {
            return "index";
        }
    }

    @GetMapping("lisaa")
    public String naytaLomake(Model model) {
        model.addAttribute("ruokinta", new Ruokinta());
        model.addAttribute("ateriat", ateriaService.haeKaikkiAteriat());
        model.addAttribute("ruoat", ruokaService.haeKaikkiRuoat());
        return "lisaaRuokinta";
    }

    @PostMapping("lisaa")
    public String lisaaRuokinta(@ModelAttribute Ruokinta uusiRuokinta, Model model) {
        try {
            ruokintaService.uusiRuokinta(uusiRuokinta);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("ruokinta", uusiRuokinta);
            model.addAttribute("ateriat", ateriaService.haeKaikkiAteriat());
            model.addAttribute("ruoat", ruokaService.haeKaikkiRuoat());

            

            return "lisaaRuokinta";
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Ruokinta> haeRuokintaId(@PathVariable("id") Long ruokintaId) {
        Optional<Ruokinta> ruokinta = ruokintaService.haeRuokintaId(ruokintaId);
        return ruokinta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> poistaRuokinta(@PathVariable("id") Long ruokintaId) {
        if (ruokintaService.poistaRuokinta(ruokintaId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("muokkaa/{id}")
    public String naytaMuokkausLomake(@PathVariable("id") Long ruokintaId, Model model) {
        Optional<Ruokinta> ruokinta = ruokintaService.haeRuokintaId(ruokintaId);
        if (ruokinta.isPresent()) {
            model.addAttribute("ruokinta", ruokinta.get());
            model.addAttribute("ateriat", ateriaService.haeKaikkiAteriat());
            model.addAttribute("ruoat", ruokaService.haeKaikkiRuoat());
            return "ruokkinnatMuokkaa"; 
        } else {
            return "redirect:/"; // Redirect to the root URL if not found
        }
    }

    @PatchMapping("muokkaa/{id}")
    public String paivitaRuokinta(@PathVariable("id") Long ruokintaId, @ModelAttribute Ruokinta paivitettyRuokinta, Model model) {
        try {
            ruokintaService.paivitaRuokinta(ruokintaId, paivitettyRuokinta);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("ruokinta", paivitettyRuokinta);
            model.addAttribute("ateriat", ateriaService.haeKaikkiAteriat());
            model.addAttribute("ruoat", ruokaService.haeKaikkiRuoat());
            return "ruokkinnatMuokkaa";
        }
    }
    
}