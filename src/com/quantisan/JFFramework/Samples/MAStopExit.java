package com.quantisan.JFFramework.Samples;

import java.util.List;

import com.dukascopy.api.*;
import com.quantisan.JFFramework.Trade.AbsExit;
import com.quantisan.JFFramework.Trade.OrderCommentReader;
import com.quantisan.JFUtil.JForexContext;
import com.quantisan.JFUtil.Orderer;

public class MAStopExit extends AbsExit {
	private int length;

	/**
	 * @param period period to check positions for exit
	 * @param length length of the moving average
	 */
	public MAStopExit(Period period, int length) {
		super(period);
		this.length = length;
	}
	
	
	@Override
	public String getTag() {
		return "MAS" + this.length;
	}

	@Override
	public String toString() {
		return "MA Stop Exit";
	}


	@Override
	protected void manageOrdersExit(Instrument instrument, List<IOrder> orders, 
									IBar askBar, IBar bidBar) throws JFException 
	{	
		double ma = JForexContext.getIndicators().sma(instrument, 
				getDefaultPeriod(), 
				OfferSide.BID, 
				IIndicators.AppliedPrice.MEDIAN_PRICE, 
				this.length, 
				Filter.ALL_FLATS, 
				1, 
				JForexContext.getHistory().getStartTimeOfCurrentBar(instrument, getDefaultPeriod()), 
				0)[0];
		for (IOrder order : orders) {
			OrderCommentReader commr = OrderCommentReader.getInstance(order.getComment());
			if ( commr.getExit() == this.getTag() &&
				((order.isLong() && askBar.getHigh() < ma) ||
				(!order.isLong() && bidBar.getLow() > ma))) {
				Orderer.close(order);
			}
		}
	}

}
