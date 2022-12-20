package com.IdolTicketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 주석을 사용하면 아래 3가지 어노테이션을 활성화 합니다.
 * @EnableAutoConfiguration: 클래스 경로에 있는 Bean을 자동으로 Spring Boot 매모리 내에 설정합니다.
 * 클래스 경로란 하위 경로를 의미한다.
	 * src/main/java/
	 * src/main/resources/
 * @ComponentScan: @Component 애플리케이션에 있는 패키지 내에 빈을 사용할 수 있게 활성화합니다.
 * @Configuration: 컨텍스트에 추가 빈을 등록하거나 추가 구성 클래스를 가져올 수 있게 설정합니다.
 * 어플리케이션 컨텍스트란?(Application Context)
	 * Spring에서는 빈 팩토리를 상속받아 확장한 애플리케이션 컨텍스트(Application Context)를 주로 사용한다.
	 * 생성 정보와 연관관계 정보에 대한 설정을 읽어 처리한다. 예를 들어 @Configuration과 같은 어노테이션이 대표적인 IoC의 설정정보이다.
 */
@SpringBootApplication
public class IdolTicketingApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdolTicketingApplication.class, args);
	}

}
