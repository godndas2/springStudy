# springStudy
# ERROR
회원관리 - 필수입력 체크<br/>
관리자 로그인 - 상품등록 클릭시 에러<br/>
관리자 로그인 - 상품등록, 수정시 에러<br/>
AOP - rest Test ( 400 Error )<br/>
Interceptor - LoginInterceptor Error <br/>
Ajax File upload 확인 <br/>

# Spring Study Start
~~~
개발환경 : Eclipse Oxygen, Spring 4.3.20.RELEASE, Tomcat 8.5, Oracle, Mybatis, Maven, JDK1.8
~~~

> 공부 방법
~~~
Git clone 후, 새로 프로젝트를 생성해줍니다. ( 총 2개의 프로젝트가 생기겠죠? )
~~~
[Git clone](https://jwgye.tistory.com/38)

> 1 Day 1 Study를 기본 목표로 
# DAY 1 ( Logging 과 DB 연동 )
## Pom.xml
~~~ 
Oracle Setting, Mybits, logback, logging을 Clone받은 프로젝트에서 확인하고 추가해줍니다.
~~~
## root-context.xml
~~~
dataSource, sqlSessionFactory, sqlSession 추가
~~~
## xml파일 추가
~~~
logback.xml, log4j.xml, mybatis-config.xml을 추가해줍니다.
폴더의 위치는 해당 파일의 마우스 오른쪽을 클릭 - properties에서 확인하거나
파일을 열어서 작업창 상단에 탭에 마우스를 갖다대면 경로를 알 수 있습니다.
~~~
## 메인화면 만들기
~~~
1. menu.jsp, header.jsp를 만들고 main.jsp에 include 해줍니다.
2. MainController 생성
~~~
## 실행해보기
1. Tomcat의 Path를 "/"로 해줍니다.
2. Server - Tomcat - Modules - Edit("/") - 저장
3. 실행 -> 예:( localhost:8099/ )


