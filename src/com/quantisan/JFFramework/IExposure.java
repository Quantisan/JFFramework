package com.quantisan.JFFramework;

import com.dukascopy.api.Instrument;

public interface IExposure {
	public boolean isNewPositionAllowed(Instrument instrument);
}
