package com.meals.security.utils;

import com.meals.security.entity.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 * 继承OncePerRequestFilter只执行一次
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-11-25
 */
@Component
public class JwtUtils {
    //用户ID
    private final String ID = "id";
    //密钥
    @Value("${jwt.secret}")
    private String tokenSecret;
    //前端头部
    @Value("${jwt.header}")
    private String tokenHeader;
    //起始字串
    @Value("${jwt.tokenHead}")
    private String authTokenStart;

    /**
     * 获取请求token
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (StringUtils.isNotEmpty(token)) {
            token = token.substring(authTokenStart.length());
        }
        return token;
    }

    /**
     * 从令牌中获取数据声明
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 从令牌TOKEN中获取用户ID
     * @param token 令牌
     * @return 用户ID
     */
    public Long getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        if(claims!=null)
        {
            Object id = claims.get(ID);
            return id == null ? null : Long.valueOf(id.toString());
        }
        return null;
    }

    /**
     * 根据请求获取一个仅有ID的用户对象
     * @return 用户名
     */
    public JwtUser getJwtUserFromRequest(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            Long userId = getUserIdFromToken(token);
            if (userId == null) {
                return null;
            }
            JwtUser jwtUser = new JwtUser();
            jwtUser.setId(userId);
            return jwtUser;
        }
        return null;
    }

    /**
     * JWT数据声明
     * @return 令牌
     */
    public String generateToken(Long userId) {
        Map<String, Object> claims = new HashMap<>(1);
        claims.put(ID, userId);
        return generateToken(claims);
    }

    /**
     * 从数据声明生成令牌
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, tokenSecret).compact();
    }
}
