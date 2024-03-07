package com.green.Nolloo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
        //csrf 공격에 대한 방어를 해지 하겠다.
        security.csrf(AbstractHttpConfigurer::disable)
                //authorizeHttpRequests 메소드 안에서 인증 및 인가 관리
                .authorizeHttpRequests(
                        c -> {
                            c.requestMatchers(
                                            // new AntPathRequestMatcher("/member/*")
                                            // ex > /member/a, /member/b, /member/c(가능) /member/abc/a (불가능)
                                            // new AntPathRequestMatcher("/member/**")
                                            // ex > /member/a, /member/b, /member/c, /member/abc/a (모두가능)
                                            new AntPathRequestMatcher("/item/list"),
                                            new AntPathRequestMatcher("/member/join"),
                                            new AntPathRequestMatcher("/member/loginPage")

                                    ).permitAll()
                                    .requestMatchers(
                                            new AntPathRequestMatcher("/admin")
                                    ).hasRole("ADMIN")
                                    .requestMatchers(
                                            new AntPathRequestMatcher("/itemAddForm")
                                    ).hasAnyRole("USER","ADMIN")
                                    .anyRequest().authenticated();
                        }
                )
                // 로그인 form을 활용해서 할 것이고,
                // 로그인 설정내용도 포함
                .formLogin(
                        formLogin ->{
                            // 로그인 페이지 url 설정
                            formLogin.loginPage("/member/loginPage")
                                    // 로그인 시 전달되는 id 및 pw의 name 속성값을 지정
                                    .usernameParameter("memberId")
                                    .passwordParameter("memberPw")
                                    // 로그인 기능이 실행되는 url
                                    .loginProcessingUrl("/member/login")
                                    // 로그인 성공 시 이동할 url
                                    // 두번째 매개변수로 true 를 넣으면 항상 지정한 url 로 이동!
                                    // 두번째 매개변수가 없으면 이전 페이지로 이동.
                                    // 이전페이지가 없다면 지정한 url로 이동
                                    .defaultSuccessUrl("/item/list")
                                    .failureUrl("/member/loginPage");

                        }
                ).logout(
                        logout -> {
                            // 해당 url 요청이 들어오면 시큐리티가 로그아웃 진행
                            logout.logoutUrl("/member/logout")
                                    // 로그아웃 성공 시 이동할 url
                                    .logoutSuccessUrl("/item/list")
                                    // 로그아웃 성공 시 세션 데이터 삭제
                                    .invalidateHttpSession(true);
                        }
                );
//                // 예외발생 시 처리해야 하는 내용 작성
//                .exceptionHandling(
//                        ex -> {
//                            ex.accessDeniedPage("/deny");
//                        }
//                );

        return security.build();
    }
}
