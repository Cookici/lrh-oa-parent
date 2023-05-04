package com.lrh.common.result;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: lrh-oa-parent
 * @Package: com.lrh.common.result
 * @ClassName: ResponseUtil
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/5/4 10:29
 */

public class ResponseUtil {
    public static void out(HttpServletResponse response,Result result){
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            objectMapper.writeValue(response.getWriter(),result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
