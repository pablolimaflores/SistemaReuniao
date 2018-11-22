package br.com.projeto.reuniao.domain.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe que representa as pessoas que serão cadastradas no sistema e seus atributos.
 */

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Pessoa extends AbstractEntity implements UserDetails, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6017386540403784565L;

	/**
	 * 
	 */	
	@NotBlank(message ="O campo Nome é de preenchimento obrigatório.")
    @Column(nullable = false, length = 300)
    @Size(min=3, max=100)
	private String nome;
	
	/**
	 * 
	 */
    @Email(message = "Favor informar um e-mail válido.")
    @NotBlank(message ="O campo E-mail é de preenchimento obrigatório.")
    @Column(nullable = false, length = 300, unique = true)
    private String email;	
    
    /**
	 * 
	 */	
    @Column(length = 50)
    @Size(max=50)
    private String telefone;
    
    /**
	 * 
	 */	
    @Column(length = 50)
    @Size(max=50)
    private String celular;
        
    @Column()
	private Boolean usuarioAtivo;
    
    /**
     * 
     */
    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(length = 256)
    @Size(min=3, max=256)
    private String senha;        
    
    /**
     * 
     */
	@Column(nullable = true, length = 20)
	@Enumerated(EnumType.STRING)
    private Role role;    

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
	
	public Pessoa(String nome, String email, String telefone, String celular, Boolean usuarioAtivo, String senha, Role role) {		
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.celular = celular;
		this.usuarioAtivo = usuarioAtivo;
		this.senha = senha;
		this.role = role;
	}
	
	public Pessoa(Long id, String nome, String email, String telefone, String celular, Boolean usuarioAtivo, String senha, Role role) {
		super(id);
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.celular = celular;
		this.usuarioAtivo = usuarioAtivo;
		this.senha = senha;
		this.role = role;
	}
	
	/**
	 * Método para adicionar permissões de acesso a pessoa cadastrada.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		final Set<GrantedAuthority> authorities = new HashSet<>();        
		
        authorities.add(new SimpleGrantedAuthority(Role.ROLE_ADMIN.name()));
        authorities.add(new SimpleGrantedAuthority(Role.ROLE_USER.name()));
        
        return authorities;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {		
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { 
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.getAtivo();
	}
    
}
