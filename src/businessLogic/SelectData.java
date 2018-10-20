package businessLogic;

import businessEntity.dao.SelectT_USER;

public class SelectData {

	public String[] getTUserData(String userId, String passWoord) throws Exception {

		SelectT_USER selectTUser = new SelectT_USER();

		String[] tUserData = selectTUser.select_USER(userId, passWoord).get(0);

		return tUserData; 
	}
}