package othellogame;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BoardTest.class, CpuPlayerTest.class, GameMasterTest.class,
		PlayerFactoryTest.class, UserPlayerTest.class })
public class AllTests {

}
