package com.e9pay.repository;

import com.e9pay.entity.Dealing;
import com.e9pay.entity.OutboundRemittance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealingRepository extends JpaRepository<Dealing, String> {
}
