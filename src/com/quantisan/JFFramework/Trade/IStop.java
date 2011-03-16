package com.quantisan.JFFramework.Trade;

import com.dukascopy.api.*;
import com.quantisan.JFFramework.*;

public interface IStop extends ITag {	
	public double getInitialStopPrice(Instrument instrument,											
												Sentiment sentiment,
												double openPrice);
}
