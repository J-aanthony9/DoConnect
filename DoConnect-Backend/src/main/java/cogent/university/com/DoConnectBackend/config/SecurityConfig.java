package cogent.university.com.DoConnectBackend.config;

import cogent.university.com.DoConnectBackend.filter.JwtFilter;
import cogent.university.com.DoConnectBackend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/authenticate").permitAll()
                .antMatchers("/user/addUser").permitAll()
                .antMatchers("/user/getAllUsers").permitAll()
                .antMatchers("/question/addQuestion").permitAll()
                .antMatchers("/question/getAllQuestion").permitAll()
                .antMatchers("/answer/addanswer").permitAll()
                .antMatchers("/chat/addMsg").permitAll()
                .antMatchers("/chat/getallmsg").permitAll()
                .antMatchers("/chat/getallmsgbetweenusers").permitAll()
                .antMatchers("/question/getQuestionById/**").permitAll()
                .antMatchers("/question/getQuestionByTitle/**").permitAll()
                .antMatchers("/question/getAllQuestionFalse").permitAll()
                .antMatchers("/question/updateQuestion/**").permitAll()
                .antMatchers("/question/deleteQuestionById/**").permitAll()
                .antMatchers("/answer/addAnswer").permitAll()
                .antMatchers("/answer/**/**/**").permitAll()
                .antMatchers("/answer/question/**").permitAll()
                .antMatchers("/answer/getAnswerById/**").permitAll()
                .antMatchers("/answer/updateAnswer/**").permitAll()
                .antMatchers("/question/deleteAnswerById/**").permitAll()
                .anyRequest().authenticated().and()
                .exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

