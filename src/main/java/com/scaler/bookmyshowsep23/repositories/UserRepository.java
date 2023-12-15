package com.scaler.bookmyshowsep23.repositories;

import com.scaler.bookmyshowsep23.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
