package br.com.projeto.reuniao.domain.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude="participantes")
public class Reuniao extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8442247510843669491L;
	
	/**
	 * 
	 */	
	@NotBlank
    @Column(nullable = false, length = 20)
	@Size(min=3, max=20)
    private String titulo;

	/**
	 * 
	 */
	@NotNull
	@Column
	private LocalDateTime data;
	
	/**
	 * 
	 */
	@Column(length=100) 
	private String local;
	
	/**
	 * 
	 */
	@Column(name="hora_inicio")
	private LocalTime horaInicio;
	
	/**
	 * 
	 */
	@Column(name="hora_fim")
	private LocalTime horaFim;
	
	/**
	 * 
	 */
	@Column(name="pre_requisito", length=100) 
	private String preRequisito;
	
	/**
	 * 
	 */	
	@Column(length=100)
	@Size(max=100)
	private String objetivo;
		
	/**
	 * 
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Tipo tipo;
	
	/**
	 * 
	 */
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY, mappedBy = "reuniao")
	private List<Participante> participantes;
	
}
