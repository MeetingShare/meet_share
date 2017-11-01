package com.meet.controller.sys;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.meet.common.catptcha.CaptchaRender;
import com.meet.common.resources.Resources;
import com.meet.controller.BaseController;

@Controller
@RequestMapping("/auth")
public class LoginController extends BaseController {  
    /** 
     * 获取验证码图片和文本(验证码文本会保存在HttpSession中) 
     */  
    @RequestMapping("/captchat")  
    public void getVerifyCodeImage(HttpServletResponse response) throws IOException {  
    	CaptchaRender img = new CaptchaRender(4);
		this.setSessionAttr(CaptchaRender.DEFAULT_CAPTCHA_MD5_CODE_KEY, img.getMd5RandonCode());
        img.render(response);
    }  
       
    /** 
     * 用户登录 
     */  
    @RequestMapping(value="/login", method=RequestMethod.POST)  
    public String login(HttpServletRequest request){  
        String resultPageURL = InternalResourceViewResolver.FORWARD_URL_PREFIX + "/login.in";  
        String username = getParam("username");  
        String password = getParam("password");
        String remermber=getParam("remermber");
        //获取HttpSession中的验证码  
        String verifyCode = (String)getSessionAttr(CaptchaRender.DEFAULT_CAPTCHA_MD5_CODE_KEY);  
        //获取用户请求表单中输入的验证码  
        String submitCode = WebUtils.getCleanParam(request, "captcha"); 
        if (!CaptchaRender.validate(verifyCode, submitCode)){  
        	setAttr("erroMessage", Resources.getMessage("login_code_fail"));  
            return resultPageURL;  
        }  
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);  
        if("true".equals(remermber)){
        	 token.setRememberMe(true);  
        }
        //获取当前的Subject  
        Subject currentUser = SecurityUtils.getSubject();  
        try {  
            currentUser.login(token);  
            resultPageURL =InternalResourceViewResolver.REDIRECT_URL_PREFIX+ "/";  
        }catch(UnknownAccountException uae){  
            setAttr("erroMessage",Resources.getMessage("login_unknow_user"));  
        }catch(IncorrectCredentialsException ice){  
        	System.out.println("ice.getMessage():"+ice.getMessage());;
        	setAttr("erroMessage", Resources.getMessage("login_password_error"));  
        }catch(LockedAccountException lae){  
        	setAttr("erroMessage", Resources.getMessage("login_user_checkd"));  
        }catch(ExcessiveAttemptsException eae){  
        	setAttr("erroMessage", Resources.getMessage("login_number_fail"));  
        }catch(AuthenticationException ae){  
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
            ae.printStackTrace();  
            request.setAttribute("erroMessage", Resources.getMessage("login_fail"));  
        }  
        //验证是否登录成功  
        if(currentUser.isAuthenticated()){  
        	logger.info("用户{}登录认证通过",username);
        }else{  
            token.clear();  
        }  
        return resultPageURL;  
    }  
       
       
    /** 
     * 用户登出 
     */  
    @RequestMapping("/logout")  
    public String logout(HttpServletRequest request){  
         SecurityUtils.getSubject().logout();  
         return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";  
    }  
}
