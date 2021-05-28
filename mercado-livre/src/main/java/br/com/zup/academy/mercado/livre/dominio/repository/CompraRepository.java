package br.com.zup.academy.mercado.livre.dominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.academy.mercado.livre.dominio.modelo.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long>{

}
