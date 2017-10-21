package businesslogic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
public class InsertOracle {
 private Connection conn = null;
 private Statement stmt = null;
 private PreparedStatement ps = null;
 private DriverManeger dm = new DriverManeger();
 public void insertUserTable(String sql, String userId, String userName, String password, int age, String orgCd ) throws Exception
 {
    try 
    {
        // Connectionの作成   conn = dm.getConnection();
        //オートコミットはオフにする。   conn.setAutoCommit(false);
        // Statementの作成
        stmt = conn.createStatement();
        ps = conn.prepareStatement(sql);
        ps.setString(1, userId);
        ps.setString(2, userName);
        ps.setString(3, password);
        ps.setInt(4, age);
        ps.setString(5, orgCd);
        
        //INSERT文を実行する
        int result = ps.executeUpdate();
        //処理件数を表示する
        System.out.println("結果：" + result);
        //コミット
        conn.commit();
     }
     catch (Exception e)
     {
          conn.rollback();
          throw e;
     }
     finally
     {
        try
        {
          /* クローズ処理 */
          if (stmt != null) 
          {
              stmt.close();
              stmt = null;
          }
          if (conn != null)
          {
              conn.close();
              conn = null;
          }
        }
        catch (Throwable e)
        {
          // nop
        }
     }
  }
}
