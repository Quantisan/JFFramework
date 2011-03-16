package com.quantisan.JFFramework.Trade;

import java.util.LinkedList;
import java.util.List;

import com.dukascopy.api.*;
import com.quantisan.JFFramework.ITag;
import com.quantisan.JFUtil.Orderer;

/**
 * Exit manager (i.e. exit strategy)
 * 
 * @author plam
 *
 */
public abstract class AbsExit implements ITag {
//	private AbsExit next;
//	
//	public void setNext(AbsExit successor) {
//		this.next = successor;
//	}
	private Period period;
	/**
	 * @param minPeriod period to check for exits
	 */
	public AbsExit(Period period) {
		this.period = period;
	}
	
	public Period getDefaultPeriod() {
		return this.period;
	}
	
	/**
	 * Manage all the positions in this instrument using the current exit algorithm
	 * by matching {@link #getTag() Tags}.
	 * 
	 * @param instrument
	 * @param period
	 * @param askBar
	 * @param bidBar
	 * @return a list of orders that has been checked, and maybe exited
	 * @throws JFException
	 */
	void managePositions(Instrument instrument, Period period, IBar askBar,
											IBar bidBar) throws JFException
	{
		List<IOrder> matchedOrders = new LinkedList<IOrder>();
		
		if (period == this.period) {
			List<IOrder> allOrders = Orderer.getOrders(instrument);
			for (IOrder order : allOrders) {
				OrderCommentReader label = new OrderCommentReader(order.getLabel());
				if (label.getExit().equals(this.getTag())) {
					matchedOrders.add(order);
				}
			}
			manageOrdersExit(instrument, matchedOrders, askBar, bidBar);
		}
	}
	
	/**
	 * Called when the order's tag is equal to current algorithm's tag.
	 * @param instrument
	 * @param orders a verified list of orders that uses this exit algorithm
	 * @param askBar current ask bar of the default period
	 * @param bidBar current bid bar of the default period
	 * @throws JFException
	 */
	protected abstract void manageOrdersExit(Instrument instrument, List<IOrder> orders, 
											IBar askBar, IBar bidBar) throws JFException;

	
	@Override public abstract String toString();

}
