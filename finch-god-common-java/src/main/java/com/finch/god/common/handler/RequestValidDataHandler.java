package com.finch.god.common.handler;

import com.finch.god.common.annotation.Trim;
import com.finch.god.common.annotation.XssCheck;
import com.finch.god.common.config.Constants;
import com.finch.god.common.exception.XssException;
import com.finch.god.common.utils.XssTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@Component
@Aspect
@Slf4j
public class RequestValidDataHandler {

    @Pointcut("execution(* com.finch.*.controller..*.*(..))")
    public void pointcutName() {
    }


    @Around("pointcutName()")
    public Object executeAround(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Annotation[][] methodAnnotations = method.getParameterAnnotations();
        Object[] obj = pjp.getArgs();
        if (obj == null) pjp.proceed();
        try {
            for (int i = 0; i < obj.length; i++) {
                if (this.checkAnnotation(methodAnnotations[i])) {
                    this.preHandle(obj[i]);
                }
            }
        } catch (XssException e) {
            log.info("XssException:{}", e);
            return e.toResult();
        }
        return pjp.proceed();
    }


    private void preHandle(Object requestDataObj) throws XssException {
        Class clazz = requestDataObj.getClass();
        List<Field> fields = this.getFields(clazz);

        for (Field field : fields ) {
            Object fieldValue = null;
            field.setAccessible(true);

            try {
                String fieldName = field.getName();
                fieldValue = field.get(requestDataObj);
                if (Objects.isNull(fieldValue)) continue;
                if (String.class.isAssignableFrom(field.getType())) { // ?????????????????????
                    String strValue = (String)fieldValue;
                    if (field.isAnnotationPresent(Trim.class)) {
                        field.set(requestDataObj, StringUtils.trim((String)fieldValue));
                    }
                    if (field.isAnnotationPresent(XssCheck.class)) {
                        if (!XssTool.matchXSS(strValue)) {
                            StringBuilder buf = new StringBuilder("?????????").append(fieldName).append(":[ ").append(strValue).append(" ]?????????XSS??????");
                            throw new XssException(Constants.RESULT_CODE_ERROR, buf.toString());
                        }
                    }
                }
                if (field.isAnnotationPresent(Valid.class) || field.isAnnotationPresent(Validated.class)) {
                    if (Collection.class.isAssignableFrom(field.getType())) { // ??????????????????
                        Collection listValue = (Collection) fieldValue;
                        for (Object item : listValue) {
                            this.preHandle(item);
                        }
                    }else if (Map.class.isAssignableFrom(field.getType())) { // ??????map??????
                        Map mapValue = (Map) fieldValue;
                        mapValue.forEach((k, v) -> {
                            if (v != null) this.preHandle(v);
                        });
                    } else {
                        this.preHandle(fieldValue);
                    }

                }
            } catch (IllegalAccessException e) {
                log.error("??????????????????|{}", e);
                continue;
            }
        }

    }

    private List<Field> getFields(Class clazz) {
        List<Field> fieldList = new ArrayList<>() ;
        Class tempClass = clazz;
        while (tempClass != null) {//????????????null??????????????????????????????????????????(Object???).
            fieldList.addAll(Arrays.asList(tempClass .getDeclaredFields()));
            tempClass = tempClass.getSuperclass(); //????????????,??????????????????
        }
        Collections.reverse(fieldList);
        return fieldList;
    }

    private boolean checkAnnotation( Annotation[] annotations) {
        if(annotations == null) return false;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == Valid.class || annotation.annotationType() == Validated.class) {
                return true;
            }
        }
        return false;
    }

}
