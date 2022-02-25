package com.cb.ex.repo;

import java.util.UUID;

import org.springframework.data.couchbase.repository.Collection;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.data.couchbase.repository.Scope;
import org.springframework.stereotype.Repository;

import com.cb.ex.entity.Employee;

@Repository
@Scope("_default")
@Collection("reactive_collection")
public interface EmployeeRepository extends ReactiveCouchbaseRepository<Employee, Integer>{


}
