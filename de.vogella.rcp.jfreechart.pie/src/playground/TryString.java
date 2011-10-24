package playground;

public class TryString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RateValue r = new RateValue("1.38621");
		System.out.println("[" + r.getMain() + "][" + r.getPips() + "]");

		RateValue r2 = new RateValue("13883.38621");
		
		System.out.println("[" + r2.getMain() + "][" + r2.getPips() + "]");
		
		RateValue r3 = new RateValue("13883.38");
		System.out.println("[" + r3.getMain() + "][" + r3.getPips() + "]");		
	}

}
