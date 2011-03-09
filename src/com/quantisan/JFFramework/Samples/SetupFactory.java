package com.quantisan.JFFramework.Samples;

import com.quantisan.JFFramework.Trade.AbsSetup;


public class SetupFactory {
	private SetupFactory() {}
	
	
	public static AbsSetup getNullEntry() {
		return NullSetup.getInstance();
	}

}
