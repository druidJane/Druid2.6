/*
 * Copyright (c) 2012 by XUANWU INFORMATION TECHNOLOGY CO. 
 *             All rights reserved                         
 */
package com.druid.config;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.MapCache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户登录和权限领域验证
 *
 * @author <a href="mailto:jiji@javawind.com">XueFang.Xu</a>
 * @version 1.0.0
 * @date 2016-08-24
 */
public class ShiroRealm extends AuthorizingRealm {

	private static final Logger LOG = LoggerFactory.getLogger(com.druid.config.ShiroRealm.class);
	protected static final String WILDCARD_TOKEN = "*";
	private static final String COMMON = "/common";
	private static Set<String> allPermissions = null;

	// warning: 不能注入其它service，否则会导致该service的事务注解无效!!!



	public ShiroRealm() {
		// 授权缓存
		String authorizationCacheName = "cmp_authorizationCache";
		Map<Object, AuthorizationInfo> authorizationMap = new HashMap<Object, AuthorizationInfo>();
		Cache<Object, AuthorizationInfo> authorizationCache = new MapCache<Object, AuthorizationInfo>(
				authorizationCacheName, authorizationMap);
		super.setAuthorizationCache(authorizationCache);
		super.setAuthorizationCacheName(authorizationCacheName);
	}

	/**
	 * 身份验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		SecurityUtils.getSubject().logout();// 覆盖登录

		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		if (StringUtils.isBlank(username)) {
			throw new UnknownAccountException("UserName is blank:[" + username + "]");
		}

		/*SimpleUser user = shiroService.getLoginUser(username, platformMode.getPlatform());
		if (user == null) {
			throw new UnknownAccountException("Can't find user by name:" + username);
		}

		if (user.getState() != UserState.NORMAL) {
			throw new DisabledAccountException("The user is locked by name:" + username);
		}*/

		//String password = user.getPassword().toLowerCase();
		//AuthenticationInfo info = new SimpleAuthenticationInfo(username, password.toCharArray(), getName());
		AuthenticationInfo info = new SimpleAuthenticationInfo(username, "112233", getName());
		/*SessionUtil.setCurUser(user);
		User enterprise = shiroService.getUserById(user.getEnterpriseId());
		SessionUtil.setCurEnterprise(enterprise);
		// 设置数据范围
		HashMap<Integer, DataScope> dataScopes = shiroService.getDataScopes(user.getId());
		SessionUtil.setDataSope(dataScopes);*/
		return info;
	}

	/**
	 * 权限验证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (principals == null) {
			throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
		}
		/*SimpleUser user = SessionUtil.getCurUser();
		if (null == user) {
			LOG.error("can't find the user in session");
			String username = (String) getAvailablePrincipal(principals);
			user = shiroService.getLoginUser(username, platformMode.getPlatform());
		}
		if (null == user) {
			LOG.error("can't find the user in db and session");
		}
		Set<String> permissions = new HashSet<String>();
		try {
			ConcurrentHashMap<String, Permission> url2PermissionMapping = new ConcurrentHashMap<>();
			List<Permission> permissionList = shiroService.getUserPermissions(user.getId(), platformMode.getPlatform());
			for (com.xuanwu.cmp.domain.entity.Permission permission : permissionList) {
				if (StringUtils.isNotBlank(permission.getUrl())) {
					permissions.add(permission.getUrl());
					if (platformMode.getPlatform() == Platform.BACKEND) {
						collectUrlToPermissionCodeMapping(url2PermissionMapping, permission);
					}
				}
			}
			if (platformMode.getPlatform() == Platform.BACKEND) {
				SessionUtil.setUrlToPermissionMap(url2PermissionMapping);
				Collection<Permission> userPermission = url2PermissionMapping.values();
				Iterator<Permission> iterator = userPermission.iterator();
				Set<String> permissionCodeSet = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
				while (iterator.hasNext()) {
					Permission permission = iterator.next();
					if (permission != null && StringUtil.isNotBlank(permission.getPermissionCode())) {
						permissionCodeSet.add(permission.getPermissionCode());
					}
				}
				SessionUtil.setPermissionCodeSet(permissionCodeSet);
			}
		} catch (Exception e) {
			LOG.error("Authorization error: ", e);
		}

		// 后台
		if (allPermissions == null && platformMode.getPlatform() == Platform.BACKEND) {
			allPermissions = new HashSet<>();
			List<Permission> allList = shiroService.findPermissionsByPlatform(Platform.BACKEND);
			for (Permission p : allList) {
				allPermissions.add(p.getUrl());
			}
		}
		// 前台
		if (platformMode.getPlatform() == Platform.FRONTKIT) {
			permissions.add(Constants.HOME);
			permissions.add(Constants.COMMON);
		}*/

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//info.setStringPermissions(permissions);
		return info;
	}

	/*private void collectUrlToPermissionCodeMapping(ConcurrentHashMap<String, Permission> mappingStorage,
			Permission permission) {
		*//*String[] permsUrl = permission.getUrl().split(Constants.PERMISSION_URL_SEPERATOR);
		for (int i = 0; i < permsUrl.length; i++) {
			if (StringUtils.isNotEmpty(permsUrl[i])) {
				mappingStorage.put(permsUrl[i], permission);
			}
		}*//*
	}*/

	@Override
	protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
		return SecurityUtils.getSubject().getSession().getId();
	}

	@Override
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		/*if (platformMode.getPlatform() == Platform.FRONTKIT) {
			return isFrontKitPermitted(principals, permission);
		}*/
		//return isBackendPermitted(principals, permission);
		return true;
	}

	/**
	 * 前台权限判断
	 * 
	 * @param principals
	 * @param permission
	 * @return
	 */
	private boolean isFrontKitPermitted(PrincipalCollection principals, String permission) {
		AuthorizationInfo info = getAuthorizationInfo(principals);
		Collection<String> perms = info.getStringPermissions();
		if (perms != null && !perms.isEmpty()) {
			for (String perm : perms) {
				if (perm.endsWith(WILDCARD_TOKEN)) { // 适配通配符
					String permPrefix = perm.substring(0, perm.length() - 1);
					if (permission.startsWith(permPrefix)) {
						return true;
					}
				} else {
					if (permission.equals(perm)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 后台权限判断
	 * 
	 * @param principals
	 * @param permission
	 * @return
	 */
	private boolean isBackendPermitted(PrincipalCollection principals, String permission) {
		AuthorizationInfo info = getAuthorizationInfo(principals);
		Collection<String> ownPerms = info.getStringPermissions();
		if (ownPerms != null && !ownPerms.isEmpty()) {
			// 公用权限
			if (permission.startsWith(COMMON)) {
				return true;
			}
			// 角色拥有权限
			for (String permStr : ownPerms) {
				String[] perms = permStr.split(",");
				for (String perm : perms) {
					if (perm.equals(permission)) {
						return true;
					}
				}
			}
			// 不参与权限分配的url
			for (String permStr : allPermissions) {
				String[] perms = permStr.split(",");
				for (String perm : perms) {
					if (perm.equals(permission)) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

}
