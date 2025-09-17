package com.ecommerce.rutamtb.repository;

import com.ecommerce.rutamtb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

}
