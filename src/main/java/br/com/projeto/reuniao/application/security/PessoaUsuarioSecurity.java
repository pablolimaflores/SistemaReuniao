package br.com.projeto.reuniao.application.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class PessoaUsuarioSecurity extends User {

	private static final long serialVersionUID = 1L;
	 
	public PessoaUsuarioSecurity(String login, String senha, Boolean ativo, Collection<? extends GrantedAuthority> authorities) {		
		super(login, senha, ativo,	true, true,true, authorities);
	}
	
}
