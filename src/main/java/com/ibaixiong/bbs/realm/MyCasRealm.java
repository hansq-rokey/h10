package com.ibaixiong.bbs.realm;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.PrincipalMap;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.tags.HasAnyRolesTag;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibaixiong.bbs.service.UserService;
import com.ibaixiong.entity.User;

public class MyCasRealm extends CasRealm{

	 private static Logger log = LoggerFactory.getLogger(MyCasRealm.class);
	@Autowired
	 private UserService userService;
	
	 @Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		 String username = (String)principals.getPrimaryPrincipal();
//
//		 List<Object> list=principals.asList();
//		 AttributePrincipal aa;
//		 System.out.println(Arrays.toString(list.toArray()));
//		 Set set=principals.asSet();
//		 Collection collection=principals.fromRealm(username);
//		 Iterator it=set.iterator();
//		 while(it.hasNext()){
//			 System.out.println("-----====="+it.next());
//		 }
//		 
//		 SimplePrincipalCollection principalMap= (SimplePrincipalCollection) principals;
//		 System.out.println("---------"+principals.fromRealm("id"));
//		 System.out.println("dddddd==="+username);
//	        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//	        authorizationInfo.setRoles(userService.queryRoles(userId));
//	        authorizationInfo.setStringPermissions(userService.findPermissions(username));

//	        return authorizationInfo;


//	     // retrieve user information
	        SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) principals;
	        List<Object> listPrincipals = principalCollection.asList();
	        Map<String, String> attributes = (Map<String, String>) listPrincipals.get(1);
	        String userId=attributes.get("id");
/*	        User user=new User();
	        user.setId(Long.valueOf(userId));
	        user.setEmail(attributes.get("email"));
	        user.setNickName(attributes.get("nickName"));
	        user.setPhone(attributes.get("phone"));*/
//	        // create simple authorization info
	        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
	        List<String> rolesList=userService.queryRoles(Long.valueOf(userId));
	        List<String> permissionsList=userService.queryPermissions(Long.valueOf(userId));
	        	        
	        authorizationInfo.setRoles(new HashSet<String>(rolesList));
	        	
	        authorizationInfo.setStringPermissions(new HashSet<String>(permissionsList));
	        log.info("用户角色："+Arrays.toString(rolesList.toArray()));
	        log.info("用户权限："+Arrays.toString(permissionsList.toArray()));
//	        // add default roles
//	        addRoles(simpleAuthorizationInfo, split(defaultRoles));
//	        // add default permissions
//	        addPermissions(simpleAuthorizationInfo, split(defaultPermissions));
//	        // get roles from attributes
//	        List<String> attributeNames = split(roleAttributeNames);
//	        for (String attributeName : attributeNames) {
//	            String value = attributes.get(attributeName);
//	            addRoles(simpleAuthorizationInfo, split(value));
//	        }
//	        // get permissions from attributes
//	        attributeNames = split(permissionAttributeNames);
//	        for (String attributeName : attributeNames) {
//	            String value = attributes.get(attributeName);
//	            addPermissions(simpleAuthorizationInfo, split(value));
//	        }
	        return authorizationInfo;
	}

	 @Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		 CasToken casToken = (CasToken) token;
	        if (token == null) {
	            return null;
	        }
	        
	        String ticket = (String)casToken.getCredentials();
	        if (!StringUtils.hasText(ticket)) {
	            return null;
	        }
	        
	        TicketValidator ticketValidator = ensureTicketValidator();

	        try {
	            // contact CAS server to validate service ticket
	            Assertion casAssertion = ticketValidator.validate(ticket, getCasService());
	            // get principal, user id and attributes
	            AttributePrincipal casPrincipal = casAssertion.getPrincipal();
	            String userId = casPrincipal.getName();
	            log.debug("Validate ticket : {} in CAS server : {} to retrieve user : {}", new Object[]{
	                    ticket, getCasServerUrlPrefix(), userId
	            });
	            
	           Map<String, Object> map= casAssertion.getPrincipal().getAttributes();
	           
	           log.debug("id :"+map.get("id"));
	            
	            Map<String, Object> attributes = casPrincipal.getAttributes();
	            // refresh authentication token (user id + remember me)
	            casToken.setUserId(userId);
	            String rememberMeAttributeName = getRememberMeAttributeName();
	            String rememberMeStringValue = (String)attributes.get(rememberMeAttributeName);
	            boolean isRemembered = rememberMeStringValue != null && Boolean.parseBoolean(rememberMeStringValue);
	            if (isRemembered) {
	                casToken.setRememberMe(true);
	            }
	            // create simple authentication info
	            List<Object> principals = CollectionUtils.asList(userId, attributes);
	            PrincipalCollection principalCollection = new SimplePrincipalCollection(principals, getName());
	            return new SimpleAuthenticationInfo(principalCollection, ticket);
	        } catch (TicketValidationException e) { 
	            throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", e);
	        }
	}
	 
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	 
	 
	 
}
