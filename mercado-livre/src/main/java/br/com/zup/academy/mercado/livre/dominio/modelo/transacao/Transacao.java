package br.com.zup.academy.mercado.livre.dominio.modelo.transacao;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zup.academy.mercado.livre.dominio.modelo.compra.Compra;

@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private Long idTransacao;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusTransacao status;

	@CreationTimestamp
	private LocalDateTime instante;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Compra compra;

	@Deprecated
	public Transacao() {
	}

	public Transacao(StatusTransacao status, Long idTransacao, Compra compra) {
		this.status = status;
		this.idTransacao = idTransacao;
		this.compra = compra;
//		compra.addTransacao(this);
	}

	public StatusTransacao getStatus() {
		return status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compra == null) ? 0 : compra.hashCode());
		result = prime * result + ((idTransacao == null) ? 0 : idTransacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		if (compra == null) {
			if (other.compra != null)
				return false;
		} else if (!compra.equals(other.compra))
			return false;
		if (idTransacao == null) {
			if (other.idTransacao != null)
				return false;
		} else if (!idTransacao.equals(other.idTransacao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transacao [idTransacao=" + idTransacao + ", compra=" + compra + "]";
	}

}