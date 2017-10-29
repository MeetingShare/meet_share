package com.meet.common.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.meet.common.constants.OrderConstants;
import com.meet.orm.pojo.SysPermission;
import com.meet.orm.pojo.SysRole;
import com.meet.orm.pojo.SysUser;
import com.meet.orm.service.sys.UserService;

/**
 * @author zhixing 2015-02-27
 * */
public class ShiroRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 获取当前登录的用户名
		// 获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
		String currentUsername = (String) super.getAvailablePrincipal(principals);
		SysUser user = userService.findUserByName(currentUsername);
		Set<String> roles = new HashSet<String>();
		Set<String> permissions = new HashSet<String>();
		if (user != null) {
			if (user.getRoles() != null && user.getRoles().size() > 0) {
				for (SysRole role : user.getRoles()) {
					roles.add(role.getName());
					if (role.getPermissions() != null && role.getPermissions().size() > 0) {
						for (SysPermission pmss : role.getPermissions()) {
							if (StringUtils.isNotEmpty(pmss.getName())) {
								permissions.add(pmss.getName());
							}
						}
					}
				}
			}
		} else {
			throw new AuthorizationException();
		}
		// 给当前用户设置角色
		info.addRoles(roles);
		// 给当前用户设置权限
		info.addStringPermissions(permissions);
		return info;

	}

	/**
	 * 验证当前登录的Subject
	 * 
	 * @see 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		SysUser user = userService.findUserByName(token.getUsername());
		if (null != user) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
			this.setSession(OrderConstants.SESSION_USER_NAME, user.getUsername());
			this.setSession(OrderConstants.SESSION_USER_Id, user.getId());
			return authcInfo;
		}
		return null;
	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * 
	 * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}
}