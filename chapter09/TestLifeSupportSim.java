import java.util.*;

public class TestLifeSupportSim {
	public static void main(String[] args) {
		ArrayList<SimUnit> aList = new ArrayList<>();
		//V2Radiator v2 = new V2Radiator(aList);
		V3Radiator v3 = new V3Radiator(aList);
		//RetentionBot ret;
		for (int z = 0; z < 20; z++) {
			RetentionBot ret = new RetentionBot(aList);
		}

		int radCount = 0;
		int retCount = 0;
		for (SimUnit su : aList) {
			if (su.powerUse() == 2) {
				retCount++;
			}
			if (su.powerUse() == 4) {
				radCount++;
			}
		}
		int retPowCons = 2 * retCount;
		int radPowCons = 4 * radCount;

		System.out.println("Retention bots: " + retCount + ", power consumption: " + retPowCons);
		System.out.println("Radiators: " + radCount + ", power consumption: " + radPowCons);
		System.out.println("Overall power consumption: " + (retPowCons + radPowCons));
	}
}