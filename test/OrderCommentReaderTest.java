import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.quantisan.JFFramework.Trade.OrderCommentMaker;
import com.quantisan.JFFramework.Trade.OrderCommentReader;


public class OrderCommentReaderTest {
	OrderCommentReader lr;
	
	@Before
	public void setUp() throws Exception {
		String comment = OrderCommentMaker.getComment("Strat",
														"Setup", 
														"Entry",
														"Stop",
														"Exit");
		lr = OrderCommentReader.getInstance(comment);
	}

	@Test
	public void testGetSetup() {
		assertEquals("Setup", lr.getSetup());
	}
	
	@Test
	public void testGetStrategy() {
		assertEquals("Strat", lr.getStrategy());
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
