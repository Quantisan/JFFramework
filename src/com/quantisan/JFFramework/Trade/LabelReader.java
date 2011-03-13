package com.quantisan.JFFramework.Trade;

public class LabelReader {
	private String[] texts;
	
	public LabelReader(String label) {
		this.texts = label.split(":");
	}
	
	public String getInstrument() {
		return texts[0];
	}
	
	public String getTimestamp() {
		return texts[1];
	}
	
	public String getSetup() {
		return texts[2];
	}
	
	public String getEntry() {
		return texts[3];
	}
	
	public String getExit() {
		return texts[4];
	}
}
