package dev.demo.dnadetector.service;

import dev.demo.dnadetector.entity.Human;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class HumanService {

    private final DNAService dnaService;

    public HumanService(final DNAService dnaService) {
        this.dnaService = dnaService;
    }

    public Iterable<Human> getHumans(final boolean mutants) {
        return new ArrayList<>();
    }

    public void validateMutation (final Human human) {
        Boolean hasMutation = dnaService.hasMutation(human.getDna());
        human.setMutant(hasMutation);
    }

}
