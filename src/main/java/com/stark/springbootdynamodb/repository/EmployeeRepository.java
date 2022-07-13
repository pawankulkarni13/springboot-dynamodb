package com.stark.springbootdynamodb.repository;

import com.stark.springbootdynamodb.model.EmployeeInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface EmployeeRepository extends CrudRepository<EmployeeInfo, String> {

    Optional<EmployeeInfo> findById(String id);
}