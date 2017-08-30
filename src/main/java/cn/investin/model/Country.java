package cn.investin.model;

public class Country {

	int id;
	String countryDesc_cn;
	String countryDesc_en;
	String code;
	int order_num;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountryDesc_cn() {
		return countryDesc_cn;
	}

	public void setCountryDesc_cn(String countryDesc_cn) {
		this.countryDesc_cn = countryDesc_cn;
	}

	public String getCountryDesc_en() {
		return countryDesc_en;
	}

	public void setCountryDesc_en(String countryDesc_en) {
		this.countryDesc_en = countryDesc_en;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getOrder_num() {
		return order_num;
	}

	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}

}
