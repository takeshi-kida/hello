package businessLogic;

import businessEntity.dao.SelectT_USER;

public class SelectData {

	public String[] getTUserData(String userId, String passWord) throws Exception {

		SelectT_USER selectTUser = new SelectT_USER();

		String[] tUserData = selectTUser.selectT_USER(userId, passWord).get(0);

		return tUserData; 
	}
}