package com.dewen.ldap.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiUtil {

    public static String filter2(String nickName) {

        String regEx = "/[\\x{10000}-\\x{10FFFF}]/u";
        Pattern emoji = Pattern.compile(regEx, Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher emojiMatcher = emoji.matcher(nickName);
        if (emojiMatcher.find()) {
            return "";
        }
        return nickName;
    }

    /**
     * 检测是否有emoji字符
     * 
     * @param source 需要判断的字符串
     * @return 一旦含有就抛出
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!notisEmojiCharacter(codePoint)) {
                // 判断确认有表情字符
                return true;
            }
        }
        return false;
    }

    /**
     * 非emoji表情字符判断
     * 
     * @param codePoint
     * @return
     */
    private static boolean notisEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     * 
     * @param source 需要过滤的字符串
     * @return
     */
    public static String filter(String source) {
        if (!containsEmoji(source)) {
            return source;// 如果不包含，直接返回
        }

        StringBuilder buf = null;// 该buf保存非emoji的字符
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (notisEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            }
        }

        if (buf == null) {
            return "";// 如果没有找到非emoji的字符，则返回无内容的字符串
        } else {
            if (buf.length() == len) {
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
    }
}
