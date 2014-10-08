package othellogame;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(BoardTest.class);
		suite.addTestSuite(CpuPlayerTest.class);
		suite.addTestSuite(GameMasterTest.class);
		suite.addTestSuite(PlayerFactoryTest.class);
		suite.addTestSuite(UserPlayerTest.class);
		//$JUnit-END$
		return suite;
	}

}
