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
	@NotBlank(message ="O campo Nome é de preenchimento obrigatório.")
	@Column(nullable = false, length = 50, unique = true)
	@Size(min=3, max=50)
	private String nome;
	
	/**
	 * 
	 */
	@Column(length=300)
	@Size(max=300)
	private String descricao;
	
	/**
     * 
     */
    @Column
    private Boolean debate;

    /**
     *  
     * @param nome
     * @param descricao
     * @param debate
     */
	public Tipo(String nome, String descricao, Boolean debate) {			
		this.nome = nome;
		this.descricao = descricao;
		this.debate = debate;
	}
    
    /**
     * 
     * @param id
     * @param nome
     * @param descricao
     * @param debate
     */
	public Tipo(Long id, String nome, String descricao, Boolean debate) {
		super(id);		
		this.nome = nome;
		this.descricao = descricao;
		this.debate = debate;
	}
        		
}
