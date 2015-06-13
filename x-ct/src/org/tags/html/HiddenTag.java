package org.tags.html;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.tags.util.TagUtil;
import org.tags.web.IteratorTag;

public class HiddenTag extends HtmlTag {

    private static final long serialVersionUID = 1L;
    
    private String key;
    
    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<input type='hidden' " + this.getAttrContent() + " value='");
        IteratorTag iteratorTag = TagUtil.getIteratorTag(this);
        Object value = null;
        try {
            if( iteratorTag != null ){
                value = TagUtil.getObjectByIteratorTag(request, iteratorTag, key);
            }else {
                value = TagUtil.getObjectByRequest(request, key);
            }
            htmlContent.append(value);
            htmlContent.append("'/>");
            pageContext.getOut().print(htmlContent);
        } catch ( Exception e1 ) {
            e1.printStackTrace();
        }
        return super.doStartTag();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
