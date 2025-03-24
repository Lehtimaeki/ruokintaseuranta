package kissat.ruokintaseuranta.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import kissat.ruokintaseuranta.domain.Ruoka;
import kissat.ruokintaseuranta.domain.Ruokinta;
import kissat.ruokintaseuranta.service.RuokintaService;
import kissat.ruokintaseuranta.service.RuokaService;
import kissat.ruokintaseuranta.service.AteriaService;

@Controller
@RequestMapping("/ruokinnat")
public class RuokintaController {

    @Autowired
    private RuokintaService ruokintaService;

    @Autowired
    private RuokaService ruokaService;

    @Autowired
    private AteriaService ateriaService;

    @GetMapping
    public String haeKaikkiRuokinnat(Model model) {
        List<Ruokinta> ruokinnat = ruokintaService.haeKaikkiRuokinnat();
        model.addAttribute("ruokinnat", ruokinnat);
        model.addAttribute("ruokinta", new Ruokinta());
        model.addAttribute("ateriat", ateriaService.haeKaikkiAteriat());
        model.addAttribute("ruoat", ruokaService.haeKaikkiRuoat());
        return "ruokinnatLista";
    }

    @GetMapping("/lisaa")
    public String naytaLomake(Model model) {
        model.addAttribute("ruokinta", new Ruokinta());
        model.addAttribute("ateriat", ateriaService.haeKaikkiAteriat());
        model.addAttribute("ruoat", ruokaService.haeKaikkiRuoat());
        return "lisaaRuokinta";
    }

    @PostMapping("/lisaa")
    public String lisaaRuokinta(@ModelAttribute Ruokinta uusiRuokinta, Model model) {
        if (uusiRuokinta.getRuoka() == null) {
            throw new RuntimeException("Ruokaa ei löydy");
        }
        Optional<Ruoka> ruoka = ruokaService.haeRuokaId(uusiRuokinta.getRuoka().getRuokaId());
        if (ruoka.isPresent()) {
            uusiRuokinta.setRuoka(ruoka.get());
        } else {
            throw new RuntimeException("Ruokaa ei löydy");
        }
        ruokintaService.uusiRuokinta(uusiRuokinta);
        return "redirect:/ruokinnat";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ruokinta> haeRuokintaId(@PathVariable("id") Long ruokintaId) {
        Optional<Ruokinta> ruokinta = ruokintaService.haeRuokintaId(ruokintaId);
        return ruokinta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> poistaRuokinta(@PathVariable("id") Long ruokintaId) {
        if (ruokintaService.poistaRuokinta(ruokintaId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/muokkaa/{id}")
    public String naytaMuokkausLomake(@PathVariable("id") Long ruokintaId, Model model) {
        Optional<Ruokinta> ruokinta = ruokintaService.haeRuokintaId(ruokintaId);
        if (ruokinta.isPresent()) {
            model.addAttribute("ruokinta", ruokinta.get());
            model.addAttribute("ateriat", ateriaService.haeKaikkiAteriat());
            model.addAttribute("ruoat", ruokaService.haeKaikkiRuoat());
            return "ruokkinnatMuokkaa";
        } else {
            return "redirect:/ruokinnat";
        }
    }

    @PatchMapping("/muokkaa/{id}")
    public String paivitaRuokinta(@PathVariable("id") Long ruokintaId, @ModelAttribute Ruokinta paivitettyRuokinta) {
        Optional<Ruokinta> olemassaOlevaRuokinta = ruokintaService.haeRuokintaId(ruokintaId);
        if (olemassaOlevaRuokinta.isPresent()) {
            Ruokinta ruokinta = olemassaOlevaRuokinta.get();
            ruokinta.setRuokintaAika(paivitettyRuokinta.getRuokintaAika());
            ruokinta.setAteria(paivitettyRuokinta.getAteria());
            ruokinta.setRuoka(paivitettyRuokinta.getRuoka());
            ruokinta.setTaimiMaistui(paivitettyRuokinta.isTaimiMaistui());
            ruokinta.setLempiMaistui(paivitettyRuokinta.isLempiMaistui());
            ruokintaService.uusiRuokinta(ruokinta);
        }
        return "redirect:/ruokinnat";
    }
}