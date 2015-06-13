package org.tags.web;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.tags.util.TagUtil;

@SuppressWarnings("unchecked")
public class IteratorTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private String items;
    private String var = "";
    private Map<String, Object> valueMap = new LinkedHashMap<String, Object>();
    private Iterator<Map.Entry<String, Object>> iterator;
    
    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        IteratorTag iteratorTag = TagUtil.getIteratorTag(this);
        Object targetObject = null;
        try {
            if( iteratorTag != null ){
                targetObject = TagUtil.getObjectByIteratorTag(request, iteratorTag, items);
            }else {
                targetObject = TagUtil.getObjectByRequest(request, items);
            }
            convert2Map(targetObject);
            if( valueMap == null || valueMap.isEmpty() ){
                return SKIP_BODY;
            }
            return EVAL_BODY_INCLUDE;
        } catch ( Exception e1 ) {
            e1.printStackTrace();
        }
        return SKIP_BODY;
    }

    private void convert2Map(Object targetObj) {
        if( targetObj == null ){
            return;
        }
        if( targetObj instanceof List ){
            List itemList = (List) targetObj;
            for ( int i = 0; i < itemList.size(); i++ ) {
                valueMap.put(i + "", itemList.get(i));
            }
        }else if( targetObj instanceof Map ){
            valueMap = (Map) targetObj;
        }else if( targetObj instanceof Set ){
            Set itemSet = (Set) targetObj;
            int tmp_idx = 0;
            for (Object obj : itemSet) {
                valueMap.put(tmp_idx + "", obj);
                tmp_idx += 1;
            } 
        }
        iterator = valueMap.entrySet().iterator();
        if( iterator != null && iterator.hasNext() ){
            var = iterator.next().getKey();
        }
    }
    
    @Override
    public int doAfterBody() throws JspException {
        if( iterator != null && iterator.hasNext() ){
            if( iterator != null && iterator.hasNext() ){
                var = iterator.next().getKey();
            }
            return EVAL_BODY_AGAIN;
        }else {
            return SKIP_BODY;
        }
    }
    
    @Override
    public int doEndTag() throws JspException {
        release();
        return super.doEndTag();
    }
    
    @Override
    public void release() {
        iterator = null;
        var = "";
        super.release();
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public Map<String, Object> getValueMap() {
        return valueMap;
    }

    public void setValueMap(Map<String, Object> valueMap) {
        this.valueMap = valueMap;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }
    
    
}
