package cogent.university.com.DoConnectBackend.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig  {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/user/authenticate").permitAll()
                .antMatchers("/user/addUser").permitAll()
                .antMatchers("/user/getAllUsers").permitAll()
                .antMatchers("/question/addQuestion").hasRole("USER")
                .antMatchers("/question/getAllQuestion").hasRole("USER")
                .antMatchers("/answer/addanswer").hasRole("USER")
                .antMatchers("/chat/addMsg").hasRole("USER")
                .antMatchers("/chat/getallmsg").hasRole("USER")
                .antMatchers("/chat/getallmsgbetweenusers").hasRole("USER")
                .antMatchers("/question/getQuestionById/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/question/getQuestionByTitle/**").hasRole("USER")
                .antMatchers("/question/getAllQuestionFalse").hasRole("ADMIN")
                .antMatchers("/question/updateQuestion/**").hasRole("ADMIN")
                .antMatchers("/question/deleteQuestionById/**").hasRole("ADMIN")
                .antMatchers("/answer/addAnswer").hasRole("USER")
                .antMatchers("/answer/question/**").hasRole("USER")
                .antMatchers("/answer/getAnswerById/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/answer/updateAnswer/**").hasRole("ADMIN")
                .antMatchers("/question/deleteAnswerById/**").hasRole("ADMIN")
                .anyRequest().authenticated();

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }


}
//
//     .antMatchers("/user/addUser/**").permitAll()
//             .antMatchers("/user/authenticate/**").permitAll()
//             .antMatchers("/question/addQuestion").hasAnyRole("USER")
//             .antMatchers("/question/getAllQuestion").hasAnyRole("USER")
//             .antMatchers("/question/deleteQuestionById/**").hasAnyRole("ADMIN")



//   .antMatchers("/user/authenticate").permitAll()
//           .antMatchers("/user/addUser").permitAll()
//           .antMatchers("/user/getAllUsers").permitAll()
//           .antMatchers("/question/addQuestion").permitAll()
//           .antMatchers("/question/getAllQuestion").permitAll()
//           .antMatchers("/answer/addanswer").permitAll()
//           .antMatchers("/chat/addMsg").permitAll()
//           .antMatchers("/chat/getallmsg").permitAll()
//           .antMatchers("/chat/getallmsgbetweenusers").permitAll()
//           .antMatchers("/question/getQuestionById/**").permitAll()
//           .antMatchers("/question/getQuestionByTitle/**").permitAll()
//           .antMatchers("/question/getAllQuestionFalse").permitAll()
//           .antMatchers("/question/updateQuestion/**").permitAll()
//           .antMatchers("/question/deleteQuestionById/**").permitAll()
//           .antMatchers("/answer/addAnswer").permitAll()
//           .antMatchers("/answer/**/**/**").permitAll()
//           .antMatchers("/answer/question/**").permitAll()
//           .antMatchers("/answer/getAnswerById/**").permitAll()
//           .antMatchers("/answer/updateAnswer/**").permitAll()
//           .antMatchers("/question/deleteAnswerById/**").permitAll()


