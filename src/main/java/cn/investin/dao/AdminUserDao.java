package cn.investin.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.investin.model.AdminUser;
import cn.investin.model.BaseData;

public interface AdminUserDao {

	/**
	 * 查询所有管理员
	 * 
	 * @return
	 */
	public List<AdminUser> getAllUser();
	
	public void addUser();
	
	public void updatePwd(AdminUser adminUser);

	/**
	 * 根据ID、密码查询用户
	 * 
	 * @param user
	 * @return
	 */
	public int getUserByIdAndPwd(AdminUser user);

	public List<HashMap<String, String>> getSetting();

	public List<BaseData> getBaseData(@Param("limitStart") int limitStart, @Param("pageSize") int pageSize);

	public int getCountForBaseData();

	public void addBaseData(BaseData baseData);

	public void updateBaseData(BaseData baseData);

	public void delBaseData(@Param("id") String id);

	public BaseData getBaseDataById(@Param("id") String id);

	public void updateSetting(@Param("_key") String _key, @Param("_value") String _value);

}
