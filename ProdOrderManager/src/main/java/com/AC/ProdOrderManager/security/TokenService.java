package com.AC.ProdOrderManager.security;

import com.AC.ProdOrderManager.models.user.UserModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserModel user) {
         try {
             Algorithm algorithm = Algorithm.HMAC256(secret);

             return JWT.create()
                     .withIssuer("ProdOrderManager-Application")
                     .withSubject(user.getLogin())
                     .withExpiresAt(genereteExpirationDate())
                     .sign(algorithm);
         }
         catch (JWTCreationException exception) {
             throw new RuntimeException("Erro durante a autenticação");
         }
     }

     public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("ProdOrderManager-Application")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException exception){
            return null;
        }
     }

     public Instant genereteExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
     }
}
