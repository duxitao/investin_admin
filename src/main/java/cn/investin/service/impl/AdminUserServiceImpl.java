package cn.investin.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.investin.dao.AdminUserDao;
import cn.investin.model.AdminUser;
import cn.investin.model.BaseData;
import cn.investin.service.AdminUserService;
import cn.investin.util.AppConfig;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	AdminUserDao dao = null;
	@Autowired
	AppConfig appConfig;

	public List<AdminUser> getAllUser() {
		return dao.getAllUser();
	}

	public boolean login(AdminUser user) {
		int result = dao.getUserByIdAndPwd(user);
		return result > 0 ? true : false;
	}

	public List<HashMap<String, String>> getSetting() {
		return dao.getSetting();
	}

	public List<BaseData> getBaseData(int pageNum) {
		int pageSize = Integer.parseInt(appConfig.getProperties().getProperty("system.pageSize"));
		int limitStart = (pageNum - 1) * pageSize;

		return dao.getBaseData(limitStart, pageSize);
	}

	public int getCountForBaseData() {

		return dao.getCountForBaseData();
	}

	public void addBaseData(BaseData baseData) {
		dao.addBaseData(baseData);

	}

	public void updateBaseData(BaseData baseData) {
		dao.updateBaseData(baseData);

	}

	public void delBaseData(String id) {
		dao.delBaseData(id);

	}

	public BaseData getBaseDataById(String id) {

		return dao.getBaseDataById(id);
	}

	public void updateSetting(String _key, String _value) {
		dao.updateSetting(_key, _value);

	}

	public void addUser() {
		dao.addUser();
		
	}

	public void updatePwd(AdminUser adminUser) {
		dao.updatePwd(adminUser);
		
	}

}
