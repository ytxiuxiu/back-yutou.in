package in.yutou.site.common.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ExceptionResolver extends SimpleMappingExceptionResolver {
	
	protected static Logger logger = Logger.getRootLogger();
	private static String stackTrace;
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

    try {
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json");
    	response.setStatus(500);
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("error", true);
    	
    	// hide non-business error
    	if (ex instanceof BusinessException) {
    	  result.put("message", ex.getMessage());
    	} else {
    	  result.put("message", "Sorry, Something wrong with the Server. Please contact <ytxiuxiu@gmail.com>");
    	}

    	log(ex);
    	
    	ObjectMapper mapper = new ObjectMapper();
      PrintWriter writer = response.getWriter();
      writer.write(mapper.writeValueAsString(result));
      writer.flush();
    } catch (IOException e) {
      log(e);
    }
    return null;
	}
	
	public static void log(Throwable e) {
		if (checkIfItIsASystemException(e)) {
			getFullStackTrace(e);
    	logger.error(e.getClass().getName() + ": " + e.getMessage() + stackTrace);
    	stackTrace = "";
		}
	}
	
	public static void log(String string) {
    logger.info(string);
  }
	
	private static void getFullStackTrace(Throwable e) {
		stackTrace += getStackTraceString(e);
		Throwable cause = e.getCause();
		if (cause != null) {
			getFullStackTrace(cause);
		} else {
			return;
		}
	}
	
	private static String getStackTraceString(Throwable e) {
		StackTraceElement[] stackTraceElements = e.getStackTrace();
		String stackTrace = "";
		stackTrace += "\n    " + e.getClass().getName() + " " + e.getMessage();
		for (StackTraceElement stackTraceElement : stackTraceElements) {
			if (!stackTraceElement.getClassName().contains("$$")) {
				stackTrace += "\n        at " + stackTraceElement.getClassName() + ":" + stackTraceElement.getLineNumber();
			}
		}
		return stackTrace;
	}
	
	private static boolean checkIfItIsASystemException(Throwable e) {
		if (e instanceof BusinessException) {
			return false;
		} else {
			return true;
		}
	}
}
