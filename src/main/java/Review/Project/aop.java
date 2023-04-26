package Review.Project;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.aspectj.AspectJAsyncConfiguration;
import org.springframework.stereotype.Component;

@Aspect
@Configuration
public class aop {


    //@Before(value = "execution(* Review.Project.controllers.*.*(..))")
    public void logStatementBefore(JoinPoint joinPoint) {

        System.out.println("Executing {}" + joinPoint.getArgs()[0]);
    }

    //@After(value = "execution(* Review.Project.controllers.*.*(..))")
    public void in(JoinPoint joinPoint) {

        Object[] signatureArgs = joinPoint.getArgs();

        //System.out.println("Arg: " );

    }

    public String Papopepo() throws Throwable {

        return "pito";

    }

    //@Around(value = "execution(* Review.Project.controllers.*.*(..))")
    public Object taskHandler(ProceedingJoinPoint joinPoint) throws Throwable {

            Object pene = Papopepo();

            System.out.println("Arg: " + joinPoint.getArgs()[0]);

            if (pene == "pito"){
                return pene;
            }

            else {
                Object obj = joinPoint.proceed();

                System.out.println(obj);

                return obj;
            }




    }





}
