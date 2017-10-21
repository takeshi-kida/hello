package businesslogic;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ReadCsv
{
  public void readUriageCsv() throws Exception
  {
    try
    {
      File csv = new File("C:\\TEMP\\URIAGE.csv"); // CSVデータファイル
      
      BufferedReader br = new BufferedReader(new FileReader(csv));
      
      InsertOracle insertOracle = new InsertOracle();
      SelectOracle selectOracle = new SelectOracle();
   
      // 最終行まで読み込む
      String line = "";
      String inclusionYMD = selectOracle.selectTSystemInfo();
      int seqTSaleTran = 0;
      while ((line = br.readLine()) != null)
      {
        // 1行をデータの要素に分割
        StringTokenizer st = new StringTokenizer(line, ",");
        String voucherNo = "";
        String ProductCd = "";
        int sales = 0;
        seqTSaleTran = selectOracle.seqTranUri();
        
        while (st.hasMoreTokens())
        {
          // 1行の各要素をタブ区切りで表示
          voucherNo = st.nextToken();
          ProductCd = st.nextToken();
          sales = Integer.parseInt(st.nextToken());
        }
    
        insertOracle.insertUriageTran("INSERT INTO T_SALE_TRAN values(?, ?, ?, ?, ?)", seqTSaleTran, inclusionYMD, voucherNo, ProductCd, sales);
       }
      br.close();
  } 
  catch (FileNotFoundException e)
  {
    // Fileオブジェクト生成時の例外捕捉   e.printStackTrace();  
  }
  catch (IOException e)
  {
    // BufferedReaderオブジェクトのクローズ時の例外捕捉   e.printStackTrace();
  }
 }
}
