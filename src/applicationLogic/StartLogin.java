package applicationLogic;

import businessLogic.SelectData;
import businessLogic.UpdateData;

public class StartLogin {

	public String[] getUserData(String userId, String password) throws Exception {
		SelectData selectData = new SelectData();

		// ユーザーテーブルからユーザーデータを取得する
		return selectData.getTUserData(userId, password);
	}
	
	public void updateLoginTime(String userId, String loginTime) throws Exception {
		UpdateData updateData = new UpdateData();

		updateData.updateTUserData(userId, loginTime);
	}
}
