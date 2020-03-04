package dev.demo.dnadetector.contoller;

import dev.demo.dnadetector.entity.DNA;
import dev.demo.dnadetector.entity.Human;
import dev.demo.dnadetector.repository.DNARepository;
import dev.demo.dnadetector.repository.HumanRepository;
import dev.demo.dnadetector.service.DNAService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/")
public class HumanController {

    private final DNAService dnaService;
    private final HumanRepository humanRepository;
    private final DNARepository dnaRepository;

    public HumanController(
        final HumanRepository humanRepository,
        final DNAService dnaService,
        final DNARepository dnaRepository
    ) {
        this.humanRepository = humanRepository;
        this.dnaService = dnaService;
        this.dnaRepository = dnaRepository;
    }

    @PostMapping(path = "/human")
    public @ResponseBody ResponseEntity<Human> createHuman (@RequestBody Human human) {
        try {

            DNA dna = dnaService.createRandomDNA();
            DNA dnaDB = dnaRepository.save(dna);

            human.setDna(dnaDB);
            Human humanDB = humanRepository.save(human);

            return new ResponseEntity<>(humanDB, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/mutant-humans")
    public @ResponseBody
    Iterable<Human> getMutantHumans() {
        return getHumans(true);
    }

    @GetMapping(path = "/non-mutant-humans")
    public @ResponseBody Iterable<Human> getNonMutantHumans() {
        return getHumans(false);
    }

    private Iterable<Human> getHumans(final Boolean mutants) {
        try {
            return humanRepository.findByDnaMutation(mutants);
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

}
