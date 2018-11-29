package br.com.projeto.reuniao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe que representa os participantes que serão cadastradas no sistema e seus atributos.
 */
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
    @Column
    private Boolean compareceu;
	
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
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Reuniao reuniao;		
	
    /**
	 * 
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)	
	private TipoParticipante tipoParticipante;

	/**
	 * @param id
	 * @param compareceu
	 * @param pessoa
	 * @param reuniao
	 * @param tipoParticipante
	 */
	public Participante(Long id, Boolean compareceu, @NotNull Pessoa pessoa, @NotNull Reuniao reuniao,
			@NotNull TipoParticipante tipoParticipante) {
		super(id);
		this.compareceu = compareceu;
		this.pessoa = pessoa;
		this.reuniao = reuniao;
		this.tipoParticipante = tipoParticipante;
	}
	
	/**
	 * 	 
	 * @param compareceu
	 * @param pessoa
	 * @param reuniao
	 * @param tipoParticipante
	 */
	public Participante(Boolean compareceu, @NotNull Pessoa pessoa, @NotNull Reuniao reuniao,
			@NotNull TipoParticipante tipoParticipante) {		
		this.compareceu = compareceu;
		this.pessoa = pessoa;
		this.reuniao = reuniao;
		this.tipoParticipante = tipoParticipante;
	}

}
