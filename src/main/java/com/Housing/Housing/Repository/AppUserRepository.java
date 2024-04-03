package com.Housing.Housing.Repository;

import com.Housing.Housing.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUserName(String UserName);

}
