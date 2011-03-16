package com.quantisan.JFFramework.Trade;

public class OrderCommentReader {
	private String[] texts;
	
	private OrderCommentReader() {};
	
	/**
	 * Factory
	 * @param label
	 * @return an instance
	 */
	public static OrderCommentReader getInstance(String comment) {
		OrderCommentReader commr = new OrderCommentReader();
		commr.texts = comment.split(";");
		return commr;
	}
	
	public String getSetup() {
		return texts[0];
	}
	
	public String getEntry() {
		return texts[1];
	}
	
	public String getStop() {
		return texts[2];
	}
	
	public String getExit() {
		return texts[3];
	}
}
