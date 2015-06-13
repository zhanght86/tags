package org.tags.html;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.tags.util.TagUtil;

public class ATag extends HtmlTag {

    private static final long serialVersionUID = 1L;
    
    private String target;
    private String href;
    
    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<a ");
        htmlContent.append(getAttrContent());
        if( isNotEmpty(tabindex) ){
            htmlContent.append(" target='" + this.target + "' ");
        }
        if( isNotEmpty(href) ){
            htmlContent.append(" href='" + this.href + "' ");
        }
        htmlContent.append(" >");
        if( isNotEmpty(value) ){
            htmlContent.append(value);
        }else {
            htmlContent.append(TagUtil.getI18n(request, lang, globalization));
        }
        htmlContent.append(" </a> ");
        try {
            pageContext.getOut().print(htmlContent);
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
    
    
}
