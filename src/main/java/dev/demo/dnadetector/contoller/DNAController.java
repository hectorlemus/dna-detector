package dev.demo.dnadetector.contoller;

import dev.demo.dnadetector.service.DNAService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/")
public class DNAController {

    private final DNAService dnaService;

    public DNAController(DNAService dnaService) {
        this.dnaService = dnaService;
    }

    @PostMapping(path = "/mutation")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<Boolean> requestHasMutation(@RequestBody final String [] dna) {
        try {
            Boolean hasMutation = dnaService.hasMutation(dna);
            HttpStatus httpStatus = hasMutation ? HttpStatus.OK : HttpStatus.FORBIDDEN;
            return new ResponseEntity<>(httpStatus);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/dna")
    public @ResponseBody ResponseEntity<String[]> createDNA() {
        String[] dna = dnaService.createDNA();
        return new ResponseEntity(dna, HttpStatus.OK);
    }

}
