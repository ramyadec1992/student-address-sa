package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class AspectConfig {

	Logger logger=LoggerFactory.getLogger(getClass());

	/*
	 * @Before(value= "execution(* com.example.controller.*.*(..))") public void
	 * beforeAdvice(JoinPoint jointPoint) { logger.info("Inside before advice"); }
	 */

	//method and getting one parameter
	/*
	 * @Before(value=
	 * "execution(* com.example.controller.*.*(..)) and args(object)") public void
	 * beforeAdvice(JoinPoint jointPoint, Object object) {
	 * 
	 * logger.info("Request = " + object); }
	 */
	/*
	 * @Before(value=
	 * "execution(* com.example.controller.*.*(..)) and args(object1, object2)")
	 * public void beforeAdvice(JoinPoint jointPoint, Object object1, Object
	 * object2) {
	 * 
	 * logger.info("Request = " + object1); logger.info("Request = " + object2); }
	 */
	/*@After(value= "execution(* com.example.controller.*.*(..)) and args(object1, object2)")
	public void Advice(JoinPoint jointPoint, Object object1, Object object2)
	{

		logger.info("Request = " + object1);
		logger.info("Request = " + object2);
	}*/


	/*
	 * @AfterReturning(value=
	 * "execution(* com.example.controller.*.*(..)) and args(object1, object2)",
	 * returning ="returningObject") public void AfterReturning(JoinPoint
	 * jointPoint, Object object1, Object object2, Object returningObject) {
	 * 
	 * //logger.info("Request = " + object1); //logger.info("Request = " + object2);
	 * logger.info("response = " + returningObject);
	 * 
	 * }
	 */
	/*
	 * @AfterReturning(value=
	 * "execution(* com.example.controller.*.*(..)) and args(object)", returning
	 * ="returningObject") public void AfterReturning(JoinPoint jointPoint, Object
	 * object, Object returningObject) {
	 * 
	 * logger.info("Request = " + object); //logger.info("Request = " + object2);
	 * logger.info("response = " + returningObject);
	 * 
	 * }
	 */

	@Around(value= "execution(* com.example.controller.*.*(..)) and args(object)")
	public void AroundAdvice(ProceedingJoinPoint proceedingJointPoint, Object object)
	{

		logger.info("Request = " + object);

		Object returningObject =null;
		try
		{
			returningObject=proceedingJointPoint.proceed();
		}
		catch(Throwable e)
		{
			e.printStackTrace();			
		}


		logger.info("response = " + returningObject);

	}
}
