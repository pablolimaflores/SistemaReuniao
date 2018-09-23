package br.com.projeto.reuniao.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
    @Column(name="nome", nullable = false, length = 150)
    private String nome;
	
	/**
	 * 
	 */
    @Email(message = "Favor informar um e-mail válido.")
    @NotBlank
    @Column(name="email", nullable = false, length = 150, unique = true)
    private String email;	
    
    /**
	 * 
	 */	
    @Column(name="telefone", length = 20)
    private String telefone;
    
    /**
	 * 
	 */	
    @Column(name="celular", length = 20)
    private String celular;
    
}
