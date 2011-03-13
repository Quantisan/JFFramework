package com.quantisan.JFFramework.Samples;

import com.dukascopy.api.Instrument;
import com.dukascopy.api.Period;
import com.quantisan.JFFramework.Sentiment;
import com.quantisan.JFFramework.Trade.IStop;

public class FixedAbsoluteStop implements IStop {
	private double gap;
	public FixedAbsoluteStop(double gap) {
		this.gap = gap;
	}
	
	@Override
	public double getInitialStopPrice(Instrument instrument, Period period,
			Sentiment sentiment, double openPrice) {
		double stop = 0;
		if (sentiment == Sentiment.BULL)
			stop = openPrice - (this.gap * instrument.getPipValue());
		else if (sentiment == Sentiment.BEAR)
			stop = openPrice + (this.gap * instrument.getPipValue());
		return stop;
	}

	@Override
	public String getTag() {		
		return "FAS" + this.gap;
	}

}
