package in.yutou.site.common.auth.powercontrol;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sun.javafx.binding.StringFormatter;

import in.yutou.site.common.auth.GoogleAuth;
import in.yutou.site.common.auth.dao.UserDao;
import in.yutou.site.common.auth.domain.Action;
import in.yutou.site.common.auth.domain.Group;
import in.yutou.site.common.auth.domain.User;
import in.yutou.site.common.auth.service.GroupService;
import in.yutou.site.common.auth.service.UserService;
import in.yutou.site.common.exception.BusinessException;

@Component
public class PowerControlAspect {
	
  @Autowired
  private UserService userService;
  
  @Autowired
  private GroupService groupService;
  
  @Autowired
  private GoogleAuth googleAuth;
  
  protected static Logger logger = Logger.getRootLogger();
  
	public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object result = null;

		// get power control annotation
		Method[] methods = proceedingJoinPoint.getTarget().getClass().getMethods();
    Method method = null;
    for (Method m : methods) {
      if (m.getName().equals(proceedingJoinPoint.getSignature().getName())) {
        method = m;
      }
    }
    
		String[] values = null;
		if (method.isAnnotationPresent(PowerControl.class)) {
		  PowerControl powerControl = method.getAnnotation(PowerControl.class);
		  values = powerControl.value();
		}
		
		if (values == null || values.length == 0) {
			return proceedingJoinPoint.proceed();
		}
		
		List<String> requires = new ArrayList<String>();
    for (String value : values) {
      requires.add(value);
    }

		// get user
    ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = requestAttributes.getRequest();
    
    String idToken = request.getParameter("idToken");
    
    Group group = null;
    if (idToken.equals("no-login")) { // handle no login user
      group = groupService.getGroupByName("no-login");
    } else {  // handle login user
  		User _user = userService.getUserById(googleAuth.getUserId(idToken));
  		// set user to request attribute, set that controller can get it
  		request.setAttribute("user", _user);
  		
  		// root has supreme power!
  		if (_user.getGroup().getName().equals("root")) {
  		  result = proceedingJoinPoint.proceed();
  		}
  		group = _user.getGroup();
  		
  		logger.debug("[Auth] check power with " + _user);
  		logger.debug("[Auth] requires: " + requires);
    }
    
		// check power
		for (String require : requires) {
		  boolean meet = false;
		  for (Action action : group.getActions()) {
		    if (action.getName().equals(require)) {
		      meet = true;
		    }
		  }
		  if (!meet) {
		    logger.error("[Auth] check power with " + group);
		    logger.error("[Auth] Permission denied, require: " + require);
		    throw new BusinessException("Permission denied");
		  }
		}
	
		result = proceedingJoinPoint.proceed();

		return result;
  } 
}
