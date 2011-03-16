package com.quantisan.JFFramework.Trade;

import com.quantisan.JFFramework.AbsExposure;

/**
 * Factory to get framework Null component instances
 * 
 * @author LamT
 *
 */
public class NullFactory {
	public static AbsExposure getNullExposure() {
		return NullExposure.getInstance();
	}
}
