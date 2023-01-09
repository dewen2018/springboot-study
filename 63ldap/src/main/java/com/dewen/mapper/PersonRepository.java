package com.dewen.mapper;

import com.dewen.entity.Person;
import org.springframework.data.repository.CrudRepository;

import javax.naming.Name;

public interface PersonRepository extends CrudRepository<Person, Name> {


}