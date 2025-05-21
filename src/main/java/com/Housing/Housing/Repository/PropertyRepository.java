package com.Housing.Housing.Repository;

import com.Housing.Housing.Model.Property;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface PropertyRepository extends JpaRepository
{
    void save(Property properties);
}
