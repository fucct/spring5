package spring5.domain;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Order(2)
public class CacheAspect {
    private final Map<Long, Object> cache = new HashMap<>();

//    @Pointcut("execution(public * spring5.domain.chap07..*(long))")
    public void cacheTarget() {

    }

    @Around("execution(public * spring5.domain.chap07..*(long))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        Long key = (Long) joinPoint.getArgs()[0];
        if (cache.containsKey(key)) {
            System.out.println("Cache 에서 구함 : " + key);
            return cache.get(key);
        }

        Object result = joinPoint.proceed();
        cache.put(key, result);
        System.out.println("Cache 에 추가 : " + key);
        return result;
    }
}
