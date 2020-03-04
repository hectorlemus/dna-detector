package dev.demo.dnadetector.contoller;

import dev.demo.dnadetector.entity.DNA;
import dev.demo.dnadetector.entity.DNARequest;
import dev.demo.dnadetector.repository.DNARepository;
import dev.demo.dnadetector.service.DNAService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/")
public class DNAController {

    private final DNAService dnaService;
    private final DNARepository dnaRepository;

    public DNAController(final DNAService dnaService, final DNARepository dnaRepository) {
        this.dnaService = dnaService;
        this.dnaRepository = dnaRepository;
    }

    @PostMapping(path = "/mutation")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<Boolean> requestHasMutation(@RequestBody final DNARequest request) {
        try {
            DNA dna = dnaService.getDNA(request.getDna());
            saveDNA(dna);

            HttpStatus httpStatus = dna.getMutation() ? HttpStatus.OK : HttpStatus.FORBIDDEN;
            return new ResponseEntity<>(httpStatus);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void saveDNA(final DNA dna) {
        try {
            dnaRepository.save(dna);
        } catch (Exception ex) {}
    }

}
