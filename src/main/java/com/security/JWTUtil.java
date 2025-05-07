package com.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
	private static final String SECRET_KEY_STRING = "40425e9350e7002e6185ed1fb81a1d7d69d44596254c8ade3001eb10017401e6a05abe4b310e4f5d5d24e2b8de42772ab0f62e62ea5da4783ae92f525597a51f81f33cac0116bbd6136fa66c0dc11d49a99c2ccce37960f207e0df20e1ec8689b89ca83576326d071bc29303d4c1e782c289bed32b9ec0b5b681d94308c18e6bf232745c3f48065a23201e5649b4d4149d55167685c21516048715e1f4546cde3d878d239871fef2124182cbe810db1605f6217e15cbafed590ac0f12b70a74ba204ea5b429d37c07a46405c381a7f5997f83e902a3315b0ffb10994a6848cff9544763a5d5a68c93cbb669a4af4cf0cb0645683bb7f5389dc374d32da9da90d";
	private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());
	

	public String generateToken(UserDetails userDetails) {
		return Jwts.builder()
				.subject(userDetails.getUsername())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+1000 * 60 * 60))
				.signWith(SECRET_KEY, Jwts.SIG.HS256)
				.compact()
				;
	}
	public boolean validateToken(String token, UserDetails userDetails) {
		return extractUsername(token).equals(userDetails.getUsername());
	}
	public String extractUsername(String token) {
		return Jwts.parser()
				.verifyWith(SECRET_KEY)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}

}
