package com.proyekt.user.jwt;

import com.proyekt.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    @Autowired
    private UserDetailsService userDetailsService;       //(6.0)

    @Value("${jwt.access.secret.key}")
    private String accessSecretKey;

    @Value("${jwt.refresh.secret.key}")
    private String refreshSecretKey;

//---------------------------------ACCESS TOKEN--------------------------------------


    public String generateToken(UserDetails userDetails){              //tokien yaradilir         (1.0)  accessToken
          return Jwts.builder()
                .subject(userDetails.getUsername())   // istifadecinin adi (User) username       (1.2)
                .issuedAt(new Date())  // tokienin yaranma vaxti(indi)                           (1.3)
                .expiration(new Date(System.currentTimeMillis() + 1000*60*30))  // bitme vaxti   (1.4)
                .signWith(getkey(), SignatureAlgorithm.HS256)   // Token imzalanması:1. getkey() - bizim gizli açarımız (SECRET_KEY)2. HS256 - HMAC SHA-256 şifrələmə alqoritmi Bu sayədə token dəyişdirildimi yoxlamaq olur (1.5)
                .compact();
    }





    public <T> T exportToken(String token, Function<Claims,T> claimsTFunction ){  // burada tokenler cozulur (4.0 ve 5.0)(3.0)

        Claims claims = Jwts.parser()                                 // JWT parser yaradırıq
                .verifyWith((SecretKey) getkey())                    //Token-in imzasını secret key ilə yoxlayırıq
                .build()                                            //Parser-i hazır vəziyyətə gətiririk
                .parseSignedClaims(token).getPayload();            // Signed JWT-ni parse edirik. Payload = Claims (username, role, exp və s.)

        return claimsTFunction.apply(claims);                     // Claims-dən lazım olan məlumatı çıxarırıq
    }





    public Key getkey(){   // Gizli açarı (SECRET_KEY) hazırlayırıq: (2.0)

        // SECRET_KEY string-i byte massivə çeviririk (BASE64 dekodlayırıq)  (2.1)
      byte[] keyBytes  = Decoders.BASE64.decode(accessSecretKey);

        // Byte massivdən HMAC-SHA üçün hazır Key yaradırıq (2.2)
      return Keys.hmacShaKeyFor(keyBytes);  // bunu 1.5 e gonderirik
    }



    public String getUserNameByToken(String token){ // Token-dən username çıxarmaq üçündür. (4.0)

        return exportToken(token,Claims::getSubject);   // exportToken token-i parse edir (imza yoxlanır, payload alınır)
                                                       // Claims::getSubject → Claims içindəki "subject" sahəsini götürür (1.2 deki )
                                                      //JWT-də subject adətən username-dir. Token-dəki username qaytarılır (exportToken (3.0) methodunda cozulur )

    }





    public boolean isTokenExpired(String token){  // burada vaxti yoxluyuruq (5.0)

        Date expairedDate =  exportToken(token, Claims::getExpiration);  // (5.1) (exportToken (3.0) methodunda cozulur )

        return new Date().after(expairedDate);  // vaxt kecmiyibse true kecibse false qaytarir
    }



    // tokenin tipini oyrenirik
    public String getTokenType(String token){

        return exportToken(token,claims -> claims.get("tokenType",String.class) );
    }


    // -------------------------- REFRESH TOKEN -----------------------------------




    public String getUserNameByTokenRefresh(String token){ // Token-dən username çıxarmaq üçündür. (4.0)

        return exportRefreshToken(token,Claims::getSubject);   // exportToken token-i parse edir (imza yoxlanır, payload alınır)
        // Claims::getSubject → Claims içindəki "subject" sahəsini götürür (1.2 deki )
        //JWT-də subject adətən username-dir. Token-dəki username qaytarılır (exportToken (3.0) methodunda cozulur )

    }




    public String refreshTokenGenered(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24*7)) // 7 gün
                .claim("tokenType", "REFRESH")  // ✅ BUNU ƏLAVƏ EDİN
                .signWith(getkeyRefresh(), SignatureAlgorithm.HS256)
                .compact();
    }





    public Key getkeyRefresh(){

        byte[] keyBytes = Decoders.BASE64.decode(refreshSecretKey);

        return Keys.hmacShaKeyFor(keyBytes);


    }





    public <T> T exportRefreshToken(String token, Function<Claims, T> claimsTFunction) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith((SecretKey) getkeyRefresh()) // ⚠️ REFRESH KEY
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return claimsTFunction.apply(claims);
        } catch (Exception e) {
            System.out.println("Refresh token parse error: " + e.getMessage());
            throw new RuntimeException("Refresh token parse edilə bilmədi");
        }
    }




    // ⚠️ Refresh token üçün

    //--------------------- REFESH TOKEN YOXALYIR TIPINI---------------------------
    public String getRefreshTokenType(String token){

        return exportRefreshToken(token,claims -> claims.get("tokenType",String.class) );
    }




    public boolean isRefreshToken(String token){

        try {
            String tokenType = getRefreshTokenType(token);

            return "REFRESH".equals(tokenType);

        } catch (Exception e) {
           return false;
        }
    }




    public boolean isTokenRefreshExpired(String token){  // (REFRESH) burada vaxti yoxluyuruq (5.0)

        Date expairedDate =  exportRefreshToken(token, Claims::getExpiration);  // (5.1) (exportToken (3.0) methodunda cozulur )

        return new Date().after(expairedDate);  // vaxt kecmiyibse true kecibse false qaytarir
    }
//-----------------------------------------------------------------------------------

}



