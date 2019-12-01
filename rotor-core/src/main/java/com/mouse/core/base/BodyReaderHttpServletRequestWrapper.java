package com.mouse.core.base;

import com.mouse.core.enums.RequestMethodEnum;
import com.mouse.core.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.Map;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2019-11-30
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private byte[] body;

    private String bodyS;

    public String getBodyS() {
        return bodyS;
    }

    public void setBodyS(String bodyS) {
        this.bodyS = bodyS;
    }

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        switch (RequestMethodEnum.parse(request.getMethod())) {
            case GET: {
                Map<String, String[]> parameterMap = request.getParameterMap();
                bodyS = JsonUtils.toJson(parameterMap);
                break;
            }

            case POST: {
                //普通form表单提交 stream 已被操作
                if (MediaType.APPLICATION_FORM_URLENCODED_VALUE.equals(request.getContentType())) {
                    Map<String, String[]> parameterMap = request.getParameterMap();
                    bodyS = JsonUtils.toJson(parameterMap);
                    break;
                }

                InputStream is = request.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte buff[] = new byte[1024];
                int read;
                while ((read = is.read(buff)) > 0) {
                    baos.write(buff, 0, read);
                }
                body = baos.toByteArray();
                bodyS = new String(body, "UTF-8");
                break;
            }

            default: {
            }
        }


    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    /**
     * 获取单个参数值
     *
     * @param name
     * @return
     */
    public String getParam(String name) {

        if (StringUtils.isBlank(bodyS)
                || StringUtils.isBlank(name)) {
            return null;
        }

        Object nameO = JsonUtils.toObject(bodyS, Map.class).get(name);
        if (null == nameO) {
            return null;
        }
        return nameO.toString().replace("[", "").replace("]", "");
    }
}
