package dev.demo.dnadetector.repository;

import dev.demo.dnadetector.entity.DNA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DNARepository extends CrudRepository<DNA, Integer> {

    Iterable<DNA> findByMutation(Boolean mutation);

}
