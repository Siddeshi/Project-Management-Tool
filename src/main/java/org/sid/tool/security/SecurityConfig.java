/*
package org.sid.tool.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter   {

    @Value("${ldap.urls}")
    private String ldapUrls;

    @Value("${ldap.base.dn}")
    private String ldapBaseDn;

    @Value("${ldap.username}")
    private String ldapSecurityPrincipal;

    @Value("${ldap.password}")
    private String ldapPrincipalPassword;

    @Value("${ldap.user.dn.pattern}")
    private String ldapUserDnPattern;

    @Value("${ldap.enabled}")
    private String ldapEnabled;

    @Value("${user.name}")
    private String basicUserName;

    @Value("${user.password}")
    private String basicUserPassword;

    @Value("${user.roles}")
    private String userRoles;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        if(Boolean.parseBoolean(ldapEnabled)) {
            auth.ldapAuthentication()
                    .contextSource()
                    .url(ldapUrls + ldapBaseDn)
                    .managerDn(ldapSecurityPrincipal)
                    .managerPassword(ldapPrincipalPassword)
                    .and()
                    .userDnPatterns(ldapUserDnPattern);
        } else {
            auth.inMemoryAuthentication()
                    .withUser(basicUserName).password(basicUserPassword).roles(userRoles);
        }
    }

}*/
