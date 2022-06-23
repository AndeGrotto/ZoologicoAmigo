package br.upf.ZoologicoAmigo.utils;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import br.upf.ZoologicoAmigo.jwt.TokenJwt;

public class Utils {

	public static Key gerarChave() {
		/* 
		 * Senha criptografada: "ZoologicoAmigo"
		 * SHA256: bd1b5a448e835023be4a57b01f58e11887ea3b227564b8d1101b715718d1d370
		 */
		String keyString = "YmQxYjVhNDQ4ZTgzNTAyM2JlNGE1N2IwMWY1OGUxMTg4N2VhM2IyMjc1NjRiOGQxMTAxYjcxNTcxOGQxZDM3MA==";
		Key key = new SecretKeySpec(keyString.getBytes(),
				0, keyString.getBytes().length, "HmacSHA256");
		return key;
	}
	
	public static Date definirDataExpiracao(long tempoEmMinutos) {
		LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(tempoEmMinutos);
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static String processarTokenJWT(String usuario) {
		Key chave = Utils.gerarChave();
		TokenJwt token = new TokenJwt(chave);
		Date dataExpiracao = Utils.definirDataExpiracao(42000L);
		return token.gerarToken(usuario, dataExpiracao);
	}
	
}
