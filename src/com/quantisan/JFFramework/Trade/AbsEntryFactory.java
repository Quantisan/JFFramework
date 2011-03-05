package com.quantisan.JFFramework.Trade;


public abstract class AbsEntryFactory {
	private AbsEntryFactory() {}
	
	//public abstract IEntry createMAEntry();
	
	public static ISetup createNullEntry() {
		return new NullEntry();
	}

}
