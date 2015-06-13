package org.tags.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.tags.util.TagUtil;

public class IfTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    private boolean flag;

    private String exp;

    private String eq;
    
    private boolean object;//如果true,则将表达式当成对象处理, 否则当成普通文本处理
    
    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        if ( TagUtil.isEmpty(exp) ) {
            if ( flag ) {
                return EVAL_BODY_INCLUDE;
            } else {
                return SKIP_BODY;
            }
        } else {// 解析表达式
            eq = TagUtil.analyzeExp(exp);
            String ltKey = exp.split(eq)[0].trim();
            String rtKey = exp.split(eq)[1].trim();
            IteratorTag iteratorTag = TagUtil.getIteratorTag(this);
            Object leftValue = getObject(ltKey, iteratorTag, request, ltKey);
            Object rightValue = getObject(rtKey, iteratorTag, request, rtKey);
            return TagUtil.equalsObject(leftValue, rightValue, eq);
        }
    }

    private Object getObject(String key, IteratorTag iteratorTag, HttpServletRequest request, String srcVal){
        Object value = null;
        try {
            if( iteratorTag == null ){
                value = TagUtil.getObjectByRequest(request, key);
            }else {
                value = TagUtil.getObjectByIteratorTag(request, iteratorTag, key);
            }
            if( value != null || object){
                return value;
            }
            
            return srcVal;
        } catch ( Exception e ) {
            
        }
        return srcVal;
    }
    
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public boolean isObject() {
        return object;
    }

    public void setObject(boolean object) {
        this.object = object;
    }
}
