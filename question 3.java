package GlobalTrendProgrammingAssessment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class annotiationmethod {
}
public class LogExecutionTimeAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogExecutionTimeAspect.class);

    @Around("@annotation(com.example.annotations.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logger.info("{} executed in {} ms", joinPoint.getSignature(), executionTime);
        return proceed;
    }
}