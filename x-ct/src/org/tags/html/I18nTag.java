package org.tags.html;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.tags.util.TagUtil;

public class I18nTag extends HtmlTag {
    
    private static final long serialVersionUID = 1L;
    
    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        try {
            pageContext.getOut().print(TagUtil.getI18n(request, lang, key));
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
