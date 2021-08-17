package com.pzy.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @Author Nice
 * @Date 2021/7/1 17:56
 * 随机生成验证码
 */

public class CodeGenerateUtil {
    private static final String SYMBOLS = "0123456789";
    private static final Random RANDOM = new SecureRandom();

    /**
     * 生成6位随机数字
     * @return 返回6位数字验证码
     */
    public static String generateVerCode() {
        char[] nonceChars = new char[6];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }
}
