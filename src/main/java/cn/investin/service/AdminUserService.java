package cn.investin.service;

import java.util.HashMap;
import java.util.List;
import cn.investin.model.AdminUser;
import cn.investin.model.BaseData;

public interface AdminUserService {

	public List<AdminUser> getAllUser();

	public void addUser();

	public void updatePwd(AdminUser adminUser);

	public boolean login(AdminUser user);

	public List<HashMap<String, String>> getSetting();

	public List<BaseData> getBaseData(int pageNum);

	public int getCountForBaseData();

	public void addBaseData(BaseData baseData);

	public void updateBaseData(BaseData baseData);

	public void delBaseData(String id);

	public BaseData getBaseDataById(String id);

	public void updateSetting(String _key, String _value);
}
