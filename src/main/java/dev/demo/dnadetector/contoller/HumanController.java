package dev.demo.dnadetector.contoller;

import dev.demo.dnadetector.entity.Human;
import dev.demo.dnadetector.service.HumanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/")
public class HumanController {

    private final HumanService humanService;

    public HumanController(HumanService humanService) {
        this.humanService = humanService;
    }

    @PostMapping(path = "/human")
    public @ResponseBody ResponseEntity<Human> createHuman (@RequestBody Human human) {
        try {
            humanService.validateMutation(human);
            return new ResponseEntity<>(human, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/mutant-humans")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    Iterable<Human> getMutantHumans() {
        try {
            return humanService.getHumans(true);
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    @GetMapping(path = "/non-mutant-humans")
    @CrossOrigin(origins = "*")
    public @ResponseBody Iterable<Human> getNonMutantHumans() {
        try {
            return humanService.getHumans(false);
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

}
