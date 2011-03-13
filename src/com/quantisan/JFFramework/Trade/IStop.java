package com.quantisan.JFFramework.Trade;

import com.dukascopy.api.*;
import com.quantisan.JFFramework.*;

public interface IStop extends ITag {
	public abstract double getInitialStopPrice(Instrument instrument,
												Period period,
												Sentiment sentiment,
												double openPrice);
}
