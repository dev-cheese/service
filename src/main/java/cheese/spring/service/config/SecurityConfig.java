package cheese.spring.service.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // TODO: 23/10/2addResourceHandlers018 임시로 시큐리티 전부 허용 -yun
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/**").permitAll();
    }

}
