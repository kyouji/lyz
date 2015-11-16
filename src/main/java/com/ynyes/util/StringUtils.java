package com.ynyes.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;
/**
 * 常用字符串工具类
 * （小郭封装）
 * StringUtils<BR>
 * 创建人:guozhengyang <BR>
 * 时间：2015年1月29日-下午12:35:10 <BR>
 * @version 1.0.0
 *
 */
@SuppressWarnings("restriction")
public class StringUtils {

    /**
     * 判断字符串是否是整数
     */
    public static boolean isInteger(String value) {
     try {
      Integer.parseInt(value);
      return true;
     } catch (NumberFormatException e) {
      return false;
     }
    }

    /**
     * 判断字符串是否是浮点数
     */
    public static boolean isDouble(String value) {
     try {
      Double.parseDouble(value);
      if (value.contains("."))
       return true;
      return false;
     } catch (NumberFormatException e) {
      return false;
     }
    }
    public static String base64Encode(byte[] b) {
        if (b == null) {
            return null;
        }
        return new BASE64Encoder().encode(b);
    }
    
    /**
     * 判断字符串是否是数字
     */
    public static boolean isNumber(String value) {
     return isInteger(value) || isDouble(value);
    }
    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return null == str || str.length() == 0 || "".equals(str)
                || str.matches("\\s*");
    }
    
    /**
     * 非空判断
     * 方法名：isNotEmpty<BR>
     * 创建人：guozhengyang <BR>
     * 时间：2014年8月12日-下午9:36:18 <BR>
     * @param str
     * @return boolean<BR>
     * @exception <BR>
     * @since  1.0.0
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    /**
    *
    * @param plainText
    *            明文
    * @return 32位密文
    */
   public static String encryption(String plainText) {
       String re_md5 = new String();
       try {
           MessageDigest md = MessageDigest.getInstance("MD5");
           md.update(plainText.getBytes());
           byte b[] = md.digest();

           int i;

           StringBuffer buf = new StringBuffer("");
           for (int offset = 0; offset < b.length; offset++) {
               i = b[offset];
               if (i < 0)
                   i += 256;
               if (i < 16)
                   buf.append("0");
               buf.append(Integer.toHexString(i));
           }

           re_md5 = buf.toString();

       } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
       }
       return re_md5;
   }
   /**
    * 凯德加密
    * 方法名：encryption<BR>
    * 创建人：xiaowei <BR>
    * 时间：2014年10月25日-下午9:48:19 <BR>
    * @param str
    * @param k
    * @return String<BR>
    * @exception <BR>
    * @since  1.0.0
    */
   public static String encryption(String str,int k){
       String string = "";
       for (int i = 0; i < str.length(); i++) {
           char c= str.charAt(i);
           if(c>='a' && c<='z'){
               c += k%26;
               if(c<'a'){
                   c+=26;
               }
               if(c>'z'){
                   c-=26;
               }
           }else if(c>='A' && c<='Z'){
               c+=k%26;
               if(c<'A'){
                   c+=26;
               }
               if(c>'Z'){
                   c-=26;
               }
           }
           string+=c;
       }
       return string;
   }
   
   /**
    * 凯德解密
    * 方法名：dencryption<BR>
    * 创建人：xiaowei <BR>
    * 时间：2014年10月25日-下午9:48:35 <BR>
    * @param str
    * @param n
    * @return String<BR>
    * @exception <BR>
    * @since  1.0.0
    */
   public static String dencryption(String str,int n){
       String string = "";
       int k = Integer.parseInt("-"+n);
       for (int i = 0; i < str.length(); i++) {
           char c= str.charAt(i);
           if(c>='a' && c<='z'){
               c += k%26;
               if(c<'a'){
                   c+=26;
               }
               if(c>'z'){
                   c-=26;
               }
           }else if(c>='A' && c<='Z'){
               c+=k%26;
               if(c<'A'){
                   c+=26;
               }
               if(c>'Z'){
                   c-=26;
               }
           }
           string+=c;
       }
       return string;
   }
   public static void main(String[] args) {
    String str="1";
    System.out.println(encryption(str));
}
} 
  

