package dev.demo.dnadetector.repository;

import dev.demo.dnadetector.entity.Human;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRepository extends CrudRepository<Human, Integer> {

    Iterable<Human> findByDnaMutation(Boolean mutant);

    @Override
    Iterable<Human> findAll();
}
