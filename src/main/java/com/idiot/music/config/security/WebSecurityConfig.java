package com.idiot.music.config.security;
/**
 * @auther idiot
 * @date 2019/9/15 - 14:51
 **/

import com.idiot.music.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @ClassName:WebSecurityService
 * @Description:TODO
 * @Version:1.0
 **/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SysUserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("idiot").password("xx94362").roles("ADMIN");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //防止静态资源被拦截(仅举例，路径根据情况改。填写/static是无效的==)
        web.ignoring().antMatchers("/themes/**", "/script/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()// 该方法所返回的对象的方法来配置请求级别的安全细节
                .antMatchers("/login").permitAll() // 登录页面不拦截
                .antMatchers(HttpMethod.POST, "/checkLogin").permitAll()// 对于登录路径不进行拦截
                .and().formLogin()//配置登录
                .loginPage("/login").permitAll()//登录页面路径
                .loginProcessingUrl("/checkLogin").permitAll()//登录时表单提交路径
                .failureUrl("/login?error==true")//登录失败
                .defaultSuccessUrl("/index")//登录成功后跳转路径
                .and().logout()//登出配置
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))// 用户退出所访问的路径，Post方式
                .permitAll().logoutSuccessUrl("/login?logout=true") // 退出成功所访问的路径
                .and()
                .csrf().disable()
                .headers().frameOptions();// 允许iframe内呈现
    }
}
