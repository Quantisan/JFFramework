package com.quantisan.JFFramework.Samples;

import com.dukascopy.api.*;
import com.dukascopy.api.IIndicators.AppliedPrice;
import com.dukascopy.api.IIndicators.MaType;
import com.quantisan.JFFramework.Sentiment;
import com.quantisan.JFFramework.Trade.AbstractCondition;
import com.quantisan.JFUtil.IndicatorBean.Indicating;
import com.quantisan.JFUtil.IndicatorBean.IndicatorBeanFactory;
import com.quantisan.JFUtil.IndicatorBean.MovingAverage;

public class DualMovingAveragesCondition extends AbstractCondition {
	private int fastWidth, slowWidth;
	private MovingAverage maBean = IndicatorBeanFactory.getMovingAverage();
	
	public DualMovingAveragesCondition(MaType maType, int fastWidth, int slowWidth) {		
		this.fastWidth = fastWidth;
		this.slowWidth = slowWidth;
		maBean.setAppliedPrice(AppliedPrice.MEDIAN_PRICE).setMAType(maType);
	}
	
	@Override
	public Sentiment calculate(Instrument instrument, Period period) throws JFException
	{			
		double maFast = Indicating.calculate(instrument, period, maBean.setWidth(this.fastWidth));
		double maSlow = Indicating.calculate(instrument, period, maBean.setWidth(this.slowWidth));

		if (maFast > maSlow)
			return Sentiment.BULL;
		else if (maFast < maSlow)
			return Sentiment.BEAR;
		else
			return Sentiment.NEUTRAL;
	}

	@Override
	public String toString() {
		return "Dual Moving Averages Condition";
	}

}
