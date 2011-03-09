package com.quantisan.JFFramework.Samples;

import com.dukascopy.api.Instrument;
import com.dukascopy.api.Period;
import com.quantisan.JFFramework.AbsRiskManager;
import com.quantisan.JFFramework.AbstractStrat;
import com.quantisan.JFUtil.JForexAccount;

public class MuteOnDrawdownRM extends AbsRiskManager {
	private double maxDD = 0.01d;		// drawdown in decimal, e.g. 1% = 0.01

	@Override
	public String getName() {
		return "Mute on Drawdown RM";
	}

	@Override
	public String getTag() {
		return "MOD";
	}

	@Override
	public boolean isNewPositionAllowed(Instrument instrument, Period period) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void checkRisk(AbstractStrat strategy, Instrument instrument,
			Period period) {
		if (JForexAccount.isMaxDrawdownBroken(this.maxDD))
			strategy.setSetup(SetupFactory.getNullEntry());
		
	}

}
