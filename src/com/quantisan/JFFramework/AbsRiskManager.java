package com.quantisan.JFFramework;

import com.quantisan.JFUtil.JForexAccount;

public abstract class AbsRiskManager implements IName {
	private double maxDD;
	
	public AbsRiskManager(double maxDrawdown) {
		this.setMaxDrawDown(maxDrawdown);
	}

	public abstract boolean isNewPositionAllowed();
	
	/**
	 * @param maxDrawdown the maxDD to set
	 */
	public void setMaxDrawDown(double maxDrawdown) {
		this.maxDD = maxDrawdown;
	}

	/**
	 * Check to see if the current drawdown is beyond the max. drawdown limit.
	 * 
	 * @param period
	 * @param strategy
	 */
	public void checkDrawdown(AbstractStrat strategy) {
		if (JForexAccount.isMaxDrawdownBroken(this.maxDD))
			this.drawDownBroken(strategy);
	}

	/**
	 * Called when max. drawdown is broken.  
	 * Implement drawdown risk control here.
	 */
	protected abstract void drawDownBroken(AbstractStrat strategy);

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
