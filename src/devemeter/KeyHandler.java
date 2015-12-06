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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String key=request.getParameter("key");
		response.getWriter().write(key+" received! Got it!");
		
		count++;
		WsHandler.pushMsg("Greetings! You've pushed enter key "+count+" times!");
		//db.removeLine(1,1,151201,1);
		List<Integer> list=db.getLanguages(1);
		System.out.println(list);
		//WsHandler ws=new WsHandler();
		
		//clients=ws.clients;
		//for (String key: ws.clients)
		
		
	}

}
