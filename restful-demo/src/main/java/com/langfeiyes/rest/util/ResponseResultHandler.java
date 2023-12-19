//package com.langfeiyes.rest.util;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
//@Slf4j
//@ControllerAdvice
//public class ResponseResultHandler implements ResponseBodyAdvice<Object> {
//
//    @Override
//    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//        log.info("supports:{}", returnType.getDeclaringClass().getName());
//        /**
//         * 排除swagger-ui请求返回数据增强
//         */
//        return !returnType.getDeclaringClass().getName().contains("springfox");
//    }
//
//    @Override
//    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//        // 如果是ResponseInfo数据类型就直接返回
//        if (body instanceof ResponseInfo) {
//            return body;
//        }
//        // 如果是空，则返回成功
//        else if (body == null) {
//            return ResponseInfo.success();
//        }
//        // 如果是异常类型就直接返回
//        else if (body instanceof Exception) {
//            return body;
//        }
//        // 如果是String类型则直接返回String类型
//        else if (body instanceof String) {
//            return body;
//        }
//        // 返回封装后的数据
//        else {
//            return ResponseInfo.success(body);
//        }
//    }
//}
