package com.e9pay.repository;

import com.e9pay.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface LoginRepository extends JpaRepository<AdminUser, String> {


}
