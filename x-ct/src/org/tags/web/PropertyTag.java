package org.tags.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.tags.util.TagUtil;

public class PropertyTag extends TagSupport {
    
    private static final long serialVersionUID = 1L;
    
    private String key;
    
    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        try {
            if( TagUtil.isEmpty(key) ){
                pageContext.getOut().print("");
                return SKIP_BODY;
            }
            IteratorTag iteratorTag = TagUtil.getIteratorTag(this);
            if( iteratorTag != null ){
                pageContext.getOut().print(TagUtil.getObjectByIteratorTag(request, iteratorTag, key));
            }else {
                pageContext.getOut().print(TagUtil.getObjectByRequest(request, key));
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
