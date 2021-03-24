package com.example.redis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TestController {
    


    private final StringRedisTemplate redisTemplate ; 

    @GetMapping("/test")
    public String test(HttpSession req ) {
        System.out.println("#### 레디스테스트 시작 ####");
        // HttpSession session = req.setAttribute(name, value);
        
        String member = "아s개s1";
        req.setAttribute("good", "goooood");

        
        // final ValueOperations<String, String> strValOperation = redisTemplate.opsForValue(); 
        // strValOperation.set("JSESSIONID", "zzz");

        // System.out.println("session : " + strValOperation.get("JSESSIONID"));

        return "성공!"; 
    }

    @GetMapping("/getsession")
    public String getSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String member = (String) session.getAttribute("member");
        System.out.println("세션멤버:"+member);
        return member;
    }
}
