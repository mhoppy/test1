package playground;

public class RateValue {
	private String main;
	private String pips;
	
	public RateValue(String rate) {
		int decimalIdx = rate.indexOf(".");
		setMain(rate.substring(0, decimalIdx+3));
		setPips( rate.substring(decimalIdx+ 3));
	}
	
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	public String getPips() {
		return pips;
	}
	public void setPips(String pips) {
		this.pips = pips;
	}

	public String getText() {
		return main + pips;
	}
	
	
	
}
