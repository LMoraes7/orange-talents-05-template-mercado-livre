package br.com.zup.academy.mercado.livre.dominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.academy.mercado.livre.dominio.modelo.produto.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
