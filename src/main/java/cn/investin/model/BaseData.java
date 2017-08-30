package cn.investin.model;

import java.util.Date;

public class BaseData {

	int id;
	String type;
	String code;
	String name_zh;
	String name_en;
	String name_es;
	String tel;
	String add;
	int order_num;
	Date create_time;
	Date update_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName_zh() {
		return name_zh;
	}
	public void setName_zh(String name_zh) {
		this.name_zh = name_zh;
	}
	public String getName_en() {
		return name_en;
	}
	public void setName_en(String name_en) {
		this.name_en = name_en;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public String getName_es() {
		return name_es;
	}
	public void setName_es(String name_es) {
		this.name_es = name_es;
	}
	@Override
	public String toString() {
		return "BaseData [id=" + id + ", type=" + type + ", code=" + code + ", name_zh=" + name_zh + ", name_en="
				+ name_en + ", tel=" + tel + ", add=" + add + ", order_num=" + order_num + ", create_time="
				+ create_time + ", update_time=" + update_time + "]";
	}
	
	
	
}
