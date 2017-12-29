// package PhraseOMaticServlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class OMaticServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = "PhraseOMatic has generated the following phrase:";
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<HTML><HEAD><TITLE>");
		out.println("PhraseOMatic");
		out.println("</TITLE></HEAD><BODY>");
		out.println("<H1>" + title + "</H1>");
		out.print("<P>" + PhraseOMatic.makePhrase());
		out.println("<P><a href=\"OMaticServlet\">make another phrase</a></p>");
		out.println("</BODY></HTML>");

		out.close();
	}
}