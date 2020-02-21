package com.mouse.core.utils;

import com.mouse.core.base.BodyReaderHttpServletRequestWrapper;
import com.mouse.core.base.R;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ; lidongdong
 * @Description web 操作小工具
 * @Date 2019-09-23
 */
public class WebKit {

    static Logger logger = LoggerFactory.getLogger(WebKit.class);

    private static String pattern = "^Mozilla/\\d\\.\\d\\s+\\(+.+?\\)";
    private static String pattern2 = "\\(+.+?\\)";
    private static Pattern r = Pattern.compile(pattern);
    private static Pattern r2 = Pattern.compile(pattern2);

    /**
     * 授权的 token header
     */
    public static final String  AUTH_HEADER = "Authorization";

    public static String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    public static String getRealIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 允许跨域
     *
     * @param response
     * @param origin
     */
    public static void setCORS(HttpServletResponse response, String origin) {
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Origin", origin);

    }

    /**
     * 打印完全参数
     *
     * @param requestWrapper
     */
    public static void loggerSheer(BodyReaderHttpServletRequestWrapper requestWrapper,
                                   Logger log) {
        log.info("请求info:ip:{},url:{},\n\tparam:{},\n\tcontent-type:{},\n\torigin:{},\n\treferer:{},\n\tUser-Agent:{}",
                new Object[]{getRequestIp(requestWrapper),
                        requestWrapper.getRequestURL(),
                        requestWrapper.getBodyS(),
                        requestWrapper.getContentType(),
                        requestWrapper.getHeader("Origin"),
                        requestWrapper.getHeader("Referer"),
                        requestWrapper.getHeader("User-Agent")});
    }

    /**
     * 打印缺省参数
     *
     * @param requestWrapper
     * @param log
     */
    public static void loggerDefault(BodyReaderHttpServletRequestWrapper requestWrapper,
                                     Logger log) {
        log.info("请求info:ip:{},url:{},\n\tparam:{},\n\tcontent-type:{},\n\torigin:{},\n\treferer:{},\n\tUser-Agent:{}",
                new Object[]{getRealIp(requestWrapper),
                        requestWrapper.getRequestURL(),
                        "敏感信息，不打印参数",
                        requestWrapper.getContentType(),
                        requestWrapper.getHeader("Origin"),
                        requestWrapper.getHeader("Referer"),
                        requestWrapper.getHeader("User-Agent")});
    }

    /**
     * 响应数据
     *
     * @param response
     * @param mesResponse
     */
    public static void response(HttpServletResponse response,
                                R mesResponse) {
        response(response, JsonUtils.toJson(mesResponse));
    }


    /**
     * 响应数据
     *
     * @param response
     * @param bodys
     */
    public static void response(HttpServletResponse response,
                                String bodys) {

        PrintWriter writer = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            writer = response.getWriter();
            writer.write(bodys);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            logger.error("数据响应异常 IOException:", e);
        }


        return;
    }

    /**
     * 获取设备信息
     *
     * @param userAgent
     * @return
     */
    public static String getDeviceInfo(String userAgent) {

        if (userAgent.contains("CFNetwork")
                || userAgent.contains("Darwin")) {
            return userAgent;
        }

        Matcher m = r.matcher(userAgent);
        String result = null;
        if (m.find()) {
            result = m.group(0);
        }
        if (StringUtils.isBlank(result)) {
            return userAgent;
        }

        Matcher m2 = r2.matcher(result);
        if (m2.find()) {
            result = m2.group(0);
        }
        result = result.replace("(", "");
        result = result.replace(")", "");
        return filterDeviceInfo(result);
    }

    private static String filterDeviceInfo(String result) {
        if (StringUtils.isBlank(result)) {
            return null;
        }
        result = result.replace(" U;", "");
        result = result.replace(" zh-cn;", "");
        return result;
    }


    /**
     * 参数获取
     *
     * @param request
     * @return
     */
    public static Map<String, String> getReqParam(HttpServletRequest request) {
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return params;
    }


    /**
     * map转为 get param
     *
     * @param params
     * @return
     */
    public static String map2ParamGet(Map<String, String> params) {
        return map2ParamGet(params, true);
    }


    public static String map2ParamGet(Map<String, String> params, boolean isUrlEncoder) {
        boolean first = true;

        StringBuilder sb = new StringBuilder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (first) {
                    sb.append("?");
                } else {
                    sb.append("&");
                }

                sb.append(entry.getKey());
                sb.append("=");
                String value = entry.getValue();
                try {
                    if (StringUtils.isNotBlank(value)) {
                        if (isUrlEncoder) {
                            sb.append(URLEncoder.encode(value, "UTF-8"));
                        }
                        sb.append(value);

                    }
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("URLEncoder error  please check it.");
                }
                first = false;
            }
        }
        return sb.toString();
    }


    /**
     * 流关闭
     *
     * @param inputStream
     * @param outputStream
     */
    public static void close(InputStream inputStream,
                             OutputStream outputStream) {
        try {
            if (null != inputStream) {
                inputStream.close();
            }
            if (null != outputStream) {
                outputStream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取request中全部Header信息
     *
     * @param request
     * @return
     */
    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

}
