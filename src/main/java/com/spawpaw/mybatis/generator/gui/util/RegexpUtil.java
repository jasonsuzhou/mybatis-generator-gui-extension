package com.spawpaw.mybatis.generator.gui.util;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpUtil {
    /**
     * @param prefix     前定界
     * @param catchGroup 捕获组
     * @param suffix     后定界
     * @return 寻找到的第一个组
     */
    public static String findMatches(String prefix, String catchGroup, String suffix, String findFrom) {
        if (findFrom == null) {
            return "";
        }
        String regexp = prefix + catchGroup + suffix;
        System.out.printf("从%s中查找%s", findFrom, regexp);
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(findFrom);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    public static List<String> findAllMatches(String prefix, String catchGroup, String suffix, String findFrom) {
        List<String> result = new LinkedList<>();
        String regexp = prefix + catchGroup + suffix;
        if (findFrom == null) {
            return result;
        }
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(findFrom);
        while (matcher.find()) {
            result.add(matcher.group(1));
        }
        return result;
    }

    /**
     * 如果是字段值是在固定一些数据中选择那么字段的备注可以写成：字段名:可选值1/可选值2<br/>
     * 此方法用来判断字段是否有可选值并把可选值解析成一个列表
     * @param plainText 原始字段名
     * @param valueSet 解析后的可选值列表
     * @return 返回解析后的字段名，也就是去掉括号内容的最后字段名是什么
     */
    public static String parseValueSet(String plainText, Set<String> valueSet) {
        if (plainText.contains("：")) {
            plainText = plainText.replace("：",":");
        }
        if (plainText.contains(":")) {
            String ss[] = plainText.split(":");
            String array[] = ss[1].split("/");
            Collections.addAll(valueSet, array);
            return ss[0];
        }
        return plainText;
    }

}
