package dev.demo.dnadetector.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class Human {

    @JsonIgnore
    private Integer id;

    private UUID uuid;

    private String name;

    private String[] dna;

    private Boolean mutant;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    public Boolean getMutant() {
        return mutant;
    }

    public void setMutant(Boolean mutant) {
        this.mutant = mutant;
    }
}
