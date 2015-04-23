package com.example.coolweather.util;

public class weather {
	/*cityX="328.55" cityY="90.2" cityname="郧西县" centername="郧西县" fontColor="FFFFFF" 
			pyName="" state1="2" state2="2" stateDetailed="阴" tem1="14" tem2="5" temNow="15"
			windState="微风" windDir="西南风" windPower="1级" humidity="42%" time="16:20" url="101201103"*/
	private String cityX;
	private String cityY;
	private String cityname;
	private String centername;
	private String fontColor;
	private String pyName;
	private String state1;
	private String state2;
	private String stateDetailed;
	private String tem1;
	private String tem2;
	private String temNow;
	private String windState;
	private String windDir;
	private String windPower;
	private String humidity;
	private String time;
	private String url;
	public weather() {
		// TODO Auto-generated constructor stub
	}
	public weather(String cityX, String cityY, String cityname,
                   String centername, String fontColor, String pyName, String state1,
                   String state2, String stateDetailed, String tem1, String tem2,
                   String temNow, String windState, String windDir, String windPower,
                   String humidity, String time, String url) {
		super();
		this.cityX = cityX;
		this.cityY = cityY;
		this.cityname = cityname;
		this.centername = centername;
		this.fontColor = fontColor;
		this.pyName = pyName;
		this.state1 = state1;
		this.state2 = state2;
		this.stateDetailed = stateDetailed;
		this.tem1 = tem1;
		this.tem2 = tem2;
		this.temNow = temNow;
		this.windState = windState;
		this.windDir = windDir;
		this.windPower = windPower;
		this.humidity = humidity;
		this.time = time;
		this.url = url;
	}
	public String getCityX() {
		return cityX;
	}
	public void setCityX(String cityX) {
		this.cityX = cityX;
	}
	public String getCityY() {
		return cityY;
	}
	public void setCityY(String cityY) {
		this.cityY = cityY;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getCentername() {
		return centername;
	}
	public void setCentername(String centername) {
		this.centername = centername;
	}
	public String getFontColor() {
		return fontColor;
	}
	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}
	public String getPyName() {
		return pyName;
	}
	public void setPyName(String pyName) {
		this.pyName = pyName;
	}
	public String getState1() {
		return state1;
	}
	public void setState1(String state1) {
		this.state1 = state1;
	}
	public String getState2() {
		return state2;
	}
	public void setState2(String state2) {
		this.state2 = state2;
	}
	public String getStateDetailed() {
		return stateDetailed;
	}
	public void setStateDetailed(String stateDetailed) {
		this.stateDetailed = stateDetailed;
	}
	public String getTem1() {
		return tem1;
	}
	public void setTem1(String tem1) {
		this.tem1 = tem1;
	}
	public String getTem2() {
		return tem2;
	}
	public void setTem2(String tem2) {
		this.tem2 = tem2;
	}
	public String getTemNow() {
		return temNow;
	}
	public void setTemNow(String temNow) {
		this.temNow = temNow;
	}
	public String getWindState() {
		return windState;
	}
	public void setWindState(String windState) {
		this.windState = windState;
	}
	public String getWindDir() {
		return windDir;
	}
	public void setWindDir(String windDir) {
		this.windDir = windDir;
	}
	public String getWindPower() {
		return windPower;
	}
	public void setWindPower(String windPower) {
		this.windPower = windPower;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "weather [cityX=" + cityX + ", cityY=" + cityY + ", cityname="
				+ cityname + ", centername=" + centername + ", fontColor="
				+ fontColor + ", pyName=" + pyName + ", state1=" + state1
				+ ", state2=" + state2 + ", stateDetailed=" + stateDetailed
				+ ", tem1=" + tem1 + ", tem2=" + tem2 + ", temNow=" + temNow
				+ ", windState=" + windState + ", windDir=" + windDir
				+ ", windPower=" + windPower + ", humidity=" + humidity
				+ ", time=" + time + ", url=" + url + "]";
	}
	
}
