package com.quantisan.JFFramework.Samples;

import com.dukascopy.api.IOrder;
import com.dukascopy.api.Instrument;
import com.quantisan.JFFramework.Sentiment;
import com.quantisan.JFFramework.Trade.AbsEntry;
import com.quantisan.JFFramework.Trade.IStop;

public class SimpleMarketEntry extends AbsEntry {
	public SimpleMarketEntry(IStop stop) {
		super(stop);
	}

	@Override
	public String getTag() {
		return "SME";
	}

	@Override
	protected IOrder enterPosition(Instrument instrument, Sentiment sentiment,
			double riskPct, String label) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "Simple market entry";
	}

}
