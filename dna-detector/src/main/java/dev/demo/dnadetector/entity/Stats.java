package dev.demo.dnadetector.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {

    @JsonProperty("count_mutations")
    private Integer mutants;

    @JsonProperty("count_no_mutations")
    private Integer noMutants;

    private Double ratio;

    public Stats(Integer mutants, Integer noMutants, Double ratio) {
        this.mutants = mutants;
        this.noMutants = noMutants;
        this.ratio = ratio;
    }

    public Integer getMutants() {
        return mutants;
    }

    public void setMutants(Integer mutants) {
        this.mutants = mutants;
    }

    public Integer getNoMutants() {
        return noMutants;
    }

    public void setNoMutants(Integer noMutants) {
        this.noMutants = noMutants;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
}
