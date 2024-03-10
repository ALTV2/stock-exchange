//package com.tveritin.utils;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.time.Duration;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class JwtTokenUtil {
//    @Value("$jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.ttl}")
//    private Duration ttl;
//
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .toList();
//        claims.put("roles", roles);
//        Date issuedDate = new Date();
//        Date exparedDate = new Date(issuedDate.getTime() + ttl.toMillis());
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(issuedDate)
//                .setExpiration(exparedDate)
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact();
//    }
//
//    public String getUsername(String token) {
//        return getAllClaims(token).getSubject();
//    }
//
//    public List<String> getRoles(String token) {
//        return getAllClaims(token).get("roles", List.class);
//    }
//
//    private Claims getAllClaims(String token) {
//        return Jwts.parser()
//                .setSigningKey(secret)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//}
