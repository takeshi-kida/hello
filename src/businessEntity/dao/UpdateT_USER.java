package businessEntity.dao;

public class UpdateT_USER extends DaoConnectionDriverManeger {

	public void updateT_USER(String userId, String loginTime) {
		
		System.out.println(loginTime);
		
		String updateLoginTime = "update T_USER set LOGIN_TIME = TO_DATE('" + loginTime + "', 'YYYY-MM-DD HH24:MI:SS') where USER_ID = '" + userId + "'";

		try {

			//オートコミットはオフにする。
			conn.setAutoCommit(false);

			// Statementの作成
			stmt = conn.createStatement();

			ps = conn.prepareStatement(updateLoginTime);

			//UPDATE文を実行する
			int result = ps.executeUpdate();

			//処理件数を表示する
			System.out.println("結果：" + result);

			//コミット
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
