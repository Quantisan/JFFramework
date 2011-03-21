package com.quantisan.JFFramework.Trade;

import com.quantisan.JFFramework.AbstractExposure;

/**
 * Factory to get framework Null component instances
 * 
 * @author LamT
 *
 */
public class NullFactory {
	private NullFactory() {};
	
	public static AbstractExposure getNullExposure() { return NullExposure.getInstance(); }
	
	public static AbstractExit getNullExit() { return NullExit.getInstance(); }
}
