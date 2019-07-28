package com.base.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.base.dao.CrmSysMenuMapper;
import com.base.dao.CrmSysRoleMapper;
import com.base.dao.CrmSysRoleMenuMapper;
import com.base.dao.CrmSysUserMapper;
import com.base.dao.CrmSysUserRoleMapper;
import com.base.entity.CrmSysMenu;
import com.base.entity.CrmSysRole;
import com.base.entity.CrmSysUser;
import com.base.entity.CrmSysUserRole;

@Service
public class AuthUserService implements UserDetailsService{
	
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
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		List<CrmSysUser> selectAll = scum.selectAll();
//		System.out.println(selectAll.size());
		CrmSysUser user = scum.selectByUsername(username);
		if (null != user) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			Integer userId = user.getId();
			List<CrmSysUserRole> list = csurm.selectByUserId(userId);
			for (CrmSysUserRole crmSysUserRole : list) {
				CrmSysRole role = csrm.selectByPrimaryKey(crmSysUserRole.getRoleid());
				authorities.add(new SimpleGrantedAuthority(role.getName()));
//				List<CrmSysRoleMenu> menuList = csrmm.selectByRoleId(role.getId());
//				for (CrmSysRoleMenu crmSysRoleMenu : menuList) {
//					Integer menuid = crmSysRoleMenu.getMenuid();
//					CrmSysMenu menu = csmm.selectByPrimaryKey(menuid);
//				}
			}
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(user.getPassword().trim());
//			List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_work");
			User myUser = new User(user.getUsername(), encodedPassword , true,true,true,true,authorities);
			return myUser;
		}
		return null; 
	}
	
	public User getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User)userDetails;
        return user;
    }
	
	public List<CrmSysMenu> getAllMenu (){
		return csmm.selectAll();
	}

}
