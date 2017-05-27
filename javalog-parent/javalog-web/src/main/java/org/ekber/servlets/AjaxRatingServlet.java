package org.ekber.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ekber.bean.MakaleBean;
import org.ekber.domain.Article;
import org.ekber.domain.UserRate;
import org.ekber.service.interfaces.IArticleService;
import org.ekber.service.interfaces.IUserRateService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class AjaxCheckUsernameServlet
 */
@WebServlet("/page/rating")
public class AjaxRatingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private IUserRateService ursi;
	private IArticleService artService;

    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ursi = (IUserRateService) ctx.getBean("userRateService");
		artService = (IArticleService) ctx.getBean("articleService");
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		MakaleBean makaleBean = (MakaleBean) request.getSession().getAttribute("makaleBean");
		Article a = makaleBean.getSelectedArticle();
		
		String pid = request.getParameter("pid");
		String rate = request.getParameter("rate");
		
		if((pid != null && !"".equals(pid)) &&
			rate != null && !"".equals(rate)) {

			try {

				int id = new Integer(pid);

				UserRate r = new UserRate();
				r.setArticlesrate(a);
				r.setRaterIp(request.getRemoteAddr());
				r.setRate(new Integer(rate));
				r.setRatedDate(new Date());

				ursi.addRate(r);

				String avg = ursi.calculateAverageRateByArticle(id);

				if (!"".equals(avg)) {

					String[] temp = avg.split(";");

					String html = "<div id=\"rating-container\">"
							+ "<span id=\"rating-header\">&nbsp;&nbsp;Degerlendirmeniz icin tesekkurler.&nbsp;&nbsp;</span>"
							+ "<p>";

					String s = temp[0];
					float eko = Float.parseFloat(s);

					for (int i = 1; i <= 5; i++) {
						if(i <= eko){
							html = html.concat("<img class=\"post-ratings-image\" title=\""
											+ temp[1]
											+ " kez degerlendirildi, ortalama: "
											+ temp[0]
											+ " out of 5\" alt=\""
											+ temp[1]
											+ " kez degerlendirildi, ortalama: "
											+ temp[0]
											+ " out of 5\" src=\"/javalog-web/resources/img/rating_on.png\">");
						}else{
							if(i <= (eko + 0.5)){
								html = html.concat("<img class=\"post-ratings-image\" title=\""
										+ temp[1]
										+ " kez degerlendirildi, ortalama: "
										+ temp[0]
										+ " out of 5\" alt=\""
										+ temp[1]
										+ " kez degerlendirildi, ortalama: "
										+ temp[0]
										+ " out of 5\" src=\"/javalog-web/resources/img/rating_half.png\">");
							}else{
								html = html.concat("<img class=\"post-ratings-image\" title=\""
										+ temp[1]
										+ " kez degerlendirildi, ortalama: "
										+ temp[0]
										+ " out of 5\" alt=\""
										+ temp[1]
										+ " kez degerlendirildi, ortalama: "
										+ temp[0]
										+ " out of 5\" src=\"/javalog-web/resources/img/rating_off.png\">");
							}
						}
					}

					html = html.concat("<span id=\"rating-text\">" + "<strong>"
							+ temp[1] + "</strong> kez degerlendirildi | <strong>" + temp[0]
							+ "</strong> ortalama" + "</span>" + "</p>"
							+ "</div>");
					

					out.print(html);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				out.print("server da bir hata olustu !" + e.getMessage());
			}

		}else{
			
			out.print("server a giden parametreleri kontrol ediniz !");
		}

	}
}
