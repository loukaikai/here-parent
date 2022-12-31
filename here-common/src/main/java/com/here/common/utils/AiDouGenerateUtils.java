package com.here.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName AiDouGenerateUtils.java
 * @Description 邀请码生成的工具类
 * @createTime 2022年12月26日 19:35:00
 */
public class AiDouGenerateUtils {
    /**
     * 自定义进制（选择你想要的进制数，不能重复且最好不要0、1这些容易混淆的字符）
     */
  //  private static final char[] r = new char[]{'w', 'y', 'e', '3', 'h', 'u', '7', 'z', 'k', '9', 'c', 'f', 'g', '5', 'x', '8', 'm', 'j', '2', 'd', 'r', '4', 'v', 'q', 't', 'n', '6', 'b', 'p', 's'};
    private static final char[] r = new char[]{ '3', '7', '9','5','8', '2', '4', '6'};

    /**
     * 定义一个字符用来补全邀请码长度（该字符前面是计算出来的邀请码，后面是用来补全用的）
     */
   // private static final char b = 'a';
    private static final char b = '0';

    /**
     * 进制长度
     */
    private static final int binLen = r.length;

    /**
     * 邀请码长度
     */
    private static final int s = 6;


    private static final String PREFIX = "";

    /**
     * 根据ID生成随机码
     *
     * @param id ID
     * @return 随机码
     */
    public static String toSerialCode(long id, String prefix) {
        String result = toSerialCode(id);
        if (StringUtils.isBlank(prefix)) {
            result = PREFIX + result;
        } else {
            result = prefix + result;
        }
        return result;
    }


    /**
     * 根据ID生成随机码
     *
     * @param id ID
     * @return 随机码
     */
    public static String toSerialCode(long id) {
        char[] buf = new char[32];
        int charPos = 32;

        while ((id / binLen) > 0) {
            int ind = (int) (id % binLen);
            buf[--charPos] = r[ind];
            id /= binLen;
        }
        buf[--charPos] = r[(int) (id % binLen)];
        String str = new String(buf, charPos, (32 - charPos));
        // 不够长度的自动随机补全
        if (str.length() < s) {
            StringBuilder sb = new StringBuilder();
            sb.append(b);
            Random rnd = new Random();
            for (int i = 1; i < s - str.length(); i++) {
                sb.append(r[rnd.nextInt(binLen)]);
            }
            str += sb.toString();
        }
        return str;
    }

    /**
     * 根据随机码生成ID
     *
     * @param code 随机码
     * @return ID
     */
    public static long codeToId(String code) {
        char chs[] = code.toCharArray();
        long res = 0L;
        for (int i = 0; i < chs.length; i++) {
            int ind = 0;
            for (int j = 0; j < binLen; j++) {
                if (chs[i] == r[j]) {
                    ind = j;
                    break;
                }
            }
            if (chs[i] == b) {
                break;
            }
            if (i > 0) {
                res = res * binLen + ind;
            } else {
                res = ind;
            }
        }
        return res;
    }

    public static String toUpperCaseSerialCode(long id) {
        String code = toSerialCode(id);
        return StringUtils.upperCase(code);
    }


    public static void main(String[] args) {
        String code = toSerialCode(111);
        System.out.println(code);

        long id = codeToId(code);
        System.out.println(id);
    }
}
