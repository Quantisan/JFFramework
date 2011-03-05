package com.quantisan.JFFramework;

import com.dukascopy.api.IBar;
import com.dukascopy.api.Instrument;
import com.dukascopy.api.Period;

public abstract class AbsPositionManager implements IName {

	public abstract void checkPositions(Instrument instrument, 
									Period period, IBar askBar,
									IBar bidBar);

}
