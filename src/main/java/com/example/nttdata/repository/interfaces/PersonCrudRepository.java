package com.example.nttdata.repository.interfaces;

import com.example.nttdata.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersonCrudRepository<T extends Person> extends JpaRepository<T, Integer> {
}
