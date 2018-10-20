package businessEntity.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SelectT_USER extends DaoConnectionDriverManeger {

	private String[] tUsers;

	public ArrayList<String[]> selectT_USER(String userid, String password) {
		String selectAllSql = "select USER_NAME, LOGIN_TIME from T_USER where USER_ID = '" + userid + "' and PASSWORD = '" + password + "'";

		ArrayList<String[]> getResult = new ArrayList<String[]>();

		try {
			// Resultsetの作成
			ResultSet rset = stmt.executeQuery(selectAllSql);
			tUsers = new String[2];

			// 取得したデータを出力する
			while (rset.next()) {

				tUsers[0] = rset.getString(1);
				tUsers[1] = commonUtil.DateFormat.dateFormat(rset.getTimestamp(2));

				getResult.add(tUsers);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return getResult;
	}
}
