package com.example.meu_primeiro_springboot.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

/**
 * Utilitário para geração, extração e validação de tokens JWT.
 * Usa HS256 para assinatura e expiração de 10 dias.
 * Chamado por AuthController para gerar tokens e por JwtAuthFilter para validar.
 */
public class JwtUtil {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 864000000;

    /**
     * Gera um token JWT para o username fornecido.
     * Define o subject como username, expiração em 10 dias e assina com HS256.
     */
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extrai o username do token JWT.
     * Faz o parse do token e retorna o subject.
     */
    public static String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Valida se o token JWT é válido.
     * Tenta fazer o parse; retorna true se bem-sucedido, false se houver exceção.
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }
        catch (JwtException e) {
            return false;
        }
    }
}
