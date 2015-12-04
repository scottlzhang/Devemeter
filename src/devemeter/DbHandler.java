package devemeter;
import java.sql.*;

public class DbHandler {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://db4free.net:3306/devemeter";
	private static final String USER = "likaiwanqing";
	private static final String PASS = "1qazxsw2";
	
	private Connection conn = null;
	private Statement stmt = null;
	
	public DbHandler(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		}
	}
	
	public int addLine(int uid, int lang_code, int date) {
		try {
			stmt=conn.createStatement();
			String sql="UPDATE userlog SET line_count=line_count+1 WHERE uid=? AND lang_code=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setInt(2, lang_code);
			int result=pstmt.executeUpdate();
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		      return -1;
		}
	}

}
