package com.meet.common.log;

import java.lang.annotation.*;
/**
 * Created by bzhx on 2017-11-01 22:57.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface LogAnnotation {
    //操作内容  
   String option() default "";
   //模块
    String module() default  "";
}
