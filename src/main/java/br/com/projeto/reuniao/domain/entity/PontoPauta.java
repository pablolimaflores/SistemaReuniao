package br.com.projeto.reuniao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PontoPauta extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2236429717045358981L;
	
	/**
	 * 
	 */
	@Column
	private String descricao;
	
	/**
	 * 
	 */
	@OneToOne
	private Pessoa responsavel;
	
	/**
	 * 
	 */
	@ManyToOne
	private Tipo tipo;
	
}
