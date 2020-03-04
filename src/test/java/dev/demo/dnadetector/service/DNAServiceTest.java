package dev.demo.dnadetector.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DNAServiceTest {

    @Autowired
    private DNAService service;

    @Test
    void createDNA() {
        String[] dna = service.createDNA();
        Assert.assertNotNull(dna);
    }

    @Test
    void checkDNALetters() {
        String[] dna = service.createDNA();
        String regex = "[A|T|C|G]{4}";
        for (String chain: dna) {
            Assert.assertTrue(chain.matches(regex));
        }
    }

    @Test
    void hasMutation() {
        String[] dna = service.createDNA();
        Boolean hasMutation = service.hasMutation(dna);
        Assert.assertNotNull(hasMutation);

    }

}