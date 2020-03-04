package dev.demo.dnadetector.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique=true)
    private String name;

    @OneToOne()
    @JoinColumn(nullable=false)
    private DNA dna;

    public Human() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DNA getDna() {
        return dna;
    }

    public void setDna(DNA dna) {
        this.dna = dna;
    }

}
