package com.oddrock.penguin.penguinlistener.utils;
public class PenguinUtils {
	/**
	 * 将emoji表情替换成空串
	 * 
	 * @param source
	 * @return 过滤后的字符串
	 */
	public static String filterEmoji(String qqmsg) {
		if (qqmsg != null && qqmsg.length() > 0) {
			return qqmsg.replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", "");
		}else {
			return qqmsg;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
