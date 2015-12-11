package devemeter;
import java.sql.*;
import java.util.*;

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
		      e.printStackTrace();
		}
	}
	
	public int addLine(int uid, int lang_code, int date) {
		try {
			stmt=conn.createStatement();
			String sql="UPDATE userlog SET line_count=line_count+1 WHERE uid=? AND lang_code=? AND date=?";
			System.out.println(sql);
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setInt(2, lang_code);
			pstmt.setInt(3, date);
			int result=pstmt.executeUpdate();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch(Exception e){
		      e.printStackTrace();
		      return -1;
		}
	}
	
	public int removeLine(int uid, int lang_code, int date, int count){
		try {
			stmt=conn.createStatement();
			String sql="UPDATE userlog SET line_count=line_count-? WHERE uid=? AND lang_code=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setInt(2, uid);
			pstmt.setInt(3, lang_code);
			int result=pstmt.executeUpdate();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch(Exception e){
		      e.printStackTrace();
		      return -1;
		}
	}
	
	public int getLineCount(int uid, int lang_code, int date){
		try {
			stmt=conn.createStatement();
			String sql="SELECT line_count from userlog WHERE uid=? AND lang_code=? AND date=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setInt(2, lang_code);
			pstmt.setInt(3, date);
			ResultSet result=pstmt.executeQuery();
			int count=0;
			while(result.next()){
				count=result.getInt(1);
			}
			return count;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch(Exception e){
		      e.printStackTrace();
		      return -1;
		}
	}

	public int getLineCount(int uid, int lang_code, int start_date, int end_date){
		try {
			stmt=conn.createStatement();
			String sql="SELECT SUM(line_count) from userlog WHERE uid=? AND lang_code=? AND date>=? AND date<=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setInt(2, lang_code);
			pstmt.setInt(3, start_date);
			pstmt.setInt(4, end_date);
			ResultSet result=pstmt.executeQuery();
			int count=0;
			while(result.next()){
				count=result.getInt(1);
			}
			return count;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch(Exception e){
		      e.printStackTrace();
		      return -1;
		}
	}
	
	public String getLineTrend(int uid, int lang_code, int start_date, int end_date){
		try {
			stmt=conn.createStatement();
			String sql="SELECT date,line_count from userlog WHERE uid=? AND lang_code=? AND date>=? AND date<=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setInt(2, lang_code);
			pstmt.setInt(3, start_date);
			pstmt.setInt(4, end_date);
			ResultSet result=pstmt.executeQuery();
			StringBuilder sb=new StringBuilder();
			sb.append("{");
			while(result.next()){
				sb.append("\""+result.getInt(1)+"\""+":");
				sb.append(result.getInt(2)+",");
			}
			System.out.println(sb.toString().substring(0, sb.length()-1)+"}");
			return sb.toString().substring(0, sb.length()-1)+"}";
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		} catch(Exception e){
		      e.printStackTrace();
		      return "";
		}
	}
	
	public String getAllUsers(){
		try {
			stmt=conn.createStatement();
			String sql="SELECT uid,sum(line_count) from userlog WHERE date=? GROUP BY uid";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, 151211);

			ResultSet result=pstmt.executeQuery();
			StringBuilder sb=new StringBuilder();
			sb.append("{");
			while(result.next()){
				sb.append("\""+result.getInt(1)+"\""+":");
				sb.append(result.getInt(2)+",");
			}
			System.out.println(sb.toString().substring(0, sb.length()-1)+"}");
			return sb.toString().substring(0, sb.length()-1)+"}";
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		} catch(Exception e){
		    e.printStackTrace();
		    return "";
		}
	}
	
	public int getWordCount(int uid, int lang_code, int date){
		try {
			stmt=conn.createStatement();
			String sql="SELECT word_count from userlog WHERE uid=? AND lang_code=? AND date=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setInt(2, lang_code);
			pstmt.setInt(3, date);
			ResultSet result=pstmt.executeQuery();
			int count=0;
			while(result.next()){
				count=result.getInt(1);
			}
			return count;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch(Exception e){
		      e.printStackTrace();
		      return -1;
		}
	}
	
	public int getWordCount(int uid, int lang_code, int start_date, int end_date){
		try {
			stmt=conn.createStatement();
			String sql="SELECT SUM(word_count) from userlog WHERE uid=? AND lang_code=? AND date>=? AND date<=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setInt(2, lang_code);
			pstmt.setInt(3, start_date);
			pstmt.setInt(4, end_date);
			ResultSet result=pstmt.executeQuery();
			int count=0;
			while(result.next()){
				count=result.getInt(1);
			}
			return count;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch(Exception e){
		      e.printStackTrace();
		      return -1;
		}
	}
	
	public int[] getLanguages(int uid){
		try {
			stmt=conn.createStatement();
			String sql="SELECT DISTINCT lang_code from userlog WHERE uid=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			ResultSet result=pstmt.executeQuery();
			List<Integer> list=new ArrayList<Integer>();
			while(result.next()){
				list.add(result.getInt(1));
			}
			int[] intList=new int[list.size()];
			for (int i=0; i<list.size(); i++) {
				intList[i]=list.get(i);
			}
			return intList;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return new int[0];
		} catch(Exception e){
		      e.printStackTrace();
		      return new int[0];
		}
	}
	
	

}
