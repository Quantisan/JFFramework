import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.quantisan.JFFramework.Trade.OrderCommentReader;


public class OrderCommentReaderTest {
	OrderCommentReader lr;
	
	@Before
	public void setUp() throws Exception {
		lr = OrderCommentReader.getInstance("eurusd:312842:Setup:Entry:Exit");
	}

	@Test
	public void testGetSetup() {
		assertEquals("Setup", lr.getSetup());
	}

	@Test
	public void testGetEntry() {
		assertEquals("Entry", lr.getEntry());
	}

	@Test
	public void testGetExit() {
		assertEquals("Exit", lr.getExit());
	}

}
