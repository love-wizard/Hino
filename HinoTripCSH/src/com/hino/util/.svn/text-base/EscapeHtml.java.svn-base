package com.hino.util;

/**
 * This class escapes html symbols, such as > and <, to &gt; and &lt;
 * 
 * org.apache.commons.lang.StringEscapeUtils.escapeHtml() can also do the same thing,
 * but this method changes Chinese characters into &#XXXXX; at the same time.
 * This leads to a much more longer string than the original Chinese sentence. 
 * 
 * Copied from JavaEye.
 * Available at: http://lxs647.javaeye.com/blog/800722
 */
public class EscapeHtml {

	/**
	 * Escape HTML symbols in the given String
	 * @param source source String
	 * @return escaped String
	 */
	public static String htmlEncode(String source) {
		
        if (source == null) {
            return "";
        }
        
        String html = "";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (c) {
            case '<':
                buffer.append("&lt;");
                break;
            case '>':
                buffer.append("&gt;");
                break;
            case '&':
                buffer.append("&amp;");
                break;
            case '"':
                buffer.append("&quot;");
                break;
            //10 and 13 makes new line
            //case 10:
            //case 13:
            //    break;
            default:
                buffer.append(c);
            }
        }
        html = buffer.toString();
        return html;
    }
	/**
	 * Unescape HTML symbols in the given String
	 * @param source source String
	 * @return escaped String
	 */
	public static String htmlDecode(String source) {
		
        if (source == null) {
            return "";
        }
        
        String html = "";
        html = html.replaceAll("&amp;", "&");
        html = source.replaceAll("&lt;", "<");
        html = html.replaceAll("&gt;", ">");
        html = html.replaceAll("&quot;", "\"");
        
        return html;
    }
	
	/**
	 * replace java newline \n, \r with HTML symbols <br/> in the given String
	 * @param source source String
	 * @return escaped String
	 */
	public static String nrTobr(String source)
	{
		String html = "";
		html = source.replaceAll("\\n", "<br/>");
		html = html.replaceAll("\\r", "");
		return html;
	}
	
	/**
	 * replace java newline \n, \r with JS symbols \n, \r in the given String
	 * @param source source String
	 * @return escaped String
	 */
	public static String nrTonnrr(String source)
	{
		String html = "";
		html = source.replaceAll("\\n", "\\\\n");
		html = html.replaceAll("\\r", "\\\\r");
		return html;
	}

}
