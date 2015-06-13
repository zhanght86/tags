package org.tags.console;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class PrintlnTag extends TagSupport{

    private static final long serialVersionUID = 1L;

    private Object message;
    
    @Override
    public int doStartTag() throws JspException {
        System.out.println(message);
        return super.doStartTag();
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

 
}
