package dev.demo.dnadetector.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class DNA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @Column(unique=true)
    @NotNull
    private String code;

    @NotNull
    private Boolean mutation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getMutation() {
        return mutation;
    }

    public void setMutation(Boolean mutation) {
        this.mutation = mutation;
    }
}
