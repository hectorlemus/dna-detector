package dev.demo.dnadetector.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {

    @JsonProperty("count_mutations")
    private Integer countMutations;

    @JsonProperty("count_no_mutations")
    private Integer countNoMutations;

    private Integer ratio;

    public Integer getCountMutations() {
        return countMutations;
    }

    public void setCountMutations(Integer countMutations) {
        this.countMutations = countMutations;
    }

    public Integer getCountNoMutations() {
        return countNoMutations;
    }

    public void setCountNoMutations(Integer countNoMutations) {
        this.countNoMutations = countNoMutations;
    }

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }
}
