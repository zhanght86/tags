package org.tags.html;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.tags.util.TagUtil;

public class TextareaTag extends HtmlTag {

    private static final long serialVersionUID = 1L;
    
    private int cols;
    private int rows;
    
    
    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<textarea " + getAttrContent() + " cols='" + cols + "' rows='" + this.rows + "' ");
        htmlContent.append(" placeholder='" + TagUtil.getI18n(request, lang, globalization) + "' ");
        htmlContent.append(">");
        htmlContent.append("</textarea>");
        try {
            pageContext.getOut().print(htmlContent);
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
    
}
