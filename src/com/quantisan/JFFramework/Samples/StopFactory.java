package com.quantisan.JFFramework.Samples;

public class StopFactory {
	private StopFactory() {};
	
	public static FixedAbsoluteStop getFixedAbsoluteStopInstance(double gap) {
		return new FixedAbsoluteStop(gap);
	}
}
