package com.quantisan.JFFramework;

import com.dukascopy.api.*;

public interface ICleaner extends IName {
	/**
	 * Called in {@linkplain IStrategy#onStop()} to clean up or logging
	 */
	public void onStop();
}
