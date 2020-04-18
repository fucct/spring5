#sp5-chap07
### 주어진 모델에 추가적인 기능을 구현하려면?

실행 시간을 측정하는 기능을 추가한다면 → 모델 앞과 뒤에 시간측정을 한 뒤 빼서 출력

그렇다면.. Recursive의 경우? → 기존의 코드를 수정하기 보단 메서드 호출 전후로 구한다..

메서드 전후로 구하는 경우 여러개의 메서드에 대해 같은 코드가 반복되므로 변경에 취약해진다. → 이 때 프록시 객체를 사용한다

### 프록시 객체를 사용하면

- 기존 코드를 변경하지 않고 실행 시간을 출력할 수 있다.
- 실행 시간 구하는 코드의 중복 제거

### 프록시 객체의 역할

→ factorial() 기능 자체를 직접 구현하기 보단, 다른 객체에 factorial() 실행을 위임한다. (핵심 기능을 구현하지 않는다.)

→ 계산 기능 외의 다른 기능(공통 기능)을 실행한다

즉 프록시 객체란 실행은 다른 객체에 위임하고 부가적인 기능을 제공하는 객체를 의미한다.

`ExeTimeCalculator`는 프록시이고 `ImpeCalculator` 가 프록시 대상 객체가 된다.

사실 프록시는 접근 제어 관점에 초점이 맞춰져 있고, 데코레이터는 기능 추가와 확장에 초점이 맞춰져 있기 때문에 위 예제는 데코레이터에 가깝다.

### AOP란

AOP는 여러 객체에 공통으로 적용할 수 있는 기능을 분리해서 재사용성을 높여주는 프로그래밍이다. 즉 핵심 기능의 코드를 수정하지 않으면서 공통 기능을 삽입하는 것이다.

- 컴파일 시점 → AspectJ
- 클래스 로딩 시점 → AspectJ
- 런타임에 프록시 객체를 생성 → Spring

### AOP 용어

- Advice : 언제 공통 기능을 적용할 지
- Joinpoint : Advice를 적용 가능한 지점
- Pointcut : Jointpoint 들 중 실제로 Advice가 적용되는 부분
- Weaving : Advice를 핵심 코드에 적용하는 것
- Aspect : 공통으로 적용되는 기능 (트랜잭션, 보안)

### Advice의 종류

- Before : 메서드 호출 전
- After Returning : 예외 없이 메서드가 실행된 이후
- After Throwing : 예외 발생한 경우
- After : 예외와 관계없이 메서드가 실행된 이후
- Around : 메서드 실행 전, 후

### 사용방법

- Aspect로 사용할 클래스에 @Aspect
- Pointcut으로 사용할 지점에 @Pointcut → target
- 공통 기능을 구현한 메서드에 @Around → ProceedingJoinPoint.proceed() : 기능 수행

### 프록시 생성 방식

스프링 AOP를 위한 프록시 객체를 생성할 때 실제 생성할 빈 객체가 인터페이스를 상속하면 인터페이스를 이용해서 프록시 객체를 생성한다. → proxyTargetClass = true 속성을 통해 인터페이스가 아닌 클래스로 프록시를 생성하도록 한다.

CacheAspect → ExeTimeAspect → Calculator 순서로 적용

적용 순서를 지정하기 위해선 Aspect 클래스에 @Order(숫자) 애노테이션을 적용한다

### 공통 Pointcut 활용

Pointcut 말고 Around에 직접 execution 명시자를 지정해 줄 수 있다

다른 클래스에서 같은 Pointcut을 사용하고 싶다면 pointcut 메서드를 public 으로

공통 포인트 컷 클래스를 만든다