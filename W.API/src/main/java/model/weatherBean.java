package model;

public class weatherBean {

	private String cityStr;
	private String countryStr;
	private String cloudsStr;
	private String windStr;
	private String date;
	private Double celsiusTemp;

	public weatherBean() {}

	public String getCloudsStr() {
		return cloudsStr;
	}

	public void setCloudsStr(String cloudsStr) {
		this.cloudsStr = cloudsStr;
	}

	public String getWindStr() {
		return windStr;
	}

	public void setWindStr(String windStr) {
		this.windStr = windStr;
	}

	public String getDate() {
		return date;

	}

	public void setDate(String date) {
		// Add date without time
		this.date = date.substring(0, 10);
		;
	}

	public double getCelsiusTemp() {
		return Math.round(celsiusTemp);
	}

	public void setCelsiusTemp(String temperatureStr) {
		// Convert from kelvin to celsius
		this.celsiusTemp = Double.parseDouble(temperatureStr) - 273.15;
	}

	public String getCityStr() {
		return cityStr;
	}

	public void setCityStr(String cityStr) {
		this.cityStr = cityStr;
	}

	public String getCountryStr() {
		return countryStr;
	}

	public void setCountryStr(String countryStr) {
		this.countryStr = countryStr;
	}

}
