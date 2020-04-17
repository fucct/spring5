#TIL
(공부 내용 정리 [Github](https://github.com/fucct/TIL))

- @Autowired 는 필드, Setter 메서드에 붙일 수 있다.
- 일치하는 Bean이 존재하지 않거나, 두 개 이상인 경우 exception 발생

### @Qualifier 애노테이션을 통해  의존 객체 선택

- @Bean 이 붙은 설정 메서드, @Autowired 이 붙은 주입 메서드 혹은 필드에서 사용할 수 있다.
- @Qualifier("빈 이름") 에 해당하는 빈을 선택하여 자동주입한다
- @Qualifier 가 없으면 기본 한정자(메서드 이름)

### 상, 하위 타입 관계와 Autowired

상위 타입 빈과 하위 타입 빈이 함께 있으면, 익셉션이 발생한다. 어떤 타입을 주입해야 할 지 모르기 때문이다. 따라서 Qualifier로 지정해주어야 한다.

### 의존 객체가 존재하지 않아도 되는 경우

1. required 속성을 false로 지정한다 → 존재하지 않으면 의존 객체 주입을 수행하지 않는다

    ~~~java
     @Autowired(required = false)
   ~~~

2. Optional을 사용한다 →존재하지 않으면 값이 없는 Optional 객체

    ~~~java
    @Autowired
    public void setDateFormatter(Optional<DateTimeFormatter> formatterOpt) {
        ...
    }
   ~~~

3. @Nullable 애노테이션을 사용한다 → 존재하지 않으면 null을 전달

    ~~~java
    @Autowired
    public void setDateFormatter(@Nullable DateTimeFormatter formatterOpt) {
        ...
    }
   ~~~

**차이점**

- required 속성과 Nullable의 차이점은 Nullable 사용 시 자동 주입할 빈이 존재하지 않아도 메서드가 호출된다는 것이다. (null로 초기화)
- Optional은 마찬가지로 빈이 존재하지 않으면 값이 없는 Optional 객체가 전달된다.

### 명시적 주입과 자동 주입 간의 관계

설정 클래스에서 Setter 메서드로 의존 객체를 명시적으로 해당 Setter 메서드에 Autowired가 붙어있으면 자동 주입에 일치하는 빈을 주입한다.- @Autowired 는 필드, Setter 메서드에 붙일 수 있다.
- 일치하는 Bean이 존재하지 않거나, 두 개 이상인 경우 exception 발생

### @Qualifier 애노테이션을 통해  의존 객체 선택

- @Bean 이 붙은 설정 메서드, @Autowired 이 붙은 주입 메서드 혹은 필드에서 사용할 수 있다.
- @Qualifier("빈 이름") 에 해당하는 빈을 선택하여 자동주입한다
- @Qualifier 가 없으면 기본 한정자(메서드 이름)

### 상, 하위 타입 관계와 Autowired

상위 타입 빈과 하위 타입 빈이 함께 있으면, 익셉션이 발생한다. 어떤 타입을 주입해야 할 지 모르기 때문이다. 따라서 Qualifier로 지정해주어야 한다.

### 의존 객체가 존재하지 않아도 되는 경우

1. required 속성을 false로 지정한다 → 존재하지 않으면 의존 객체 주입을 수행하지 않는다

~~~java
@Autowired(required = false)
~~~

2. Optional을 사용한다 →존재하지 않으면 값이 없는 Optional 객체

~~~java
@Autowired
public void setDateFormatter(Optional<DateTimeFormatter> formatterOpt) {
    ...
}
~~~

3. @Nullable 애노테이션을 사용한다 → 존재하지 않으면 null을 전달

~~~java
@Autowired
public void setDateFormatter(@Nullable DateTimeFormatter formatterOpt) {
...
}
~~~

**차이점**

- required 속성과 Nullable의 차이점은 Nullable 사용 시 자동 주입할 빈이 존재하지 않아도 메서드가 호출된다는 것이다. (null로 초기화)
- Optional은 마찬가지로 빈이 존재하지 않으면 값이 없는 Optional 객체가 전달된다.

### 명시적 주입과 자동 주입 간의 관계

설정 클래스에서 Setter 메서드로 의존 객체를 명시적으로 해당 Setter 메서드에 Autowired가 붙어있으면 자동 주입에 일치하는 빈을 주입한다.