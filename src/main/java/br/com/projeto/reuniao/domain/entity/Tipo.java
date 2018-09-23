package br.com.projeto.reuniao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Tipo extends AbstractEntity {
			
	/**
	 * 
	 */
	private static final long serialVersionUID = -3701813693477289761L;
	
	/**
	 * 
	 */
	@NotBlank
	@Column(nullable = false, length = 20)
	@Size(min=3, max=20)
	private String nome;
	
	/**
	 * 
	 */
	@Column(length=100)
	@Size(min=3, max=100)
	private String descricao;
	
	/**
     * 
     */
    @Column
    private Boolean debate;
		
}
