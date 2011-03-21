package com.quantisan.JFFramework.Samples;

import com.dukascopy.api.IBar;
import com.dukascopy.api.Instrument;
import com.dukascopy.api.JFException;
import com.dukascopy.api.Period;
import com.quantisan.JFFramework.*;
import com.quantisan.JFFramework.Trade.*;

/**
 * @author plam
 *
 */
public class DoubleDualMASetup extends AbstractSetup {
	private AbstractCondition fastCondition, slowCondition;
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
	public DoubleDualMASetup(AbstractEntry entry, IStop stop, AbstractExit exit,
								Period fastPeriod, Period slowPeriod, 
								int fastLength, int slowLength) 
	{
		super(entry, stop, exit);
		this.fastPeriod = fastPeriod;
		this.slowPeriod = slowPeriod;
		fastCondition = new DualMovingAveragesCondition(fastLength, slowLength);
		slowCondition = new DualMovingAveragesCondition(fastLength, slowLength);
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
