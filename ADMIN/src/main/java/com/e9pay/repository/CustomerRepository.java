package com.e9pay.repository;

import com.e9pay.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Meta(comment = "findByFirstName")
    public Customer findByFirstName(String jongHyun);
}
