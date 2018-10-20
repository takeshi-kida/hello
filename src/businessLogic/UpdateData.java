package businessLogic;

import businessEntity.dao.UpdateT_USER;

public class UpdateData {

	public void updateTUserData(String userId, String loginTime) throws Exception {

		UpdateT_USER updateTUser = new UpdateT_USER();

		updateTUser.updateT_USER(userId, loginTime);

	}
}