//package com.dewen.ldap.util;
//
//import net.sourceforge.pinyin4j.PinyinHelper;
//import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
//import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
//import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
//
//public class PinyinUtil {
//
//    private static final HanyuPinyinOutputFormat defaultFormat;
//
//    static {
//        defaultFormat = new HanyuPinyinOutputFormat();
//        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//    }
//
//    /**
//     * 获取首字母.
//     *
//     * @param content
//     * @return
//     */
//    public static char getFirstLetter(String content) {
//        char c = content.charAt(0);
//        if (StrUtil.isLetter(c)) {
//            return Character.toUpperCase(c);
//        } else if (NumberUtil.isNumeric(String.valueOf(c))) {
//            return c;
//        }
//        String pinyin = PinyinUtil.getPinyin(c);// 首字母
//        if (pinyin != null) {
//            char firstLetter = pinyin.charAt(0);
//            // return firstLetter.toUpperCase();
//            return Character.toUpperCase(firstLetter);
//        } else {
//            return c;
//        }
//    }
//
//    /**
//     * 获取每个字的首字母.
//     *
//     * @param content
//     * @return
//     */
//    public static String getFirstLetters(String content) {
//        if (StrUtil.isBlank(content)) {
//            return "";
//        }
//        StringBuffer str = new StringBuffer(10);
//        for (int i = 0; i < content.length(); i++) {
//            char c = content.charAt(i);
//            if (StrUtil.isLetter(c)) {
//                str.append(Character.toUpperCase(c));
//            } else if (NumberUtil.isNumeric(String.valueOf(c))) {
//                str.append(c);
//            } else {
//                String pinyin = PinyinUtil.getPinyin(c);// 首字母
//                if (StrUtil.isNotBlank(pinyin)) {
//                    char firstLetter = pinyin.charAt(0);
//                    str.append(Character.toUpperCase(firstLetter));
//                }
//            }
//        }
//        return str.toString();
//    }
//
//    /**
//     * 获取汉字串拼音，英文字符不变
//     *
//     * @param chinese 汉字串
//     * @return 汉语拼音
//     */
//    public static String getPinyin(Character c) {
//        String[] strs;
//        try {
//            strs = PinyinHelper.toHanyuPinyinStringArray(c, defaultFormat);
//        } catch (BadHanyuPinyinOutputFormatCombination e) {
//            throw new RuntimeException(e.getMessage());
//        }
//        if (strs == null) {
//            return null;
//        }
//        return strs[0];
//    }
//
//    public static String getPinyin(String content, String split) {
//        StringBuilder sb = new StringBuilder();
//        for (char c : content.toCharArray()) {
//            if (StrUtil.isLetter(c)) {
//                sb.append(Character.toUpperCase(c));
//            } else if (NumberUtil.isNumeric(String.valueOf(c))) {
//                sb.append(c);
//            } else {
//                String pinyin = getPinyin(c);
//                if (pinyin != null) {
//                    if (sb.length() > 0) {
//                        sb.append(split);
//                    }
//                    sb.append(pinyin);
//                }
//            }
//        }
//        return sb.toString();
//    }
//
//    public static String getPinyin(String content) {
//        return PinyinUtil.getPinyin(content, "");
//    }
//
//    public static void main(String[] args) {
//        System.out.println(PinyinUtil.getFirstLetters("个护化妆、清洁用品、宠物/宠物生活/水族用品"));
//    }
//}
