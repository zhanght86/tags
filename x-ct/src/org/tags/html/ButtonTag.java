package org.tags.html;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.tags.util.TagUtil;


public class ButtonTag extends HtmlTag {
    
    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<button " + this.getAttrContent() + ">");
        if( isEmpty(value) ){
            value = TagUtil.getI18n(request, lang, globalization);
        }
        htmlContent.append(value + "</button>");
        try {
            pageContext.getOut().print(htmlContent);
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }
    
    
}
