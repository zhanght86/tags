package org.tags.html;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.tags.util.TagUtil;
import org.tags.web.IteratorTag;

@SuppressWarnings("unchecked")
public class SelectTag extends HtmlTag {
    private static final long serialVersionUID = 1L;
    private String items;//数据来源
    private String desc;//下拉列表显示的内容
    private String blankSpace;//请选择
    private String currValue;//当前选中
    
    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<select " + this.getAttrContent() + ">");
        if( isNotEmpty(globalization) ){
            htmlContent.append(" <option value=''>" + TagUtil.getI18n(request, null, globalization) + "</option>");
        }else {
            if( isNotEmpty(blankSpace) ){
                htmlContent.append(" <option value=''>" + blankSpace + "</option>");
            }
        }
        IteratorTag iteratorTag = TagUtil.getIteratorTag(this);
        Object sourceObject = null;
        Object currSelect = null;
        try {
            if( iteratorTag != null ){
                sourceObject = TagUtil.getObjectByIteratorTag(request, iteratorTag, items);
            }else {
                sourceObject = TagUtil.getObjectByRequest(request, items);
            }
            currSelect = TagUtil.getObjectByRequest(request, currValue);
            if( sourceObject != null ){
                convert(htmlContent, sourceObject, request, (currSelect == null ? "" : currSelect.toString()));
            }
            htmlContent.append("</select>");
            pageContext.getOut().print(htmlContent);
        } catch ( Exception e1 ) {
            e1.printStackTrace();
        }
        return SKIP_BODY;
    }

    private void convert(StringBuilder htmlContent, Object sourceObject, HttpServletRequest request, String currSelect) {
        if( sourceObject == null ){
            return;
        }
        if( sourceObject instanceof List ){
            List list = (List) sourceObject;
            for ( int i = 0; i < list.size(); i++ ) {
                Object src = list.get(i);
                htmlContent.append(getOptionContent(src, currSelect));
            }
        }else if( sourceObject instanceof Map ){
            Map map = (Map) sourceObject;
            Iterator it = map.keySet().iterator();
            while( it.hasNext() ){
                Object key = it.next();
                Object val = map.get(key);
                htmlContent.append(getOptionContent(val, currSelect));
            }
        }else if( sourceObject instanceof Set ){
            Set itemsSet = (Set) sourceObject;
            for (Object src : itemsSet) {
                htmlContent.append(getOptionContent(src, currSelect));
            } 
        }else {
            Field field = getObjectAttr(sourceObject, items);
            try {
                Object tmpObj = field.get(sourceObject);
                convert(htmlContent, tmpObj, request, currSelect);
            } catch ( Exception e ) {
               Object tmpObj = getTargetObjectByRequest(request);
               if( tmpObj == null ){
                   return;
               }
               convert(htmlContent, tmpObj, request, currSelect);
            }
        }
    }

    private String getOptionContent(Object src, String currSelect) {
        Field descField = getObjectAttr(src, desc);
        Field valField = getObjectAttr(src, value);
        String optionContent = "<option value='{0}' {1}>{2}</option>";
        optionContent = convertOptionContent(src, descField, optionContent, "{2}");
        optionContent = convertOptionContent(src, valField, optionContent, "{0}");
        if( valField == null ){
            optionContent = optionContent.replace("{1}", "");
        }else {
            String value = "";
            try {
                value = valField.get(src).toString();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
            if( value.equals(currSelect) ){
                optionContent = optionContent.replace("{1}", "selected");
            }else {
                optionContent = optionContent.replace("{1}", "");
            }
        }
        return optionContent;
    }

    private String convertOptionContent(Object src, Field field, String optionContent, String key) {
        if( field != null ){
            try {
                String tmpValue = field.get(src).toString();
                optionContent = optionContent.replace(key, tmpValue);
            } catch ( Exception e ) {
                optionContent = optionContent.replace(key, "");
                optionContent = optionContent.replace("{1}", "");
            }
        }else {
            optionContent = optionContent.replace(key, "");
            optionContent = optionContent.replace("{1}", "");
        }
        return optionContent;
    }

    private Field getObjectAttr(Object src, String key) {
        Field field = null;
        try {
            field = src.getClass().getDeclaredField(key);
            Field.setAccessible(new Field[]{field}, true);
        } catch ( Exception e ) {
//            new AttributeNotFoundException("对象中没有属性 : " + key, e).printStackTrace();
        }
        return field;
    }

    private Object getTargetObjectByRequest(HttpServletRequest request) {
        Object targetObject = null;
        String root = "";
        String[] objChain = null;
        if( items.contains(".") ){
            objChain = items.split(".");
            root = objChain[0];
        }else {
            root = items;
        }
        targetObject = request.getAttribute(root);
        if( targetObject == null ){//从session中取
            targetObject = request.getSession().getAttribute(root);
        }
        if( targetObject == null ){
            return null;
        }
        if( objChain != null ){
            for(int i=1; i<objChain.length; i++){
                try {
                    Field field = targetObject.getClass().getDeclaredField( objChain[i] );
                    targetObject = field.get(targetObject);
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        }
        return targetObject;
    }
    
    public String getItems() {
        return items;
    }
    public void setItems(String items) {
        this.items = items;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getClassStyle() {
        return classStyle;
    }
    public void setClassStyle(String classStyle) {
        this.classStyle = classStyle;
    }

    public String getBlankSpace() {
        return blankSpace;
    }

    public void setBlankSpace(String blankSpace) {
        this.blankSpace = blankSpace;
    }

    public String getCurrValue() {
        return currValue;
    }

    public void setCurrValue(String currValue) {
        this.currValue = currValue;
    }
    
}
