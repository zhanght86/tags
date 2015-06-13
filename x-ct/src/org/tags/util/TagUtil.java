package org.tags.util;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.Tag;

import org.tags.bean.Globalization;
import org.tags.constant.Constant;
import org.tags.web.IteratorTag;

public class TagUtil {
    private static final int SKIP_BODY = 0;
    private static final int EVAL_BODY_INCLUDE = 1;
    
    /**
     * 获取父tag
     */
    public static IteratorTag getIteratorTag(Tag currTag) {
        Tag tag = currTag.getParent();
        if( tag == null ){
            return null;
        }
        if( tag instanceof IteratorTag ){
            return (IteratorTag) tag;
        }else {
            return getIteratorTag(tag);
        }
    }
    
    /**
     * 从request中取值<br/>
     * value查找顺序 : iteratorTag --> reqeust --> session
     */
    public static Object getObjectByRequest(HttpServletRequest request, String key){
        Object value = null;
        if( key.contains(".") ){
            StringTokenizer token = new StringTokenizer(key, ".");
            while( token.hasMoreTokens()){
                String tmpKey = token.nextToken();
                try {
                    value = getObject(request, value, tmpKey);
                    if( value != null ){
                        Field field = value.getClass().getDeclaredField( token.nextToken() );
                        Field.setAccessible(new Field[]{field}, true);
                        value = field.get(value);
                    }
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        }else {
            value = getObject(request, value, key);
        }
        return value;
    }
    
    public static Object getObject(HttpServletRequest request, Object value, String key) {
        if( value == null ){
            value = request.getAttribute(key);
        }
        if( value == null ){
            value = request.getSession().getAttribute(key);
        }
        return value;
    }
    
    /**
     * 从iteratorTag中取值<br/>
     * value查找顺序 : reqeust --> session
     */
    public static Object getObjectByIteratorTag(HttpServletRequest request, IteratorTag iteratorTag, String key) throws Exception{
        if( key == null ){
            return null;
        }
        Map<String, Object> valueMap = iteratorTag.getValueMap();//集合对象
        Object targetObject = valueMap.get(iteratorTag.getVar());
        if( targetObject == null ){
            targetObject = getObjectByRequest(request, key);
        }
        if( targetObject == null ){
            return null;
        }
        if( key.contains(".") ){
            StringTokenizer token = new StringTokenizer(key, ".");
            while( token.hasMoreTokens()){
                Field field = targetObject.getClass().getDeclaredField( token.nextToken() );
                targetObject = field.get(targetObject);
            }
        }else {
            Field field = null;
            try {
                field = targetObject.getClass().getDeclaredField(key);
                Field.setAccessible(new Field[]{field}, true);
            } catch ( Exception e ) {
                targetObject = getObjectByRequest(request, key);
                if( targetObject == null ){
                    if( key.equalsIgnoreCase("null") ){
                        return null;
                    }
                    return key;
                }
                return targetObject;
            }
            targetObject = field.get(targetObject);
        }
        return targetObject;
    }
    
    public static boolean isEmpty(String str){
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    
    /**
     * 比较两个value的值<br/>
     * 详情请见: {@link org.tags.constant.Constant} condition
     * @param leftValue
     * @param rightValue
     * @param eqType
     * @return
     */
    public static int equalsObject(Object leftValue, Object rightValue, String eqType) {
        if( leftValue == null || rightValue == null ){
            return equalsNull(leftValue, rightValue, eqType);
        }
        
        int lt = convert2Int(leftValue);
        int rt = convert2Int(rightValue);
        if( lt == -1 || rt == -1 ){
            //如果数字转换失败,则当作字符串来比较
            return equalsString(leftValue, rightValue, eqType);
        }
        
        if( eqType.equals(Constant.condition_eq) ){
            if ( leftValue.equals(rightValue) ) {
                return EVAL_BODY_INCLUDE;
            } else {
                return SKIP_BODY;
            }
        }
        
        if( eqType.equals(Constant.condition_gt) ){
            if ( lt > rt ) {
                return EVAL_BODY_INCLUDE;
            } else {
                return SKIP_BODY;
            }
        }
        
        if( eqType.equals(Constant.condition_lt) ){
            if ( lt < rt ) {
                return EVAL_BODY_INCLUDE;
            } else {
                return SKIP_BODY;
            }
        }
        
        if( eqType.equals(Constant.condition_gt_eq) ){
            if ( lt >= rt ) {
                return EVAL_BODY_INCLUDE;
            } else {
                return SKIP_BODY;
            }
        }
        
        if( eqType.equals(Constant.condition_lt_eq) ){
            if ( lt <= rt ) {
                return EVAL_BODY_INCLUDE;
            } else {
                return SKIP_BODY;
            }
        }
        
        if( eqType.equals(Constant.condition_not_eq0) || eqType.equals(Constant.condition_not_eq1)){
            if ( lt != rt ) {
                return EVAL_BODY_INCLUDE;
            } else {
                return SKIP_BODY;
            }
        }
        
        /* 当成boolean比较 */
        if( eqType.equals(Constant.condition_and0) || eqType.equals(Constant.condition_and1) || eqType.equals(Constant.condition_or0) || eqType.equals(Constant.condition_or1)){
            return equalsBoolean(leftValue, rightValue, eqType);
        }
        
        return SKIP_BODY;
    }

    private static int equalsBoolean(Object leftValue, Object rightValue, String eqType) {
        boolean ltb = Boolean.valueOf(leftValue.toString());
        boolean rtb = Boolean.valueOf(rightValue.toString());
        if( leftValue.toString().equalsIgnoreCase("y") ){
            ltb = true;
        }
        if( leftValue.toString().equalsIgnoreCase("true") ){
            ltb = true;
        }
        if( rightValue.toString().equalsIgnoreCase("y") ){
            rtb = true;
        }
        if( rightValue.toString().equalsIgnoreCase("true") ){
            rtb = true;
        }
        
        if( eqType.equals(Constant.condition_and0) || eqType.equals(Constant.condition_and1)){
            if ( ltb && rtb ) {
                return EVAL_BODY_INCLUDE;
            } else {
                return SKIP_BODY;
            }
        }
        
        if( eqType.equals(Constant.condition_or0) || eqType.equals(Constant.condition_or1)){
            if ( ltb || rtb ) {
                return EVAL_BODY_INCLUDE;
            } else {
                return SKIP_BODY;
            }
        }
        return SKIP_BODY;
    }

    private static int equalsString(Object leftValue, Object rightValue, String eqType) {
        /* 如果比较符为==号 */
        if( Constant.condition_eq.equals(eqType) && leftValue.equals(rightValue) ){
            return EVAL_BODY_INCLUDE;
        }
        
        /* 如果比较符为!= 号 */
        if( (Constant.condition_not_eq0.equals(eqType) || Constant.condition_not_eq1.equals(eqType)) &&
            !leftValue.equals(rightValue) ){
            return EVAL_BODY_INCLUDE;
        }
        
        /* 其他符号不能比较 */
        return SKIP_BODY;
    }

    private static int convert2Int(Object leftValue) {
        int lt = -1;
        try {
            lt = Integer.valueOf(leftValue.toString());
        } catch ( Exception e ) {
            
        }
        return lt;
    }
    
    public static int equalsNull(Object leftValue, Object rightValue, String equalsType){
        /* null==null 比较是true的 */
        if( leftValue == null && rightValue == null && Constant.condition_eq.equals(equalsType) ){
            return EVAL_BODY_INCLUDE;
        }
        /* 只要有一方不为null, 并且比较符为!=, <> 的都为true, 否则为false, 因为null 不能与<, >, >=....这些符号做比较 */
        if( (leftValue == null && rightValue != null) || (leftValue != null && rightValue == null) && 
            (Constant.condition_not_eq0.equals(equalsType) || Constant.condition_not_eq1.equals(equalsType)) ){
            return EVAL_BODY_INCLUDE;
        }
        return SKIP_BODY;
    }
    
    /**
     * 解析表达式
     */
    public static String analyzeExp(String exp) {
        /* 1. 判断比较符 */
        if ( exp.contains(Constant.condition_eq) ) {
            return Constant.condition_eq;
        }else if( exp.contains( Constant.condition_gt ) ){
            return Constant.condition_gt;
        }else if( exp.contains( Constant.condition_lt ) ){
            return Constant.condition_lt;
        }else if( exp.contains( Constant.condition_gt_eq ) ){
            return Constant.condition_gt_eq;
        }else if( exp.contains( Constant.condition_lt_eq ) ){
            return Constant.condition_lt_eq;
        }else if( exp.contains( Constant.condition_not_eq0 ) ){
            return Constant.condition_not_eq0;
        }else if( exp.contains( Constant.condition_not_eq1 ) ){
            return Constant.condition_not_eq1;
        }else if( exp.contains( Constant.condition_and0 ) ){
            return Constant.condition_and0;
        }else if( exp.contains( Constant.condition_and1 ) ){
            return Constant.condition_and1;
        }else if( exp.contains( Constant.condition_or0 ) ){
            return Constant.condition_or0;
        }else if( exp.contains( Constant.condition_or1 ) ){
            return Constant.condition_or1;
        }
        return "";
    }
    
    public static String getI18n(HttpServletRequest request, String lang, String key){
        
        if( isEmpty(lang) ){
            lang = (String)TagUtil.getObject(request, null, "local");
        }
        
        return Globalization.getI18ndesc(lang, key);
    }
}
