package omar.demo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    // Pointcut to match all public methods in service and repository layers
    @Pointcut("execution(public * omar.demo.Service.*.*(..)) || execution(public * omar.demo.Repository.*.*(..))")
    public void serviceAndRepositoryMethods() {}

    // Log method input before execution
    @Before("serviceAndRepositoryMethods()")
    public void logMethodInput(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("Entering method: {} with arguments: {}", methodName, args);
    }

    // Log method output after execution
    @AfterReturning(pointcut = "serviceAndRepositoryMethods()", returning = "result")
    public void logMethodOutput(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Exiting method: {} with result: {}", methodName, result);
    }
}

