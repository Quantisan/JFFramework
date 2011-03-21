package com.quantisan.JFFramework.Samples;

import com.dukascopy.api.*;
import com.quantisan.JFFramework.Sentiment;
import com.quantisan.JFFramework.Trade.AbstractCondition;
import com.quantisan.JFUtil.JForexContext;

public class DualMovingAveragesCondition extends AbstractCondition {
	private int fastLength, slowLength;
	
	public DualMovingAveragesCondition(int fastLength, int slowLength) {
		this.fastLength = fastLength;
		this.slowLength = slowLength;
	}
	
	@Override
	public Sentiment calculate(Instrument instrument, Period period) throws JFException
	{
		double maFast = JForexContext.getIndicators().sma(instrument, 
							period, 
							OfferSide.BID, 
							IIndicators.AppliedPrice.MEDIAN_PRICE, 
							this.fastLength, 
							Filter.ALL_FLATS, 
							1, 
							JForexContext.getHistory().getStartTimeOfCurrentBar(instrument, period), 
							0)[0];
		double maSlow = JForexContext.getIndicators().sma(instrument, 
							period, 
							OfferSide.BID, 
							IIndicators.AppliedPrice.MEDIAN_PRICE, 
							this.slowLength, 
							Filter.ALL_FLATS, 
							1, 
							JForexContext.getHistory().getStartTimeOfCurrentBar(instrument, period), 
							0)[0];
		if (maFast > maSlow)
			return Sentiment.BULL;
		else if (maFast < maSlow)
			return Sentiment.BEAR;
		else
			return Sentiment.NEUTRAL;
	}

	@Override
	public String toString() {
		return "Dual MA Condition";
	}

}
