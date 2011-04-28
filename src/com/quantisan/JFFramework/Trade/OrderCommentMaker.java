package com.quantisan.JFFramework.Trade;

public class OrderCommentMaker {
	private OrderCommentMaker() {}
	
	public static String getComment(String strategyTag,
									String setupTag, 
									String entryTag,
									String stopTag,
									String exitTag) 
	{
		char split = ';';
		String label = strategyTag;
		label += split + setupTag;
		label += split + entryTag;
		label += split + stopTag;
		label += split + exitTag;
		label.replaceAll(" ", "");
		
		return label;
	}
}
