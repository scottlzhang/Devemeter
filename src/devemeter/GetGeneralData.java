package devemeter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetGeneralData
 */
@WebServlet("/GetGeneralData")
public class GetGeneralData extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetGeneralData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				DbHandler db=new DbHandler();
				PrintWriter out = response.getWriter();
				int uid=Integer.parseInt(request.getParameter("uid")); 
				int date1=Integer.parseInt(request.getParameter("date1"));
				int date2=Integer.parseInt(request.getParameter("date2"));
				int lineCount;
				String json="{\"uid\"="+uid+"\"general\":{";
				int[] lang_codes=db.getLanguages(uid);
				for (int i=0; i<lang_codes.length; i++) {
					int lang_code=Integer.parseInt(request.getParameter("lang_code"));
					if (date1==date2) {
						lineCount=db.getLineCount(uid, lang_code, date1);
					} else {
						lineCount=db.getLineCount(uid, lang_code, date1, date2);
					}
					json=json+"\""+lang_code+"\":"+lineCount+",";
				}
				json=json+"}}";
	}
	
	public String getData(int uid, int date1, int date2) {
		int lineCount;
		DbHandler db=new DbHandler();
		String json="{\"uid\":\""+uid+"\",\"general\":{";
		int[] lang_codes=db.getLanguages(uid);
		for (int i=0; i<lang_codes.length; i++) {
			int lang_code=lang_codes[i];
			if (date1==date2) {
				lineCount=db.getLineCount(uid, lang_code, date1);
			} else {
				lineCount=db.getLineCount(uid, lang_code, date1, date2);
			}
			if (i==lang_codes.length-1) {
				json=json+"\""+lang_code+"\":"+lineCount;
			} else {
				json=json+"\""+lang_code+"\":"+lineCount+",";
			}
		}
		json=json+"}}";
		return json;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		int uid=Integer.parseInt(request.getParameter("uid")); 
		int date1=Integer.parseInt(request.getParameter("date1"));
		int date2=Integer.parseInt(request.getParameter("date2"));
		String json=getData(uid,date1,date2);
		out.print(json);
	}

}
