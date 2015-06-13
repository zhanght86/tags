package org.tags.html;

import javax.servlet.jsp.tagext.TagSupport;

public class HtmlTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    
    /* 国际化 */
    protected String globalization;//MasterData对象中的key
    protected String lang;
    protected String key;
    
    /* 基础属性 */
    protected String id;
    protected String name;
    protected String size;
    protected String style;
    protected String title;
    protected String classStyle;
    protected String value;//子类实现 TODO
    protected String type;//input 类型
    protected String tabindex;
    /* 事件 */
    protected String onblur;
    protected String onchange;
    protected String onclick;
    protected String ondblclick;
    protected String onfocus;
    protected String onhelp;
    protected String onkeydown;
    protected String onkeypress;
    protected String onkeyup;
    protected String onmousedown;
    protected String onmousemove;
    protected String onmouseout;
    protected String onmouseover;
    protected String onmouseup;
    protected boolean readOnly;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getStyle() {
        return style;
    }
    public void setStyle(String style) {
        this.style = style;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getOnblur() {
        return onblur;
    }
    public void setOnblur(String onblur) {
        this.onblur = onblur;
    }
    public String getOnchange() {
        return onchange;
    }
    public void setOnchange(String onchange) {
        this.onchange = onchange;
    }
    public String getOnclick() {
        return onclick;
    }
    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }
    public String getOndblclick() {
        return ondblclick;
    }
    public void setOndblclick(String ondblclick) {
        this.ondblclick = ondblclick;
    }
    public String getOnfocus() {
        return onfocus;
    }
    public void setOnfocus(String onfocus) {
        this.onfocus = onfocus;
    }
    public String getOnhelp() {
        return onhelp;
    }
    public void setOnhelp(String onhelp) {
        this.onhelp = onhelp;
    }
    public String getOnkeydown() {
        return onkeydown;
    }
    public void setOnkeydown(String onkeydown) {
        this.onkeydown = onkeydown;
    }
    public String getOnkeypress() {
        return onkeypress;
    }
    public void setOnkeypress(String onkeypress) {
        this.onkeypress = onkeypress;
    }
    public String getOnkeyup() {
        return onkeyup;
    }
    public void setOnkeyup(String onkeyup) {
        this.onkeyup = onkeyup;
    }
    public String getOnmousedown() {
        return onmousedown;
    }
    public void setOnmousedown(String onmousedown) {
        this.onmousedown = onmousedown;
    }
    public String getOnmousemove() {
        return onmousemove;
    }
    public void setOnmousemove(String onmousemove) {
        this.onmousemove = onmousemove;
    }
    public String getOnmouseout() {
        return onmouseout;
    }
    public void setOnmouseout(String onmouseout) {
        this.onmouseout = onmouseout;
    }
    public String getOnmouseover() {
        return onmouseover;
    }
    public void setOnmouseover(String onmouseover) {
        this.onmouseover = onmouseover;
    }
    public String getOnmouseup() {
        return onmouseup;
    }
    public void setOnmouseup(String onmouseup) {
        this.onmouseup = onmouseup;
    }
    public boolean isReadOnly() {
        return readOnly;
    }
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
    
    public String getGlobalization() {
        return globalization;
    }
    public void setGlobalization(String globalization) {
        this.globalization = globalization;
    }
    public String getClassStyle() {
        return classStyle;
    }
    public void setClassStyle(String classStyle) {
        this.classStyle = classStyle;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getLang() {
        return lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTabindex() {
        return tabindex;
    }
    public void setTabindex(String tabindex) {
        this.tabindex = tabindex;
    }
    public String getAttrContent(){
        StringBuilder attrInfo = new StringBuilder();
        if( isNotEmpty(this.id) ){
            attrInfo.append(" id='" + this.id + "'");
        }
        if( isNotEmpty(this.name) ){
            attrInfo.append(" name='" + this.name + "'");
        }
        if( isNotEmpty(this.size) ){
            attrInfo.append(" size='" + this.size + "'");
        }
        if( isNotEmpty(this.style) ){
            attrInfo.append(" style='" + this.style + "'");
        }
        if( isNotEmpty(this.tabindex) ){
            attrInfo.append(" tabindex='" + this.tabindex + "'");
        }
        if( isNotEmpty(this.title) ){
            attrInfo.append(" title='" + this.title + "'");
        }
        if( isNotEmpty(this.classStyle) ){
            attrInfo.append(" class='" + this.classStyle + "'");
        }
        if( isNotEmpty(this.type) ){
            attrInfo.append(" type='" + this.type + "'");
        }
        if( isNotEmpty(this.onblur) ){
            attrInfo.append(" onblur='" + this.onblur + "'");
        }
        if( isNotEmpty(this.onchange) ){
            attrInfo.append(" onchange='" + this.onchange + "'");
        }
        if( isNotEmpty(this.onclick) ){
            attrInfo.append(" onclick='" + this.onclick + "'");
        }
        if( isNotEmpty(this.ondblclick) ){
            attrInfo.append(" ondblclick='" + this.ondblclick + "'");
        }
        if( isNotEmpty(this.onfocus) ){
            attrInfo.append(" onfocus='" + this.onfocus + "'");
        }
        if( isNotEmpty(this.onhelp) ){
            attrInfo.append(" onhelp='" + this.onhelp + "'");
        }
        if( isNotEmpty(this.onkeydown) ){
            attrInfo.append(" onkeydown='" + this.onkeydown + "'");
        }
        if( isNotEmpty(this.onkeypress) ){
            attrInfo.append(" onkeypress='" + this.onkeypress + "'");
        }
        if( isNotEmpty(this.onkeyup) ){
            attrInfo.append(" onkeyup='" + this.onkeyup + "'");
        }
        if( isNotEmpty(this.onmousedown) ){
            attrInfo.append(" onmousedown='" + this.onmousedown + "'");
        }
        if( isNotEmpty(this.onmousemove) ){
            attrInfo.append(" onmousemove='" + this.onmousemove + "'");
        }
        if( isNotEmpty(this.onmouseout) ){
            attrInfo.append(" onmouseout='" + this.onmouseout + "'");
        }
        if( isNotEmpty(this.onmouseover) ){
            attrInfo.append(" onmouseover='" + this.onmouseover + "'");
        }
        if( isNotEmpty(this.onmouseup) ){
            attrInfo.append(" onmouseup='" + this.onmouseup + "'");
        }
        if( this.readOnly == true ){
            attrInfo.append(" disabled='disabled'");
        }
        return attrInfo.toString();
    }
    
    public boolean isNotEmpty(String source){
        return (source != null && !"".equals(source.trim())) ? true : false;
    }
    
    public boolean isEmpty(String source){
        return (source == null || "".equals(source.trim())) ? true : false;
    }
}
