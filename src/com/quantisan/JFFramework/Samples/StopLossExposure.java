package com.quantisan.JFFramework.Samples;

import java.util.List;

import com.dukascopy.api.*;
import com.quantisan.JFFramework.AbsExposure;
import com.quantisan.JFUtil.*;

public class StopLossExposure extends AbsExposure {
	public StopLossExposure(double riskPct) {
		super(riskPct);
	}

	@Override
	public boolean isNewPositionAllowed(Instrument instrument) throws JFException 
	{
		double totalValueExposed = 0;
				
		List<IOrder> orders = Orderer.getOrders(instrument);
		for (IOrder order : orders) {
			double pipsExposed = order.getOpenPrice() - order.getStopLossPrice();
			pipsExposed *= order.isLong() ? 1d : -1d;
			totalValueExposed += pipsExposed * order.getAmount() * 1e6;
		}
		totalValueExposed *= Pairer.convertPipToAccountCurrency(instrument);
		return (totalValueExposed / JForexAccount.getEquity()) < getRiskPct();
	}
}
