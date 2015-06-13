package org.tags.html;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.tags.util.TagUtil;

public class InputTag extends HtmlTag {

    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<input " + this.getAttrContent());
        if( type.equalsIgnoreCase("button") ){
            htmlContent.append(" value='" + TagUtil.getI18n(request, lang, globalization) + "'");
        }else if( type.equalsIgnoreCase("radio") 
                || type.equalsIgnoreCase("checkbox") 
                || type.equalsIgnoreCase("image") 
                || type.equalsIgnoreCase("radio")
                || type.equalsIgnoreCase("file")){
            //不做任何事情
        } else {
            htmlContent.append(" placeholder='" + TagUtil.getI18n(request, lang, globalization) + "' ");
        }
        htmlContent.append("/>");
        
        try {
            pageContext.getOut().print(htmlContent);
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }
    
}
