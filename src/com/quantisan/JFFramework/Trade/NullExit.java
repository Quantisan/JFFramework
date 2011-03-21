package com.quantisan.JFFramework.Trade;

import java.util.List;

import com.dukascopy.api.IBar;
import com.dukascopy.api.IOrder;
import com.dukascopy.api.Instrument;
import com.dukascopy.api.JFException;
import com.dukascopy.api.Period;

public class NullExit extends AbstractExit {
	private static final NullExit INSTANCE = new NullExit(Period.WEEKLY);
	
	public NullExit(Period period) {
		super(period);
	}

	@Override
	public String getTag() {
		return "NUL";
	}

	@Override
	protected void manageOrdersExit(Instrument instrument,
									List<IOrder> matchedOrders, 
									IBar askBar, 
									IBar bidBar) throws JFException 
	{
		return;		// do nothing
	}

	@Override
	public String toString() {
		return "Null Exit";
	}

	public static AbstractExit getInstance() {
		return INSTANCE;
	}

}
