package dev.demo.dnadetector.service;

import dev.demo.dnadetector.entity.DNA;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;

@Service
public class DNAService {

    private final String DNA_CHARACTERS = "ATCG";
    private final int DNA_CHAIN_SIZE = 4;
    private final int DNA_SIZE = 20;

    public String [] createDNA() {
        return makeDNA(DNA_SIZE, DNA_CHAIN_SIZE);
    }

    public Boolean hasMutation(final String [] dna) {
        Integer mutationSequenceCounter = 0;

        if (dna != null) {
            mutationSequenceCounter += validateHorizontalMode(dna);
            mutationSequenceCounter += validateVerticalMode(dna);
        }

        return mutationSequenceCounter > 1;
    }

    public DNA getDNA(final String[] code) {
        DNA dna = new DNA();
        dna.setCode(Arrays.toString(code));
        dna.setMutation(hasMutation(code));
        return dna;
    }

    public DNA createRandomDNA() {
        String[] code = createDNA();
        return getDNA(code);
    }

    private String[] makeDNA(int size, int chainSize) {
        String[] dna = new String[size];
        for (int i = 0; i < size; i++) {
            dna[i] = makeChain(chainSize);
        }
        return dna;
    }

    private String makeChain(int size) {
        StringBuilder chain = new StringBuilder();
        for (int i = 0; i < size; i++) {
            chain.append(getRandomCharacter());
        }

        return chain.toString();
    }

    private String getRandomCharacter() {
        Random random = new Random();
        int nextInt = random.nextInt(DNA_CHARACTERS.length());
        return String.valueOf(DNA_CHARACTERS.charAt(nextInt));
    }

    private Integer validateHorizontalMode(final String[] dna) {
        Integer mutationSequenceCounter = 0;
        for (String chain: dna) {
            if (hasEqualCharactersInHorizontalMode(chain)) {
                mutationSequenceCounter++;
            }
        }
        return mutationSequenceCounter;
    }

    private Integer validateVerticalMode(final String[] dna) {
        String[] tempDNA = converterVerticalChainToHorizontalMode(dna);
        return validateHorizontalMode(tempDNA);
    }

    private String[] converterVerticalChainToHorizontalMode(final String[] dna) {
        String[] tempDNA = new String[dna[0].length()];
        for (String chain: dna) {
            for (int i = 0; i < chain.length(); i++) {
                String letter = String.valueOf(chain.charAt(i));
                tempDNA[i] = tempDNA[i] == null ? letter : tempDNA[i] + letter;
            }
        }
        return tempDNA;
    }

    private boolean hasEqualCharactersInHorizontalMode(final String chain) {
        String regex = "[A]{4}|[T]{4}|[G]{4}|[C]{4}";
        return chain.matches(regex);
    }

}
