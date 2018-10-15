package br.com.projeto.reuniao.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.projeto.reuniao.domain.service.PessoaService; 
 
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired
	private PessoaService pessoaService;
 
    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
    }	
	
	/**
	 * REALIZA AS CONFIGURAÇÕES DE ACESSO
	 * */
	@Override
	protected void configure(HttpSecurity http) throws Exception { 
 
		http.authorizeRequests()
			.antMatchers("/", "/home", "/about").permitAll()			
			
			.antMatchers("/pessoas/**").hasAuthority("ROLE_ADMIN")
//			.antMatchers("/pessoas/**").access("hasAnyAuthority('ROLE_ADMIN')")
//			.antMatchers("/pessoas/**").hasAnyRole("ADMIN")
//			.antMatchers("/pessoas/**").access("hasRole('ADMIN')")
//			.antMatchers("/tipos/**").access("hasRole('USER')")
//			.antMatchers("/pessoas/**").hasAnyRole("ADMIN")
//			.antMatchers("/tipos/**").hasAnyRole("USER")					
			
			.antMatchers("/index").authenticated()
//			.antMatchers("/pessoas/**").authenticated()
			.antMatchers("/tipos/**").authenticated()			
			.antMatchers("/tiposParticipante/**").authenticated()
			.antMatchers("/participantes/**").authenticated()			
			.antMatchers("/pontosPauta/**").authenticated()			
			.antMatchers("/reunioes/**").authenticated()
			.anyRequest().authenticated()			
			.and()			
				.formLogin()				 
				.loginPage("/").defaultSuccessUrl("/index",true)
				.permitAll()
			.and()			     
				.logout()
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/login")
				.logoutUrl("/logout") 
				.permitAll()					            
	            .and()
	            .rememberMe()
//	            .rememberMe().key("uniqueAndSecret")
//	            .rememberMeParameter("remember-me")
	            ;
 
 
		/*QUANDO O USUÁRIO NÃO TER UMA DETERMINADA PERMISSÃO DE ACESSO AO SISTEMA ELE VAI SER REDIRECIONADO*/
		http.exceptionHandling().accessDeniedPage("/acessoNegado");
 
		/*QUALQUER REQUEST TEM ACESSO AO DIRETÓRIO src/main/resources */
		http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll();
 
	}
 
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
 
        /*INFORMA A CRIPTOGRAFIA QUE DEVE SER USADA PARA A SENHA DO USUÁRIO*/				
		auth.userDetailsService(pessoaService).passwordEncoder(new BCryptPasswordEncoder());
 
    }
}
