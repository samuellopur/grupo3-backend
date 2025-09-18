package com.ecommerce.rutamtb.repository;

import com.ecommerce.rutamtb.model.Imag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImagRepository extends JpaRepository<Imag, Long> {
}
