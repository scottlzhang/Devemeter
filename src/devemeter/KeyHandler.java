package devemeter;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.nio.CharBuffer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.websocket.*;

/**
 * Servlet implementation class KeyHandler
 */
@WebServlet("/KeyHandler")
public class KeyHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WsOutbound myoutbound;
	private DbHandler db;
	private int today=151211;
	private int lweek=151207;
	private int lmonth=151107;
	
	public static int count=0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KeyHandler() {
        super();
        db=new DbHandler();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	private int getLangCode(String lang) {
		if (lang.equals("java"))
			return 1;
		else if (lang.equals("py"))
			return 2;
		else if (lang.equals("php"))
			return 3;
		else if (lang.equals("rb"))
			return 4;
		else if (lang.equals("js"))
			return 5;
		else if (lang.equals("html"))
			return 6;
		else if (lang.equals("css"))
			return 7;
		else if (lang.equals("c"))
			return 8;
		else if (lang.equals("cpp"))
			return 9;
		else
			return -1;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int uid=Integer.parseInt(request.getParameter("uid"));
		String key=request.getParameter("key");
		String ftype=request.getParameter("ftype");
		int lang_code=getLangCode(ftype);
		//response.getWriter().write(key+" received! Got it! Lang code: "+);
		
		count++;
		//WsHandler.pushMsg("Greetings! You've pushed enter key "+count+" times!");
		
		//System.out.println(list);
		
		//WsHandler ws=new WsHandler();
		db.addLine(uid, lang_code, today);
		GetGeneralData d=new GetGeneralData();
		WsHandler.pushMsg(d.getData(uid, today, today));
		//clients=ws.clients;
		//for (String key: ws.clients)
		
		
	}

}
