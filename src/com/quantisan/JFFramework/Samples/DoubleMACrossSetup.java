package com.quantisan.JFFramework.Samples;

import com.dukascopy.api.IBar;
import com.dukascopy.api.Instrument;
import com.dukascopy.api.JFException;
import com.dukascopy.api.Period;
import com.quantisan.JFFramework.*;
import com.quantisan.JFFramework.Trade.*;

public class DoubleMACrossSetup extends AbsSetup {
	private ICondition fastCondition, slowCondition;
	private Sentiment fastSentiment, slowSentiment;
	private Period fastPeriod, slowPeriod;
	
	public DoubleMACrossSetup(Period fastPeriod, Period slowPeriod, 
								int fastLength, int slowLength) 
	{
		this.fastPeriod = fastPeriod;
		this.slowPeriod = slowPeriod;
		fastCondition = ConditionFactory.getMovingAverageCross(fastLength, slowLength);
		slowCondition = ConditionFactory.getMovingAverageCross(fastLength, slowLength);
	}
	
	@Override
	public void initializeConditions(Instrument instrument) throws JFException {
		fastSentiment = fastCondition.calculate(instrument, fastPeriod);
		slowSentiment = slowCondition.calculate(instrument, slowPeriod);
	}

	@Override
	public Sentiment calculate(Instrument instrument, Period period,
								IBar askBar, IBar bidBar) throws JFException
	{
		if (period == fastPeriod)
			fastSentiment = fastCondition.calculate(instrument, fastPeriod);
		else if (period == slowPeriod)
			slowSentiment = slowCondition.calculate(instrument, slowPeriod);
		
		if (fastSentiment == Sentiment.BULL && slowSentiment == Sentiment.BULL)
			return Sentiment.BULL;
		else if (fastSentiment == Sentiment.BEAR && slowSentiment == Sentiment.BEAR)
			return Sentiment.BEAR;
		else
			return Sentiment.NEUTRAL;
	}

	@Override
	public String getTag() {
		return "DMC";
	}

	@Override
	public String toString() {
		return "Double Moving average crossover setup";
	}

}
