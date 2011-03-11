package com.quantisan.JFFramework.Trade;

import com.dukascopy.api.Instrument;
import com.quantisan.JFFramework.*;

public abstract class AbsEnter implements ITag {
	public abstract void enterPosition(Instrument instrument, Sentiment sentiment);
	
	@Override public abstract String toString();
}
