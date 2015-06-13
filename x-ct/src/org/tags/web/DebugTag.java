package org.tags.web;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class DebugTag extends TagSupport {

    private static final long serialVersionUID = 1L;
    
    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        StringBuilder html = new StringBuilder();
        html.append("<div id='debug_details'>");
        html.append("    <style type='text/css'>");
        html.append("        #debug { background: #eee; border-collapse: collapse; width:100%; font-family: consolas; font-size: 13px; cursor:default;}");
        html.append("        #debug tbody tr td { border: 1px solid #E7E7E7; height: 25px; }");
        html.append("        #debug [name='header'] { background: #80A7E5; color: #fff; border:1px solid #80A7E5; height: 25px;}");
        html.append("        #debug [name='title'] { padding-left: 10px; color: #FF7F00;}");
        html.append("        #debug [name='title'],[name='value']:hover { background: #ECFBFD; }");
        html.append("        #debug [name='key'],[name='value'] { background: #F7FBFC; }");
        html.append("        #debug [name='key'] { padding-left: 50px; }");
        html.append("        #debug [name='value'] { padding-left: 10px; }");
        html.append("        #debug [name='value']:hover { color:#FF7F00;}");
        html.append("    </style>");
        html.append("    <table id='debug'>");
        html.append("        <thead id='debug_thead'>");
        html.append("            <tr align='center'>");
        html.append("                 <td width='25%' name='header' ><strong>key</strong></td>");
        html.append("                 <td width='75%' name='header' ><strong>value</strong></td>");
        html.append("            </tr>");
        html.append("        </thead>");
        html.append("        <tbody id='debug_tbody'>");
        /* request */
        html.append("            <tr>");
        html.append("                <td colspan='2' name='title'><strong>Request:</strong></td>");
        html.append("            </tr>");
        Enumeration<?> requestAttrNames = request.getAttributeNames();
        while( requestAttrNames.hasMoreElements() ){
            String key = requestAttrNames.nextElement().toString();
            Object value = request.getAttribute(key);
            html.append("        <tr>");
            html.append("            <td name='key'>" + key + "</td>");
            html.append("            <td name='value'>" + value + "</td>");
            html.append("        </tr>");
        }
        /* session */
        html.append("            <tr>");
        html.append("                <td colspan='2' name='title'><strong>Session:</strong></td>");
        html.append("            </tr>");
        Enumeration<?> sessionAttrNames = request.getSession().getAttributeNames();
        while ( sessionAttrNames.hasMoreElements() ) {
            String key = sessionAttrNames.nextElement().toString();
            Object value = request.getSession().getAttribute(key);
            html.append("        <tr>");
            html.append("            <td name='key'>" + key + "</td>");
            html.append("            <td name='value'>" + value + "</td>");
            html.append("        </tr>");
        }
        html.append("            <tr>");
        html.append("                <td colspan='2' name='title'>");
        html.append("                    <strong>提示: 点击表格头可隐藏debug内容</strong><br/>");
        html.append("                    <strong>Hint: Click the table header to hide the debug content</strong>");
        html.append("                </td>");
        html.append("            </tr>");
        html.append("        </tbody>");
        html.append("    </table>");
        html.append("    <script>");
        html.append("        $('#debug_thead').click(function(){");
        html.append("            $('#debug_tbody').toggle(25);");
        html.append("        });");
        html.append("    </script>");
        html.append("</div>");
        try {
            pageContext.getOut().print(html);
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

}
