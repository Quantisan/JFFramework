package com.quantisan.JFFramework.Trade;

import com.dukascopy.api.Instrument;
import com.quantisan.JFFramework.IName;
import com.quantisan.JFFramework.ITag;
import com.quantisan.JFFramework.Sentiment;

public interface IEnter extends IName, ITag {
	public void enterPosition(Instrument instrument, Sentiment sentiment);
}
