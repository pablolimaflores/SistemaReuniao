package br.com.projeto.reuniao.application.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.projeto.reuniao.domain.entity.Pessoa;

public class ContextHolder {
	
    /**
     * 
     * @return
     */
    public static Pessoa getAuthenticatedUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Pessoa) {
            return (Pessoa) authentication.getPrincipal();
        }

        throw new AuthenticationCredentialsNotFoundException("O usuário não está autenticado");
    }

    /**
     * 
     * @return
     */
    public static Pessoa getAuthenticatedUserForRevision() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Pessoa) {
            return (Pessoa) authentication.getPrincipal();
        }

        return new Pessoa();//null user
    }
    
    /**
     * 
     * @return
     */
    public static boolean isAuthenticated() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication != null && authentication.getPrincipal() != null && !authentication.getPrincipal().equals("anonymousUser");
    }
}
