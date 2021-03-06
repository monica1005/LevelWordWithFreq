package com.level.rtf2txt;

import com.level.Constant;
import com.level.util.CfgUtil;

public class ParsePdf2Txt {
	public ParsePdf2Txt() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    long startTime = System.currentTimeMillis();

	    // 1.读取pdf文件路径
		String cfg_pdf = CfgUtil.getPropCfg(Constant.FILE_CONFIG_FILE, "cfg_english_pdf");
		String pdfFilename = Constant.PATH_RESOURCES + cfg_pdf;
		RtfUtil.pdf2Text(pdfFilename);

		long endTime = System.currentTimeMillis();
        System.out.println("执行耗时 : " + (endTime - startTime) / 1000f + " 秒 ");
	}
}