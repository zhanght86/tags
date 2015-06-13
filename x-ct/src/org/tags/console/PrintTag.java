package org.tags.console;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class PrintTag extends TagSupport{

    private static final long serialVersionUID = 1L;

    private Object message;
    
    @Override
    public int doStartTag() throws JspException {
        System.out.print(message);
        return super.doStartTag();
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

  
    
}
