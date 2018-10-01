package br.com.projeto.reuniao.domain.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Usuario extends AbstractEntity implements UserDetails, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5477437220949869085L;
	
	
	/**
	 * 
	 */
	@NotBlank(message ="O campo Login é de preenchimento obrigatório.")
    @Column(nullable = false, length = 50)
	@Size(min=3, max=50)
    private String login;

	/**
     * 
     */
    @JsonProperty(access = Access.WRITE_ONLY)
//    @NotBlank
    @Column(nullable = false, length = 50)
    @Size(min=3, max=256)
    private String senha;
    
    /**
     * 
     */
    @Column
    private Boolean admin;
    
    /**
     * 
     */
//    @NotNull
    @OneToOne(fetch = FetchType.EAGER/*, optional = false*/)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;        

	
    /**
	 * 
	 * @param id
	 * @param login
	 * @param senha
	 * @param admin
	 * @param pessoa
	 */
    public Usuario(Long id, String login, Boolean admin, Pessoa pessoa) {
		super(id);
		this.login = login;		
		this.admin = admin;
		this.pessoa = pessoa;
	}
    
    /**
	 * 
	 * @param login
	 * @param senha
	 * @param admin
	 * @param pessoa
	 */
    public Usuario(String login, String senha, Boolean admin, Pessoa pessoa) {		
		this.login = login;
		this.senha = senha;
		this.admin = admin;
		this.pessoa = pessoa;
	}
    
    /**
	 * 
	 * @param id
	 * @param login
	 * @param senha
	 * @param admin
	 * @param pessoa
	 */
    public Usuario(Long id, String login, String senha, Boolean admin, Pessoa pessoa) {
		super(id);
		this.login = login;
		this.senha = senha;
		this.admin = admin;
		this.pessoa = pessoa;
	}
    
    /**
     * 
     * @param id
     * @param ativo
     * @param login
     * @param senha
     * @param admin
     * @param pessoa
     */
    public Usuario(Long id, Boolean ativo, String login, String senha, Boolean admin, Pessoa pessoa) {
		super(id, ativo);
		this.login = login;
		this.senha = senha;
		this.admin = admin;
		this.pessoa = pessoa;
	}
    
    /**
     * 
     * @return
     */
    public Perfil getPerfil() {
    	if (this.admin) {
    		return Perfil.ADMINISTRADOR; 
    	} else {
    		return Perfil.USUARIO;
    	}
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		final Set<GrantedAuthority> authorities = new HashSet<>();        
		
        authorities.add(Perfil.ADMINISTRADOR);
        authorities.add(Perfil.USUARIO);
        
        return authorities;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {		
		return this.login;
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
