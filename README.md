# springboot-redis-sentinel
스프링부트와 레디스H/A를 통한 세션관리 예제

#레디스/센티널 구성 
1.레디스 : 3대 ( 192.168.1.56:6379, 192.168.1.56:6479, 192.168.1.56:6579 )
2.센티널 : 3대 ( 192.168.1.56:7000, 192.168.1.56:7001, 192.168.1.56:7002 )


session.setAttribute();시 레디스에 세션 저장. 

자세한 내용: https://blog.naver.com/anstnsp?Redirect=Update&logNo=222280154100 
