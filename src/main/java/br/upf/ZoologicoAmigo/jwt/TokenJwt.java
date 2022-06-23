package br.upf.ZoologicoAmigo.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.upf.ZoologicoAmigo.utils.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class TokenJwt {

	private static Key chave;
	private String jwt;
	
	public TokenJwt(Key chave) {
		this.chave = chave;
	}
	
    public static boolean validarToken(String token) {
        chave = Utils.gerarChave();
        boolean tokenValido = false;
        if (token != null) {
            try {
                Jwts.parser().setSigningKey(chave).parseClaimsJws(token);
                tokenValido = true;
            } catch (ExpiredJwtException e) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Token inválido!", e);
            } catch (MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
            	throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token inválido!", e);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cabeçalho de autenticação não foi encontrado!");
        }
        return tokenValido;
    }
    
    public String gerarToken(String nomeUsuario, Date dataExpiracao) {
    	jwt = Jwts.builder()
    			.setHeaderParam("typ", "JWT")
    			.setSubject(nomeUsuario)
    			.setIssuer("ZoologicoAmigo")
    			.setIssuedAt(new Date())
    			.claim("password", "admin")
    			.setExpiration(dataExpiracao)
    			.signWith(SignatureAlgorithm.HS256, chave)
    			.compact();
    	return jwt;
    }
    
    public String recuperarSubjectDoToken(String token) {
    	Jws<Claims> claimsJws = Jwts.parser().setSigningKey(chave).parseClaimsJws(token);
    	return claimsJws.getBody().getSubject();
    }
    
    public String recuperarIssuerDoToken(String token) {
    	Jws<Claims> claimsJws = Jwts.parser().setSigningKey(chave).parseClaimsJws(token);
    	return claimsJws.getBody().getIssuer();
    }
    
    public String recuperarClaim(String token, String claim) {
    	String subject = "";
    	if(token != null && !token.equals("")) {
    		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(chave).parseClaimsJws(token);
    		subject = claimsJws.getBody().get(claim).toString();
    	}
    	return subject;
    }
	
}
