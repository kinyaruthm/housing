package com.Housing.Housing.Repository;

import com.Housing.Housing.Databind.Enums.UserRole;
import com.Housing.Housing.Model.AppUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUserName(String UserName);
    AppUser findByEmailAddress(String email);


    @org.springframework.transaction.annotation.Transactional
    @Modifying
    @Query("update AppUser a set a.role = ?1 where a.emailAddress = ?2")
    int updateRole(UserRole role, String emailAddress);

    @Query("update AppUser  a set a.enabled=?1 where a.emailAddress= ?2")
    int updateEnabled(boolean enabled, String emailAddress);


}
