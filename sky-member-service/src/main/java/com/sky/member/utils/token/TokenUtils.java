/******************************************************************
 *
 *    Powered By tianxia-online.
 *
 *    Copyright (c) 2018-2020 Digital Telemedia 天下网络
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.sky.auth.utils.token
 *
 *    Filename:    TokenUtils.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2018-2020
 *
 *    Company:     天下网络科技
 *
 *    @author: Golden
 *
 *    @version: 1.0.0
 *
 *    Create at:   2018年08月31日 17:26
 *
 *    Revision:
 *
 *    2018/8/31 17:26
 *        - first revision
 *
 *****************************************************************/
package com.sky.member.utils.token;


import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;


/**
 * token在1到2小时之间都有效
 */
public class TokenUtils {
    private static final String privateKey = "com.sky.auth";

    public static String getToken(String userId, String date) {
        return Hashing.md5().newHasher().
                putString(userId, Charsets.UTF_8).
                putString(privateKey, Charsets.UTF_8).
                putString(date, Charsets.UTF_8).hash().toString();
    }

    public static String getToken(String userId, Date date) {
        return Hashing.md5().newHasher().
                putString(userId, Charsets.UTF_8).
                putString(privateKey, Charsets.UTF_8).
                putString(getDate(date), Charsets.UTF_8).hash().toString();
    }

    public static String getToken(String userId) {
        return Hashing.md5().newHasher().
                putString(userId, Charsets.UTF_8).
                putString(privateKey, Charsets.UTF_8).putString(getDate(), Charsets.UTF_8).hash().toString();
    }

    /**
     * 2个小时内都校验通过
     *
     * @param token
     * @param userId
     * @return
     */
    public static boolean validToken(String token, String userId) {
        String confirm = getToken(userId);
        String confirmNextHour = getToken(userId, getNextHour());
        if (confirm.equals(token) || confirmNextHour.equals(token)) {
            return true;
        } else {
            return false;
        }
    }

    public static String getDate() {
        Date date = new Date(System.currentTimeMillis());
        return FastDateFormat.getInstance("yyyyMMddHH").format(date);
    }

    public static String getDate(Date now) {

        return FastDateFormat.getInstance("yyyyMMddHH").format(now);
    }

    public static String getNextHour() {
        Date date = new Date(System.currentTimeMillis() + 60 * 60 * 1000);

        return FastDateFormat.getInstance("yyyyMMddHH").format(date);
    }

    public static String getNextHour(Date now) {
        Date date = new Date(now.getTime() + 120* 60 * 1000);

        return FastDateFormat.getInstance("yyyyMMddHH").format(date);
    }

    public static void main(String[] args) {

        Date now = new Date();

        System.out.println("getToken(String userId, String date) : " + getToken("135", "2018083119"));

        System.out.println(" String getToken(String userId) : " + getToken("135"));

        System.out.println("String getDate() : " + getDate());
        System.out.println(" String getDate(Date now) : " + getDate(now));

        System.out.println("getNextHour(Date now) : " + getNextHour(now));
        System.out.println("getNextHour() : " + getNextHour());

        System.out.println("validToken(String token, String userId) : " + validToken("e0db674ae3bfa2e32ec9398eb24ad71f", "135"));

    }
}

