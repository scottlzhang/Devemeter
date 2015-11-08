package devemeter;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

/**
 * Need tomcat-koyote.jar on class path, otherwise has compile error "the hierarchy of the type ... is inconsistent"
 * @author wangs
 *
 */
public class MyInBound extends MessageInbound{

 private String name;
 
 public WsOutbound myoutbound;
 
 public MyInBound(HttpServletRequest httpSerbletRequest) {
  
 }
 @Override
 public void onOpen(WsOutbound outbound) {
  System.out.println("on open..");
  this.myoutbound = outbound;
  try {
   this.myoutbound.writeTextMessage(CharBuffer.wrap("Yo! What's up?"));
   
  } catch (Exception e) {
   throw new RuntimeException(e);
  }
  
 }
 
 public void writeMsg(String str) {
	 try {
		 System.out.print(str);
		this.myoutbound.writeTextMessage(CharBuffer.wrap(str));
		//this.myoutbound.flush();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
 
 @Override
 public void onClose(int status) {
  System.out.println("Close client");
  //remove from list
 }
 
 @Override
 protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
  
 }

 @Override
 protected void onTextMessage(CharBuffer inChar) throws IOException {
  
  System.out.println("Accept msg");
  
  WsHandler.pushMsg(inChar.toString());
  //this.myoutbound.flush();
  /*
  CharBuffer outbuf = CharBuffer.wrap("- " + this.name + " says : ");
  CharBuffer buf = CharBuffer.wrap(inChar);
  
  if(name != null) {
   this.myoutbound.writeTextMessage(outbuf);
   this.myoutbound.writeTextMessage(buf);
  } else {
   this.name = inChar.toString();
   
   CharBuffer welcome = CharBuffer.wrap("== Welcome " + this.name + "!");
   this.myoutbound.writeTextMessage(welcome);
  }
  
  this.myoutbound.flush();*/
  
 }

}