package org.tags.client;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class PortTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        int port = request.getRemotePort();
        try {
            pageContext.getOut().print(port);
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }
}
