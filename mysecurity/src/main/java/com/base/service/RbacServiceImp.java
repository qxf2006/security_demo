package com.base.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.base.dao.CrmSysMenuMapper;
import com.base.dao.CrmSysRoleMapper;
import com.base.dao.CrmSysRoleMenuMapper;
import com.base.dao.CrmSysUserMapper;
import com.base.dao.CrmSysUserRoleMapper;
import com.base.entity.CrmSysMenu;
import com.base.entity.CrmSysRole;
import com.base.entity.CrmSysRoleMenu;
import com.base.entity.CrmSysUser;
import com.base.entity.CrmSysUserRole;

@Component("rbacService")
public class RbacServiceImp implements RbacService {

	@Autowired
	CrmSysUserMapper scum;
	@Autowired
	CrmSysUserRoleMapper csurm;
	@Autowired
	CrmSysRoleMapper csrm;
	@Autowired
	CrmSysMenuMapper csmm;
	@Autowired
	CrmSysRoleMenuMapper csrmm;
	
	private AntPathMatcher ant = new AntPathMatcher();
	
	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		boolean permission = false;
		Object principal = authentication.getPrincipal();
		Set<String> st = new HashSet<>();

		if (principal instanceof UserDetails) {
			String username = ((UserDetails)principal).getUsername();
			CrmSysUser user = scum.selectByUsername(username);
			Integer userId = user.getId();
			List<CrmSysUserRole> list = csurm.selectByUserId(userId);
			for (CrmSysUserRole crmSysUserRole : list) {
				CrmSysRole role = csrm.selectByPrimaryKey(crmSysUserRole.getRoleid());
				List<CrmSysRoleMenu> menuList = csrmm.selectByRoleId(role.getId());
				for (CrmSysRoleMenu crmSysRoleMenu : menuList) {
					Integer menuid = crmSysRoleMenu.getMenuid();
					CrmSysMenu menu = csmm.selectByPrimaryKey(menuid);
					String href = menu.getHref();
					System.out.println(href);
					st.add(href);
				}
			}
		}
		System.out.println(st.toString());
		for (String url : st) {
			System.out.println(request.getRequestURI());
			if (ant.match(url, request.getRequestURI())) {
				permission = true;
//				break;
			}
		}
		return permission;
	}

}
