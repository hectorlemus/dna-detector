package dev.demo.dnadetector.contoller;

import dev.demo.dnadetector.entity.Stats;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/")
public class StatController {

    @GetMapping(path = "/stats")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity<Stats> getStats() {
        try {
            Stats stats = new Stats();
            return new ResponseEntity<>(stats, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
