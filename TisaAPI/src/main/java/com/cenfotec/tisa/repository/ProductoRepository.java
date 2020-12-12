package com.cenfotec.tisa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cenfotec.tisa.model.Producto;

@Repository
public interface ProductoRepository  extends JpaRepository<Producto, Long> { }
