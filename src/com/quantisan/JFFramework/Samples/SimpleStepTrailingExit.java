package com.quantisan.JFFramework.Samples;

import java.util.List;

import com.dukascopy.api.IBar;
import com.dukascopy.api.IOrder;
import com.dukascopy.api.Instrument;
import com.dukascopy.api.JFException;
import com.dukascopy.api.Period;
import com.quantisan.JFFramework.Trade.AbstractExit;
import com.quantisan.JFFramework.Trade.OrderCommentReader;
import com.quantisan.JFUtil.JForexContext;

public class SimpleStepTrailingExit extends AbstractExit {
	private int step;
	//private double max = Double.NEGATIVE_INFINITY, min = Double.POSITIVE_INFINITY;
	
	public SimpleStepTrailingExit(Period period, int step) {
		super(period);
		this.step = step;
	}

	@Override
	public String getTag() {
		return "SST" + step;
	}

	@Override
	protected void manageOrdersExit(Instrument instrument,
									List<IOrder> matchedOrders, 
									IBar askBar, 
									IBar bidBar) throws JFException 
	{
//		double price = JForexContext.getPrice(instrument);
//		if (price > max) 	max = price;
//		else if (price < min) min = price;
		
		for (IOrder order : matchedOrders) {
			OrderCommentReader oc = OrderCommentReader.getInstance(order.getComment());
			int stopGap = Integer.getInteger(oc.getStop().substring(3));
			double diff = JForexContext.getPrice(instrument) - order.getStopLossPrice();
			diff *= order.isLong() ? 1d : -1d; 
			if (diff > stopGap)
			{
				// TODO implement new stop logic
				//Orderer.setStopLoss(order, newStop, trailStep)
			}
		}
	}

	@Override
	public String toString() {
		return "Simple Step Trailing Exit";
	}

}
