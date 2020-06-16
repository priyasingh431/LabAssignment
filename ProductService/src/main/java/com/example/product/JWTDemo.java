package com.example.product;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import io.jsonwebtoken.*;

import java.util.Date;

public class JWTDemo {
	private static String SECRET_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh+7tSDGhqPuIaWhhSFHdMwym7DnTZroivlY3R3puluCcOZpFUURtFJ2EcywdGFccJ0DRXVCrFmm8IlUkhzizen+Nt9H3K02yMd16oQrJqIypLRbFLfspvLHfx02RCpGbxtTVeOthijv/hKW3o1Pgc7bAH3iwtUD6DipiIRX1LywB3eQBBKjE62UrPcaWq0tQZt96U/fGBGrD54ZuS9NkSR9zz2G58Xm5iuU9Ql4TSntzpRwjPhBjtYJt/6O7H8OteCy7MYjDpXtVwrEdkbfCMWRSHtPANzyFDq6fFpUcB8sVhHbEAAUflA6Nk1quvAwA36pHcyAc1FBNNTLyF3uWeQIDAQAB";

	// Sample method to construct a JWT
	public static String createJWT(String id, String issuer, String subject, long ttlMillis) {

		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);

		// if it has been specified, let's add the expiration
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

	public static Claims decodeJWT(String jwt) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, UnrecoverableKeyException, IllegalArgumentException, KeyStoreException, NoSuchAlgorithmException, CertificateException, URISyntaxException, IOException {

		// This line will throw an exception if it is not a signed JWS (as expected)
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(getKey())).parseClaimsJws(jwt)
				.getBody();
		return claims;
	}

	public static void main(String arg[]) {
		String token = createJWT("123", "me", "test", 10000);
		System.out.println(token);
		
		try {
			decodeJWT("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib2F1dGgyX2FwcGxpY2F0aW9uIl0sInVzZXJfbmFtZSI6InVzZXIiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNTkxNjUxOTcyLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiZTY1ZWNiMTUtNmI1NC00MjliLWE1YzQtZjFjNDEyMDBlNDYwIiwiY2xpZW50X2lkIjoidHJ1c3RlZC1hcHAifQ.s-ginv9PSxzHwDibQPU_Rwg8iOgKGcZEZLXYPRwVxIU");
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
				| UnrecoverableKeyException | IllegalArgumentException | KeyStoreException | NoSuchAlgorithmException
				| CertificateException | URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getKey() throws URISyntaxException, KeyStoreException, IOException, NoSuchAlgorithmException,
			CertificateException, UnrecoverableKeyException {
		return new String(getKeyPair().getPublic().getEncoded(), "UTF-8");
	}

	private static KeyPair getKeyPair() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException,
			UnrecoverableKeyException {
		FileInputStream is = new FileInputStream("D:\\AccessmentWorkspace\\ProductService\\src\\main\\resources\\mykeys.jks");

		KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		keystore.load(is, "mypass".toCharArray());

		String alias = "mykeys";

		Key key = keystore.getKey(alias, "mypass".toCharArray());
		if (key instanceof PrivateKey) {
			// Get certificate of public key
			Certificate cert = keystore.getCertificate(alias);

			// Get public key
			PublicKey publicKey = cert.getPublicKey();

			// Return a key pair
			return new KeyPair(publicKey, (PrivateKey) key);
		} else
			throw new UnrecoverableKeyException();
	}
}