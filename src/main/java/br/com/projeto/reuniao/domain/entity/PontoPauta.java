package br.com.projeto.reuniao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	private static final long serialVersionUID = -2875600846014528656L;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private Integer ordem;	
	
	/**
	 * 
	 */	
	@NotBlank
    @Column(nullable = false, length = 100)
    @Size(min=3, max=100)
	private String descricao;
	
	/**
	 * 
	 */
	@NotNull
	@Column
	private Integer tempo;
	
	@Column(nullable = false, length = 100)
    @Size(min=3, max=100)
	private String discussao;
	
	/**
     * 
     */
    @NotNull
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pessoa_id")
	private Pessoa responsavel;
	
	/**
	 * 
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Tipo tipo;
	
}