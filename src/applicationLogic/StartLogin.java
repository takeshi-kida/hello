package applicationLogic;

import businessLogic.SelectData;

public class StartLogin {

	public String[] getUserData(String userId, String password) throws Exception {
		SelectData selectData = new SelectData();

		// ユーザーテーブルからユーザーデータを取得する
		return selectData.getTUserData(userId, password);
	}
}
