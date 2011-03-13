package com.quantisan.JFFramework.Samples;

import com.dukascopy.api.IBar;
import com.dukascopy.api.Instrument;
import com.dukascopy.api.JFException;
import com.dukascopy.api.Period;
import com.quantisan.JFFramework.*;
import com.quantisan.JFFramework.Trade.*;

public class DoubleMACrossSetup extends AbsSetup {
	private ICondition fastCondition, slowCondition;
	private Sentiment fastSentiment, slowSentiment;		// TODO put in Map<Instrument,Sentiment>
	private Period fastPeriod, slowPeriod;
	
	/**
	 * @param entry
	 * @param exit
	 * @param fastPeriod fast period to use for the fast set of moving averages
	 * @param slowPeriod slow period to use for the slow set of moving averages
	 * @param fastLength length of the faster moving average
	 * @param slowLength length of the slower moving average
	 */
	public DoubleMACrossSetup(AbsEntry entry, AbsExit exit,
								Period fastPeriod, Period slowPeriod, 
								int fastLength, int slowLength) 
	{
		super(entry, exit);
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
		return "DMC" + this.fastPeriod + this.slowPeriod;
	}

	@Override
	public String toString() {
		return "Double Moving average crossover setup";
	}

}
