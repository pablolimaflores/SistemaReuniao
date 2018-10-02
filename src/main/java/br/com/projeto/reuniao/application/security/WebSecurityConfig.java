package br.com.projeto.reuniao.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.projeto.reuniao.domain.service.UsuarioService; 
 
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired
	private UsuarioService usuarioRepositoryImpl;
 
	/**
	 * REALIZA AS CONFIGURAÇÕES DE ACESSO
	 * */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
 
 
		http.authorizeRequests()
			/*DETERMINA QUE PARA REALIZAR ESSA REQUEST PRECISA TER UMA DAS PERMISSÕES ABAIXO
		 	* EXEMPLO DA URL: http://localhost:8095/usuarios/usuariosEdit		 	
		 	* QUANDO USAMOS o hasRole*/
//			.antMatchers("/usuarios/usuariosEdit").access("hasRole('ADMINISTRADOR')")
			/*DETERMINA QUE PARA REALIZAR ESSA REQUEST PRECISA TER UMA DAS PERMISSÕES ABAIXO
			 * EXEMPLO DA URL: http://localhost:8090/usuarios/usuariosList */
//			.antMatchers("/usuarios/usuariosList").access("hasRole('ADMINISTRADOR')")
			 /*DETERMINA QUE PARA ACESSAR A PÁGINA INICIAL DA APLICAÇÃO PRECISA ESTÁ AUTENTICADO*/
			.antMatchers("/index").authenticated()
//			.antMatchers("/index").permitAll()
			.anyRequest().authenticated()			
			.and()			
				.formLogin()
				 /*INFORMANDO O CAMINHO DA PÁGINA DE LOGIN, E SE O LOGIN FOR EFETUADO COM SUCESSO
				  *O USUÁRIO DEVE SER REDIRECIONADO PARA /home(http://localhost:8090/home)*/
				.loginPage("/").defaultSuccessUrl("/index",true)
				.permitAll() /*AQUI ESTAMOS INFORMANDO QUE TODOS TEM ACESSO A PÁGINA DE LOGIN*/
			.and()
			     /*AQUI ESTAMOS INFORMANDO QUE QUANDO FOR REDIRECIONADO PARA  O LINK http://localhost:8090/logout
			      *O USUÁRIO DEVE TER SUA SESSÃO FINALIZADA E REDIRECIONADO PARA A PÁGINA DE LOGIN */
				.logout()
				.logoutSuccessUrl("/")
				.logoutUrl("/logout") 
				.permitAll();
 
 
		/*PÁGINA COM A MENSAGEM DE ACESSO NEGADO
		 *QUANDO O USUÁRIO NÃO TER UMA DETERMINADA PERMISSÃO DE ACESSO AO SISTEMA ELE VAI SER REDIRECIONADO
		 *PARA A URL ABAIXO */
		http.exceptionHandling().accessDeniedPage("/acessoNegado");
 
		/*AQUI ESTOU INFORMANDO QUE QUALQUER REQUEST TEM ACESSO AO DIRETÓRIO src/main/resources */
		http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll();
 
	}
 
 
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
 
        /*INFORMA A CRIPTOGRAFIA QUE DEVE SER USADA PARA A SENHA DO USUÁRIO*/				
		auth.userDetailsService(usuarioRepositoryImpl).passwordEncoder(new BCryptPasswordEncoder());
 
    }
	
	/*
	 * CRIPTOGRAFANDO A SENHA PARA TESTE
	public static void main(String[] args) {
 
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}
	*/
}
