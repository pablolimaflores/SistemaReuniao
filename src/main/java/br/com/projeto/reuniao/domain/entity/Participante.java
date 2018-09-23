package br.com.projeto.reuniao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Participante extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 390286427487319138L;
	
	/**
	 * 
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Pessoa pessoa;
	
	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TipoParticipante tipoParticipante;

}
