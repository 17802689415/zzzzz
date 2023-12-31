package com.it.config;

import org.springframework.ldap.core.support.LdapContextSource;
import javax.naming.Context;
import java.util.Hashtable;

public class SSLLdapContextSource extends LdapContextSource {
    public Hashtable<String, Object> getAnonymousEnv(){
        // 禁用jdk8以上对ldap的端点校验
        System.setProperty("com.sun.jndi.ldap.object.disableEndpointIdentification", "true");
        Hashtable<String, Object> anonymousEnv = super.getAnonymousEnv();
        anonymousEnv.put("java.naming.security.protocol", "ssl");
        anonymousEnv.put("java.naming.ldap.factory.socket", CustomSSLSocketFactory.class.getName());
        anonymousEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        return anonymousEnv;
    }
}

