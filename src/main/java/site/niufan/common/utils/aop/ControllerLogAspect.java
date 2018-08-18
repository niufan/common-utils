package site.niufan.common.utils.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

@Aspect
@Component
public class ControllerLogAspect {

	/**
	 *	定义切入点
	 */
	@Pointcut("execution(* *..controller.*.*(..))")
	public void controller() {
	}

	/**
	 * 方法执行前后执行一些操作
	 * @param proceedingJoinPoint 方法代理
	 * @return 方法执行返回值
	 * @throws Throwable controller 异常需要抛出，让全局异常处理
	 */
	@Around("controller()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Logger logger = LoggerFactory.getLogger(proceedingJoinPoint.getTarget().getClass());
		long start = System.currentTimeMillis();
		Object object = "";
		Signature signature = proceedingJoinPoint.getSignature();
		Object[] args = proceedingJoinPoint.getArgs();
		logger.info(String.format("Method    : %s", signature));
		logger.info(String.format("Arguments : %s", toString(args)));
		logger.info(String.format("Begin     : %s", dateFormat.format(new Date(start))));
		try {
			object = proceedingJoinPoint.proceed();
		} catch (Throwable throwable) {
			object = throwable.toString();
			throw throwable;
		} finally {
			long end = System.currentTimeMillis();
			logger.info(String.format("End       : %s", dateFormat.format(new Date(end))));
			logger.info(String.format("Spend     : %s(ms)", end - start));
			logger.info(String.format("Return    : %s", toString(object)));
		}
		return object;
	}

	private String toString(Object... objects) {
		StringBuilder sb = new StringBuilder();
		for (Object object: objects) {
			sb.append(toString(object)).append(",");
		}
		int last = sb.lastIndexOf(",");
		if (last > -1) {
			sb.deleteCharAt(last);
		}
		return sb.toString();
	}

	private String toString(Iterable<?> iterable) {
		StringBuilder sb = new StringBuilder();
		Iterator<?> iterator = iterable.iterator();
		while (iterator.hasNext()) {
			sb.append(toString(iterable)).append(",");
		}
		int last = sb.lastIndexOf(",");
		if (last > -1) {
			sb.deleteCharAt(last);
		}
		return sb.toString();
	}

	private String toString(Object object) {
		return object.toString();
	}
}
