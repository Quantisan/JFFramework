package com.quantisan.JFFramework.Samples;

import com.quantisan.JFFramework.AbsSetup;


public class SetupFactory {
	private SetupFactory() {}
	
	
	public static AbsSetup getNullEntry() {
		return NullSetup.getInstance();
	}

}
