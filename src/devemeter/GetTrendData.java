package devemeter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetTrendData
 */
@WebServlet("/GetTrendData")
public class GetTrendData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTrendData() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public int rNum() {
    	Random rand=new Random();
    	return rand.nextInt((20-0)+1);
    }

    public String getData(int uid, int date1, int date2) {
		
		Random rand=new Random();
		DbHandler db=new DbHandler();
		String json="{\"uid\":\""+uid+"\",\"trend\":{";
		int[] lang_codes=db.getLanguages(uid);
		//lang_codes=new int[]{1,2,3};
		for (int i=0; i<lang_codes.length; i++) {
			String lineCounts;
			int lang_code=lang_codes[i];
			if (date1<date2) {
				lineCounts=db.getLineTrend(uid, lang_code, date1, date2);
				//lineCounts=new String[]{"\"151205\":"+rNum(),"\"151208\":"+rNum(),"\"151210\":"+rNum(),"\"151211\":"+rNum()};
			} else {
				return "invalid dates";
			}
			json=json+"\""+lang_code+"\":"+lineCounts;
			if (i!=lang_codes.length-1)
				json=json+",";
			/*
			for (int j=0; j<lineCounts.length; j++) { 
				if (j!=lineCounts.length-1) {
					json=json+lineCounts[j]+",";
				} else {
					json=json+lineCounts[j]+"}";
				}
			}
			if (i!=lang_codes.length-1) {
				json=json+",";
			} else {
				json=json+"}";
			}*/
		}
		json=json+"}}";
		return json;
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		int uid=Integer.parseInt(request.getParameter("uid")); 
		int date1=Integer.parseInt(request.getParameter("date1"));
		int date2=Integer.parseInt(request.getParameter("date2"));
		String json=getData(uid,date1,date2);
		out.print(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
