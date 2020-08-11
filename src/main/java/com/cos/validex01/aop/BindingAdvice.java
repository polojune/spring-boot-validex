package com.cos.validex01.aop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.cos.validex01.RespDto;
import com.cos.validex01.StatusCode;

//공통관심사 :advice
@Component
@Aspect //Aop로 등록 완료
public class BindingAdvice {
       
	   @Before("execution(* com.cos.validex01.test.BindControllerTest.*(..))")
	   public void test1() {
		   
		  
		    System.out.println("BindController에 오신것을 환영합니다.");
	   }
	   
	   @After("execution(* com.cos.validex01.test.BindControllerTest.*(..))")
	   public void test2() {
		   
		 
		    System.out.println("BindControllerTest를 이용해주셔서 감사합니다.");
	   }
	
	
	   //@Before,@After,@Around
	   @Around("execution(* com.cos.validex01..*Controller.*(..))")
       public Object validationHandler(ProceedingJoinPoint proceedingJoinPoint ) throws Throwable {
       	   String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
    	   String method = proceedingJoinPoint.getSignature().getName(); 
    	   System.out.println("type: " + type);
    	   System.out.println("method: " + method);
    	   
    	   Object[] args = proceedingJoinPoint.getArgs(); //조인 포인트의 파라미터
    	   
    	   for(Object arg: args) {
    		   if(arg instanceof BindingResult) {
    			   BindingResult bindingResult = (BindingResult) arg;
    			   
    				if(bindingResult.hasErrors()) {
    		            Map<String, String> errorMap = new HashMap<>();
    		            
    		            for(FieldError error : bindingResult.getFieldErrors()) {
    		            	errorMap.put(error.getField(), error.getDefaultMessage());
    		            }
    		           
    		            RespDto<?> respDto = RespDto.builder()
    		            		.statusCode(StatusCode.FAIL)
    		            		.msg(method +"요청에 실패하였습니다.")
    		            		.data(errorMap)
    		                    .build();             
    		        
    		            return new ResponseEntity<RespDto>(respDto, HttpStatus.BAD_REQUEST);
    				}
    		   }
    	   }
    	   
    	   return proceedingJoinPoint.proceed();
       }
}
