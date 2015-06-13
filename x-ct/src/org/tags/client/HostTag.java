package org.tags.client;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
/**
 * 会有性能问题出现
 */
public class HostTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String host = request.getRemoteHost();
        try {
            pageContext.getOut().print(host);
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }
}
