package wojtek.net.securityFun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class MojKonfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                inMemoryAuthentication().
                withUser("wojtek").password("{noop}wojtek").roles("ADMIN","USER").
                and().
                withUser("user").password("{noop}user").roles("USER").
                and().
                withUser("hashowy").password("{bcrypt}$2a$10$8Xtv6WZ24gidN/A6m02R0O6zA6zEwp1AzX9mmYEk0P/yP7C/2s84K").roles("USER");
        // hash dla wojtek przy uzyciu BCRYPT  (ze zmiennym saltem) to $2a$10$8Xtv6WZ24gidN/A6m02R0O6zA6zEwp1AzX9mmYEk0P/yP7C/2s84K

        // jesli nie definiujemy enkodera to musi byc przed haslem {noop}
        //https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-encoding

        // https://able.bio/DavidLandup/password-encoding-with-spring-security--90gkzie

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/noSec").permitAll()
                .antMatchers("/secForAdmin").hasRole("ADMIN")
                .antMatchers("/secAny").authenticated()
                .and().httpBasic()
//        .and().formLogin() - domyslna stronka z formularzem logowania od spring boota
//        .and().formLogin().loginPage("jakas_strona"); - custom strona logowania
//        .and().formLogin().defaultSuccessUrl("/jakas_strona") - po udanym logowaniu lecimy na jakas custom stronke
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/out")).permitAll().logoutSuccessUrl("/noSec");

    }

    // to read

    // https://www.baeldung.com/spring-security-5-password-storage
    // https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt

}
