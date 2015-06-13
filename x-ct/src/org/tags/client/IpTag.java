package org.tags.client;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class IpTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String ip = request.getRemoteAddr();
        try {
            pageContext.getOut().print(ip);
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }
}
