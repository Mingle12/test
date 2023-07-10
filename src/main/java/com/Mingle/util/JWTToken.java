package com.Mingle.util;

import com.Mingle.person.Admin;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

/**
 * @author anding
 * @description
 */
public class JWTToken {
    private static Logger log = LoggerFactory.getLogger(JWTToken.class);

    /**
     * 生成token
     * @return
     */
    public static String createToken(Admin admin){
        /**
         * 第一步：构建头部信息
         */
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        /**
         * 第二步：构建密钥信息
         */
        Algorithm algorithm = Algorithm.HMAC256("secret");
        /**
         * 第三步：我们通过定义注册和自定义声明 并组合头部信息和密钥信息生成jwt token
         */
        Date nowDate = new Date();
        /**2小过期*/
        Date expireDate = getAfterDate(nowDate, 0, 0, 0, 2, 0, 0);

        String token = JWT.create()
                /**设置头部信息 Header */
                .withHeader(map)
                /**设置 载荷 Payload*/
                .withClaim("id",admin.getId())
                .withClaim("username",admin.getUsername())
                .withClaim("State",admin.getStatus())
                /**设置 载荷 签名是有谁生成 例如 服务器*/
                .withIssuer("sendticket")
                /**设置 载荷 签名的主题*/
                .withSubject("Mingle")
                /**设置 载荷 定义在什么时间之前，该jwt都是不可用的.*/
                // .withNotBefore(new Date())
                /**设置 载荷 签名的观众 也可以理解谁接受签名的*/
                .withAudience("APP")
                /**设置 载荷 生成签名的时间*/
                .withIssuedAt(nowDate)
                /**设置 载荷 签名过期的时间*/
                .withExpiresAt(expireDate)
                /**签名 Signature*/
                .sign(algorithm);

        log.info("创建token："+token);
        return token;
    }

    /**
     * 解析token
     */
    public static Admin verifyToken(String token){
        Admin admin=new Admin();
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256("secret")).withIssuer("sendticket").build();
            DecodedJWT jwt = verifier.verify(token);

            String subject = jwt.getSubject();
            List<String> audience = jwt.getAudience();
            Map<String, Claim> claims = jwt.getClaims();
            if(!"Mingle".equals(subject)){
                return null;
            }
            if(!"APP".equals(audience.get(0))){
                return null;
            }

            String username = claims.get("username").asString();
            Integer id = claims.get("id").asInt();
            Integer state = claims.get("State").asInt();

            if (id==0|| StringUtils.isBlank(username)||StringUtils.isBlank(state.toString())){
                return null;
            }
            admin.setId(id);
            admin.setUsername(username);
            admin.setStatus(state);
        }catch (Exception e){
            log.info("token错误！");
            throw new JWTDecodeException("token错误！");
        }
        return admin;
    }

    /**
     * 返回一定时间后的日期
     * @param date 开始计时的时间
     * @param year 增加的年
     * @param month 增加的月
     * @param day 增加的日
     * @param hour 增加的小时
     * @param minute 增加的分钟
     * @param second 增加的秒
     * @return
     */
    public static Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second){
        if(date == null){
            date = new Date();
        }

        Calendar cal = new GregorianCalendar();

        cal.setTime(date);
        if(year != 0){
            cal.add(Calendar.YEAR, year);
        }
        if(month != 0){
            cal.add(Calendar.MONTH, month);
        }
        if(day != 0){
            cal.add(Calendar.DATE, day);
        }
        if(hour != 0){
            cal.add(Calendar.HOUR_OF_DAY, hour);
        }
        if(minute != 0){
            cal.add(Calendar.MINUTE, minute);
        }
        if(second != 0){
            cal.add(Calendar.SECOND, second);
        }
        return cal.getTime();
    }
}
