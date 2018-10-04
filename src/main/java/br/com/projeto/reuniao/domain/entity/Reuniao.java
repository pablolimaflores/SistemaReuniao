package br.com.projeto.reuniao.domain.entity;

import java.time.LocalDate;
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

import org.springframework.format.annotation.DateTimeFormat;

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
    @Column(nullable = false, length = 50)
	@Size(min=3, max=50)
    private String titulo;

	/**
	 * 
	 */
//	@NotNull
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate data;
	
	/**
	 * 
	 */
	@Column(length=300) 
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
	@Column(name="pre_requisito", length=300) 
	private String preRequisito;
	
	/**
	 * 
	 */	
	@Column(length=300)
	@Size(max=300)
	private String objetivo;
		
	/**
	 * 
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY/*, optional = false*/)
	private Tipo tipo;
	
	/**
	 * 
	 */
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY, mappedBy = "reuniao")
	private List<Participante> participantes;

	/**
	 * 
	 * @param id
	 * @param titulo
	 * @param data
	 * @param local
	 * @param horaInicio
	 * @param horaFim
	 * @param preRequisito
	 * @param objetivo
	 * @param tipo
	 */
	public Reuniao(Long id, String titulo, LocalDate data, String local, LocalTime horaInicio, LocalTime horaFim, 
			String preRequisito, String objetivo, Tipo tipo) {
		super(id);
		this.titulo = titulo;
		this.data = data;
		this.local = local;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.preRequisito = preRequisito;
		this.objetivo = objetivo;
		this.tipo = tipo;
	}
	
	/**
	 * 
	 * @param titulo
	 * @param data
	 * @param local
	 * @param horaInicio
	 * @param horaFim
	 * @param preRequisito
	 * @param objetivo
	 * @param tipo
	 */
	public Reuniao(String titulo, LocalDate data, String local, LocalTime horaInicio, LocalTime horaFim, 
			String preRequisito, String objetivo, Tipo tipo) {		
		this.titulo = titulo;
		this.data = data;
		this.local = local;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.preRequisito = preRequisito;
		this.objetivo = objetivo;
		this.tipo = tipo;
	}
	
	
}
