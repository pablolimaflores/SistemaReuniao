package br.com.projeto.reuniao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Reuniao extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8442247510843669491L;
	
	/**
	 * 
	 */	
	@NotBlank
    @Column(nullable = false, length = 150)
    private String titulo;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Tipo tipo;
	
}
