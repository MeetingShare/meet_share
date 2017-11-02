package com.meet.common.log;

import com.alibaba.fastjson.JSON;
import com.meet.common.constants.MeetConstants;
import com.meet.orm.pojo.SysUserOperateLog;
import com.meet.orm.service.sys.LogService;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 切点类
 *
 * @author baizhixing
 * @version 1.0
 * @since 2015-05-05 Pm 20:35
 */
@Aspect
public class LogAopAction {
    //本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(LogAopAction.class);
    @Autowired
    private LogService logService;

    //Controller层切点
    @Pointcut("@annotation(com.meet.common.log.LogAnnotation)")
    public  void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param point 切点
     */
    @Before("controllerAspect()")
    public Object doBefore(JoinPoint  point) throws Throwable {
        logger.info("执行插入日志操作。。。");
        Object result = null;
        try {
            // 执行方法名
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            Integer userId = 0;
            if (request.getSession().getAttribute(MeetConstants.SESSION_USER_Id) != null) {
                userId = Integer.parseInt(request.getSession().getAttribute(MeetConstants.SESSION_USER_Id).toString());
            }
            //获取请求参数
            Map<String, Object> map = map = getControllerMethodDescription(point);
            // 执行方法所消耗的时间
            SysUserOperateLog log = new SysUserOperateLog();
            log.setUid(userId);
            log.setReqParam(getParam(request));
            log.setModule(map.get("module").toString());
            log.setAction(map.get("option").toString());
            logService.saveLog(log);
        }catch (Exception e){
            //记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息:{}", e.getMessage());
        }
        return result;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public Map<String, Object> getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    map.put("module", method.getAnnotation(LogAnnotation.class).module());
                    map.put("option", method.getAnnotation(LogAnnotation.class).option());
                    break;
                }
            }
        }
        return map;
    }

    /**
     * 获取请求参数
     *
     * @param request
     * @return
     */
    private String getParam(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            String value = request.getParameter(paraName);
            logger.info("paraName:{},value:{}",paraName,value);
            if (!StringUtils.isEmpty(value)) {
                map.put(paraName, value);
            }
        }
        return JSON.toJSONString(map);
    }
}
