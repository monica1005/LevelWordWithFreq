package com.myblog.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.myblog.model.Word;


public class RegEx {
    /**
    * @param regex
    * 正则表达式字符串
    * @param txt
    * 要匹配的字符串
    * @return 如果txt 符合 regex的正则表达式格式,返回true, 否则返回 false;
    */
    private static boolean isMatch(String regex, String txt) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(txt);
        return matcher.matches();
    }
    
//    5294    vitamin n.维生素
//    5295    vivid   a.鲜艳的;生动的,栩栩如生的
//    5296    vocabulary  n.词汇,词汇量;词汇表
//    5297    vocal   a.声音的;有声的;歌唱的 n.元音;声乐作品
//    ★ accession
//    ★ accessory
//       accident
//       accidental
//    ▲ acclaim
//    ★ accommodate
//       accommodation
//       accompany
//    ▲ accomplice
//       accomplish
//       accord
    public static Word catchFreqLevelWord(String line) {
//        if (line.trim())
//        Word freqLevelWord = new Word();
        Word levelWord = new Word();
        // /匹配双字节字符(包括汉字在内)：[^x00-xff]
        String regEx = "([^x00-xff])\\s*([a-zA-Z()\\-\']+)";//匹配一个宽字符 空白(空格) 大小写字母或连字符号(Coca-Cola/ice-cream/living-room/t-shirt/up-to-date/x-ray) 单引号(o'clock),不匹配数字
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(line);
        if (matcher.find()) {
            // int gc = matcher.groupCount();
            // System.out.println("gc = " + gc);
            // 一般要求4794个单词（含中学已学词汇），表中不设标记；
            // 较高要求1601个单词，表中标记为★；
            // 更高要求1281个单词，表中标记为▲。
            String cet = matcher.group(1);
            String word = matcher.group(2);
//          中考18Level
//          高考35Level
//          四级46Level
//          考研55Level
//          六级64Level
//          String level = "cet4";
//          if (cet.equals("★")) {
//              level = "cet6";
//          } else if (cet.equals("▲")) {
//              level = "cet8";
//          } else {
//              level = "cet4";
//          }
            String level = "";
            if (cet.equals("★")) {
                level = "六级";
            } else if (cet.equals("▲")) {
                level = "更高要求";
//              word = "";
            } else if(isNumber(cet)){
                levelWord.setFrequency(cet);
            } else {
                level = "四级";
            }
            levelWord.setSpelling(word.replaceAll("[()]", "")); // 删除()小括号
//          levelWord.setSpelling(word.replaceAll("[()]", "").toLowerCase()); // 删除()小括号
            levelWord.setLevel(level);
        }
        return levelWord;
      }
    
    
	public static Word toLevelWord(String line) {
		Word levelWord = new Word();
		// /匹配双字节字符(包括汉字在内)：[^x00-xff]
		//String regEx = "([\\d|^x00-xff]+)\\s*([a-zA-Z()\\-\']+)";//匹配一个宽字符 空白(空格) 大小写字母或连字符号(Coca-Cola/ice-cream/living-room/t-shirt/up-to-date/x-ray) 单引号(o'clock),不匹配数字
		String regEx = "([^x00-xff]|\\d+)\\s*([a-zA-Z()\\-\']+)";//匹配一个宽字符 空白(空格) 大小写字母或连字符号(Coca-Cola/ice-cream/living-room/t-shirt/up-to-date/x-ray) 单引号(o'clock),不匹配数字
		Pattern p = Pattern.compile(regEx);
		Matcher matcher = p.matcher(line);
		if (matcher.find()) {
			// int gc = matcher.groupCount();
			// System.out.println("gc = " + gc);
			// 一般要求4794个单词（含中学已学词汇），表中不设标记；
			// 较高要求1601个单词，表中标记为★；
			// 更高要求1281个单词，表中标记为▲。
			String cet = matcher.group(1);
			String word = matcher.group(2);
//			中考18Level
//			高考35Level
//			四级46Level
//			考研55Level
//			六级64Level
//			String level = "cet4";
//			if (cet.equals("★")) {
//				level = "cet6";
//			} else if (cet.equals("▲")) {
//				level = "cet8";
//			} else {
//				level = "cet4";
//			}
			String level = "";
			if (cet.equals("★")) {
				level = "六级";
			} else if (cet.equals("▲")) {
				level = "更高要求";
//				word = "";
            } else if(isNumber(cet)){
                levelWord.setFrequency(cet);
			} else {
				level = "四级";
			}
			levelWord.setSpelling(word.replaceAll("[()]", "")); // 删除()小括号
//			levelWord.setSpelling(word.replaceAll("[()]", "").toLowerCase()); // 删除()小括号
			levelWord.setLevel(level);
		}
		return levelWord;
	  }
	
	// ★ authoritative
	// ▲ austere
	// ★ authorize/-ise
	// ax(e)
	// ★ behavio(u)ral
	// airplane/aeroplane
	public static String[] catchWord(String line) {
		// String []ret ={"",""};
		String[] arr = new String[2];
		// /匹配双字节字符(包括汉字在内)：[^x00-xff]
		String regEx = "([^x00-xff])\\s*([a-zA-Z()]+)";
		Pattern p = Pattern.compile(regEx);
		Matcher matcher = p.matcher(line);
		if (matcher.find()) {
			int gc = matcher.groupCount();
			// System.out.println("gc = " + gc);
			// 一般要求4794个单词（含中学已学词汇），表中不设标记；
			// 较高要求1601个单词，表中标记为★；
			// 更高要求1281个单词，表中标记为▲。
			String cet = matcher.group(1);
			String word = matcher.group(2);
			String level = "";
			String freq = "";
			if (cet.equals("★")) {
				level = "64";
			} else if (cet.equals("▲")) {
				level = "77";
			} else if(isNumber(cet)){
			    freq = cet;
			} else {
				level = "48";
			}
			word = word.replaceAll("[()]", ""); // 删除()小括号
			// System.out.println("group 1-2:" +cet + "," + word);

			// for(int i = 0; i <= gc; i++) {
			// System.out.println("group " + i + " :" + matcher.group(i));
			// }
			arr[0] = word;
			arr[1] = level;
		}
		return arr;
	}

	// 获取4级单词/6级单词/大学要求所有单词
	// level:4/6/8
	public static String catchWord(String line, int level) {
		// /匹配双字节字符(包括汉字在内)：[^x00-xff]
		String regEx = "([^x00-xff])\\s*([a-zA-Z()]+)";
		Pattern p = Pattern.compile(regEx);
		Matcher matcher = p.matcher(line);
		if (matcher.find()) {
			// int gc = matcher.groupCount();
			// System.out.println("gc = " + gc);
			// 一般要求4794个单词（含中学已学词汇），表中不设标记；
			// 较高要求1601个单词，表中标记为★；
			// 更高要求1281个单词，表中标记为▲。
			String cet = matcher.group(1);
			String word = matcher.group(2);
			if (level <= 4) {
				if (cet.equals("★")) {
				} else if (cet.equals("▲")) {
				} else {
					return word.replaceAll("[()]", ""); // 删除()小括号
				}
			} else if (level <= 6) {
				if (cet.equals("★")) {
					return word.replaceAll("[()]", ""); // 删除()小括号
				} else if (cet.equals("▲")) {
				} else {
					return word.replaceAll("[()]", ""); // 删除()小括号
				}
			} else {
				return word.replaceAll("[()]", ""); // 删除()小括
			}
		}
		return "";
	}

	// 从指定的(第一个匹配的)字符串，删除到行尾 ,String tag
	public static String removeTrail(String line,String tag) {
		return line.replaceAll(tag+".*$", "");
	}
	
    /**
    * 验证字符串是否为整数
    * 
    * @param 待验证的字符串
    * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
    */
    public static boolean isNumber(String line) {
        String regex = "^[0-9]*$";
        return isMatch(regex, line);
    }

    
	// 判断字符串中是否包含数字
	public static boolean containsNumber(String line) {
		String regex="[\\d]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(line);
		return matcher.find();
	}
	
	// 摄取本行第一个整数,第一个单词，第二个整数
    public static Word catchNumberWord(String line) {
        Word word = toLevelWord(line);
        if (word.getSpelling() == null) {
            String []arr = line.trim().split("\\s");
            if (arr.length == 1) {
                word = new Word(arr[0]);    
            } else if (arr.length >= 2) {
                word = new Word(arr[0],arr[1]);
            }
        }
        return word;
    }
	
	public static void main(String[] args) {
	    //String line = "★ behavio(u)ral";
//	    String line = "▲ acclaim";
//		 String line = "★ authorize/-ise";
//		 String line = "  ax(e) ";
//		String line = "  airplane/aeroplane(r)";
//      String line = "believe";
//	    String line = "654 finish";
	    String line = "5297   vocal   a.声音的;有声的;歌唱的 n.元音;声乐作品";
	    
      
		//catchWord(line);
		
		//System.out.println("removeTrail=" + removeTrail(line,"/"));
		
		//System.out.println("replaceAll=" + line.replaceAll("[()\\d]", ""));
		
		//System.out.println("containsNumber=" + containsNumber(line));
	    System.out.println("containsNumber:" + catchNumberWord(line));
	}

}
