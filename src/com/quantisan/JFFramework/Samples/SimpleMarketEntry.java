package com.quantisan.JFFramework.Samples;

import java.util.concurrent.Future;
import com.dukascopy.api.IEngine.OrderCommand;
import com.dukascopy.api.IOrder;
import com.dukascopy.api.Instrument;
import com.dukascopy.api.JFException;
import com.quantisan.JFFramework.Sentiment;
import com.quantisan.JFFramework.Trade.AbstractEntry;
import com.quantisan.JFFramework.Trade.IStop;
import com.quantisan.JFUtil.*;

public class SimpleMarketEntry extends AbstractEntry {
	public SimpleMarketEntry(IStop stop) {
		super(stop);
	}

	@Override
	public String getTag() {
		return "SME";
	}

	@Override
	protected Future<IOrder> enterPosition(Instrument instrument, Sentiment sentiment,
			double riskPct, String comment) throws JFException 
	{		
		double lot, open, stop;
		
		OrderCommand command;
		if (sentiment == Sentiment.BULL)		command = OrderCommand.BUY;
		else if (sentiment == Sentiment.BEAR) 	command = OrderCommand.SELL;
		else {
			throw new IllegalArgumentException("sentiment must be BULL or BEAR");
		}		
		
		open = JForexContext.getPrice(instrument);
		stop = this.getStop().getInitialStopPrice(instrument, sentiment, open);
		
		lot = Pairer.getLot(instrument, riskPct, Math.abs(open - stop));
		OrderTicket ticket = new OrderTicket.Builder(LabelMaker.getLabel(instrument), 
														instrument, command, lot)
											.setStopLossPrice(stop)
											.setComment(comment)
											.build();
		return Orderer.placeOrder(ticket);
	}

	@Override
	public String toString() {
		return "Simple market entry";
	}

}
