package com.level.rtf2txt.fmt;

import java.util.List;
import java.util.stream.Collectors;

import com.level.Constant;
import com.level.util.CfgUtil;
import com.level.util.RegEx;
import com.level.util.ResourceUtil;

public class FmtCurriculumRequirements {

	public static void main(String[] args) {
		doFmtCurriculumRequirements();
	}
	 public static void doFmtCurriculumRequirements() {
	        long startTime = System.currentTimeMillis();
	        // 1.读取单词集合路径
	        String cfg_english_path = CfgUtil.getPropCfg(Constant.FILE_CONFIG_FILE, "cfg_english_txt");
	        // String forderPath = "E:/FanMingyou/The Economist";
	        String englishTxtFile = Constant.PATH_RESOURCES + cfg_english_path;
	        // 2.加载/读取原文件line集合
	        List<String> fileLines = ResourceUtil.readFileLines(englishTxtFile);
	        // 3.处理文件集合:格式化/替换
	        List<String> wordLines = fmtWord(fileLines);
	        // 3.保存word list
	        String cfg_english_txt_result_path = CfgUtil.getPropCfg(Constant.FILE_CONFIG_FILE, "cfg_sum_result");
	        String txtWordFile = Constant.PATH_RESOURCES + cfg_english_txt_result_path;
	        ResourceUtil.writerFile(txtWordFile, wordLines, false);
	        long endTime = System.currentTimeMillis();
	        System.out.println("执行耗时 : " + (endTime - startTime) / 1000f + " 秒 ");
	    }

	    public static List<String> fmtWord(List<String> fileLines) {
	        List<String> wordList = fileLines.parallelStream()
	                .filter(line -> !line.trim().startsWith("#") && !"".equals(line.trim()))
	                .map(RegEx::fmtWord)
	                //.distinct() // 剔重
	                .collect(Collectors.toList());
	        // .collect(Collectors.toSet());
	        // .collect(Collectors.toMap(Word::getSpelling, Word::getSentences));
	        // System.out.println("unique words count: " + wordMap.size());
	        return wordList;
	    }
}
