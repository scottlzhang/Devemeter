package devemeter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.websocket.*;

/**
 * Servlet implementation class WsHandler
 */
@WebServlet("/WsHandler")
public class WsHandler extends WebSocketServlet {
	private static final long serialVersionUID = 1L;
	// for new clients, <sessionId, streamInBound>
	 public static ConcurrentHashMap<String, StreamInbound> clients = new ConcurrentHashMap<String, StreamInbound>();
       private static String s;
	@Override
	 protected StreamInbound createWebSocketInbound(String protocol,HttpServletRequest httpServletRequest) {
		// Check if exists
		  HttpSession session = httpServletRequest.getSession();

		  // find client
		  StreamInbound client = clients.get(session.getId());
		  if (null != client) {
		   return client;

		  } else {
		   client = new MyInBound(httpServletRequest);
		   clients.put(session.getId(), client);
		   s=session.getId();
		  }

		  return client;
	}
	
	public StreamInbound getClient(String sessionId) {
		  return clients.get(sessionId);
		 }
	
	
	public static void pushMsg(String msg) {
		Enumeration<String> keys=clients.keys();
		while (keys.hasMoreElements()) {
			MyInBound c=(MyInBound) clients.get(keys.nextElement());
			c.writeMsg(msg);
			
		}
		
	}
	
	 public void addClient(String sessionId, StreamInbound streamInBound) {
		  clients.put(sessionId, streamInBound);
		 }
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WsHandler() {
        super();
        // TODO Auto-generated constructor stub
    }
	

}
