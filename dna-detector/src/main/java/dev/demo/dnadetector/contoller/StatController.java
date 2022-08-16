package dev.demo.dnadetector.contoller;

import dev.demo.dnadetector.entity.DNA;
import dev.demo.dnadetector.entity.Stats;
import dev.demo.dnadetector.repository.DNARepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(path = "api/")
public class StatController {

    private final DNARepository dnaRepository;

    public StatController(final DNARepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    @GetMapping(path = "/stats")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<Stats> getStats() {
        try {
            Stats stats = calculateStatistics();
            return new ResponseEntity<>(stats, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    private Stats calculateStatistics() {
        Integer mutants = getIterableSize(dnaRepository.findByMutation(true));
        Integer noMutant = getIterableSize(dnaRepository.findByMutation(false));
        Double ratio = ( (double) mutants ) / (noMutant == 0 ? 1 : noMutant);
        return new Stats(mutants, noMutant, ratio);
    }

    private Integer getIterableSize(final Iterable<DNA> iterable) {
        AtomicInteger size = new AtomicInteger();
        iterable.forEach( (element) -> size.getAndIncrement());
        return size.get();
    }
}
