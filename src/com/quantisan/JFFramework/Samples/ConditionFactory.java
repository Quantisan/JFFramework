package com.quantisan.JFFramework.Samples;

import com.quantisan.JFFramework.Trade.ICondition;

public class ConditionFactory {
	private ConditionFactory() {}
	
	public static ICondition getMovingAverageCross(int fastLength, int slowLength) {
		return new DualMovingAveragesCondition(fastLength, slowLength);
	}
}
