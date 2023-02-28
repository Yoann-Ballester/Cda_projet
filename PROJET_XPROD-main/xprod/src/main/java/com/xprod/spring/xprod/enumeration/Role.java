package com.xprod.spring.xprod.enumeration;

import static com.xprod.spring.xprod.constant.Authority.ADMIN_AUTHORITIES;
import static com.xprod.spring.xprod.constant.Authority.HR_AUTHORITIES;
import static com.xprod.spring.xprod.constant.Authority.MANAGER_AUTHORITIES;
import static com.xprod.spring.xprod.constant.Authority.SUPER_ADMIN_AUTHORITIES;
import static com.xprod.spring.xprod.constant.Authority.USER_AUTHORITIES;

public enum Role {
	
	ROLE_USER(USER_AUTHORITIES), //Droits : {"user:read"};
	
	ROLE_HR(HR_AUTHORITIES), //Droits : {"user:read", "user:update"};
	
	ROLE_MANAGER(MANAGER_AUTHORITIES), //Droits : {"user:read", "user:update"};
	 
	ROLE_ADMIN(ADMIN_AUTHORITIES), //Droits : {"user:read", "user:update", "user:create"};
	
	ROLE_SUPER_ADMIN(SUPER_ADMIN_AUTHORITIES); //Droits : {"user:read", "user:update", "user:create", "user:delete"};
	
	private String[] authorities;
	
	Role(String... authorities){ // Les 3 points(...) tu veux faire appelle à une liste d'éléments(de rôle), à un flux de données, en l'occurance la collection de donnée (les rôles)
		this.authorities=authorities;
	}

	public String[] getAuthorities() { //Uniquement un getter pour ne pas le modifier
		return authorities;
	}

}
