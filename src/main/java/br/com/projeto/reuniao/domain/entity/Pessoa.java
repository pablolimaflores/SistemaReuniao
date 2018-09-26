package br.com.projeto.reuniao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Pessoa extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6017386540403784565L;

	/**
	 * 
	 */	
	@NotBlank
    @Column(nullable = false, length = 100)
    @Size(min=3, max=100)
	private String nome;
	
	/**
	 * 
	 */
    @Email(message = "Favor informar um e-mail válido.")
    @NotBlank
    @Column(nullable = false, length = 100, unique = true)
    private String email;	
    
    /**
	 * 
	 */	
    @Column(length = 20)
    @Size(min=8, max=20)
    private String telefone;
    
    /**
	 * 
	 */	
    @Column(length = 20)
    @Size(min=8, max=20)
    private String celular;

    /**
     *  
     * @param nome
     * @param email
     */
	public Pessoa(String nome, String email) {		
		this.nome = nome;
		this.email = email;
	}
	
	/**
	 *  
	 * @param nome
	 * @param email
	 * @param telefone
	 * @param celular
	 */
	public Pessoa(String nome, String email, String telefone, String celular) {		
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.celular = celular;
	}
	
    /**
     * 
     * @param id
     * @param nome
     * @param email
     * @param telefone
     * @param celular
     */
	public Pessoa(Long id, String nome, String email, String telefone, String celular) {
		super(id);
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.celular = celular;
	}
    
}
