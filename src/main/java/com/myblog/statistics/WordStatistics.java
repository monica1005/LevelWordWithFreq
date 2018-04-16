package com.myblog.statistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.myblog.util.ResourceUtil;

public class WordStatistics {

    public WordStatistics() {
    }

    public static void main(String[] args) throws IOException {
        String fileName = "WordStatistics.txt";// 文件路径
        // String formfileName =
        // "E:/workspace_4.6.3_LevelWord_2017-07-26/LevelWordWithFreq/src/main/resources/countwords.txt";
        System.out.println(fileName);
        String body = ResourceUtil.readFile(fileName);
        Map<String, Integer> map = countWords(body);
        List<Map.Entry<String, Integer>> infoIds = sort(map);
        output(infoIds);
        //output2(map);
    }

    // java统计一段英文中单词及个数/统计各个单词出现的次数
    public static Map<String, Integer> countWords(String text) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Pattern pn = Pattern.compile("\\b[a-zA-Z-]+\\b");// 正则表达式,匹配一个单词边界，也就是指单词和空格(标点符号)间的位置
        // Pattern pn = Pattern.compile("[^a-zA-Z-]+");// 正则表达式,匹配一个非单词
        Matcher mr = pn.matcher(text);
        while (mr.find()) {
            String strWord = mr.group();
            // 转为小写
            String key = strWord.toLowerCase();
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }
        return map;
    }

    // 排序
    public static List<Map.Entry<String, Integer>> sort(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        return infoIds;
    }

    // 输出
    public static void output(List<Map.Entry<String, Integer>> infoIds) {
        int totalCnt = 0;
        for (int i = 0; i < infoIds.size(); i++) { // 输出
            Entry<String, Integer> id = infoIds.get(i);
            totalCnt += id.getValue();
            System.out.println("第" + (i+1) +  "行,个数" + id.getValue() + ":" + id.getKey());
        }
        System.out.println("单词总数:" + totalCnt);
    }

    // 输出2
    public static void output2(Map<String, Integer> map) {
        int line=0;
        int totalCnt = 0;
        Set<Entry<String, Integer>> entrySet = map.entrySet();
        Iterator<Entry<String, Integer>> it = entrySet.iterator();
        while (it.hasNext()) {
            Entry<String, Integer> next = it.next();
            totalCnt += next.getValue();
            System.out.println("第" + (line+1) +  "行,个数" + next.getValue() + ":" + next.getKey());
            line++;
        }
        System.out.println("单词总数:" + totalCnt);
    }
}
