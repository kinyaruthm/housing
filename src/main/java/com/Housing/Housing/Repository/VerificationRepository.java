package com.Housing.Housing.Repository;

import com.Housing.Housing.Model.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository <Verification,Long> {

    Verification findByCode(String code);
}
