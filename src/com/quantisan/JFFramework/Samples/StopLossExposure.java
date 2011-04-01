package com.quantisan.JFFramework.Samples;

import java.util.List;

import com.dukascopy.api.*;
import com.quantisan.JFFramework.AbstractExposure;
import com.quantisan.JFFramework.Trade.OrderCommentReader;
import com.quantisan.JFUtil.*;

/**
 * Assumes there is always a stop loss to every order.
 * 
 * @author plam
 *
 */
public class StopLossExposure extends AbstractExposure {
	public StopLossExposure(double riskPct) {
		super(riskPct);
	}

	@Override
	public boolean isNewPositionAllowed(String strategyTag, Instrument instrument) throws JFException 
	{	
		double totalValueExposed = 0;
		
		List<IOrder> orders = Orderer.getOrders(instrument);
		for (IOrder order : orders) {
			if (order.getState() == IOrder.State.FILLED || 
				order.getState() == IOrder.State.OPENED ||
				order.getState() == IOrder.State.CREATED) 
			{			
				double stop = order.getStopLossPrice();
				OrderCommentReader ocr = OrderCommentReader.getInstance(order.getComment());
				String tag = ocr.getStrategy();
				
				if (stop != 0d && tag.equals(strategyTag)) {
					double pipsExposed = order.getOpenPrice() - order.getStopLossPrice();
					pipsExposed *= order.isLong() ? 1d : -1d;
					if (pipsExposed > 0d)	// means at a loss since reversed sign
						totalValueExposed += pipsExposed * order.getAmount() * 1e6d;
				}
			}
		}
		double negative = Pairer.convertValueToAccountCurrency(instrument, totalValueExposed);	
		return negative / JForexAccount.getEquity() <= 0d;
	}

	@Override
	public String toString() {
		return "Stop loss check exposure";
	}

	@Override
	public String getTag() {
		return "SLE";
	}
}
