package org.ekber.customtag;

import java.io.IOException;

import javax.el.ValueExpression;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.servlet.http.HttpServletRequest;

public class PrintStarComponent extends UIOutput {

	private String articleId;

	public String getArticleId() {
		if (articleId != null)
			return articleId;

		// If the articleId has not already been set interpret the Expression
		// language and get resulting object
		Object articleId = this.getValueExpressionValue("articleId");
		return articleId != null ? (String) articleId : null;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public void encodeBegin(FacesContext facesContext) throws IOException {

		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();

		ResponseWriter writer = facesContext.getResponseWriter();

		starGenerator(writer, request.getContextPath(), this.getArticleId());

	}

	private void starGenerator(ResponseWriter writer, String path,
			String articleId) throws IOException {

		writer.startElement("div", this);
		writer.writeAttribute("id", "rating-container", null);

		writer.startElement("span", this);
		writer.writeAttribute("id", "rating-header", null);
		writer.writeText("  Bu Yazi Yararli Oldu mu?  ", null);
		writer.endElement("span");

		writer.startElement("p", this);

		for (int i = 1; i <= 5; i++) {
			writer.startElement("img", this);
			writer.writeAttribute("src", path + "/resources/img/rating_off.png", null);
			writer.writeAttribute("style", "cursor: pointer; border: 0px;", null);
			writer.writeAttribute("onkeypress", "rate_post();", null);
			writer.writeAttribute("onclick", "rate_post();", null);
			writer.writeAttribute("onmouseout", "ratings_off(0);", null);
			writer.writeAttribute("onmouseover", "current_rating(" + articleId + ", " + i + ", '" + i + " Yildiz');", null);
			writer.writeAttribute("title", i + " Star", null);
			writer.writeAttribute("alt", i + " Star", null);
			writer.writeAttribute("id", "rating_" + articleId + "_" + i, null);
			writer.endElement("img");
		}

		writer.endElement("p");
		writer.endElement("div");

	}

	public Object saveState(FacesContext context) {
		Object[] rtrn = new Object[2];
		rtrn[0] = super.saveState(context);
		rtrn[1] = articleId;
		return rtrn;
	}

	public void restoreState(FacesContext context, Object state) {
		Object[] values = (Object[]) state;
		super.restoreState(context, values[0]);
		articleId = (String) values[1];
	}

	private Object getValueExpressionValue(String name) {
		ValueExpression ve = super.getValueExpression(name);
		return ve.getValue(FacesContext.getCurrentInstance().getELContext());
	}

}
