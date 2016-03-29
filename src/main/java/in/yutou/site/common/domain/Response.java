package in.yutou.site.common.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Response, be used to generate response json
 * @author xiuxiu
 *
 */
public class Response {
  
  private Exception error;
  
  private String objectName;
  
  private Object object;
  
  public Response() {
    
  }
  
  /**
   * Create the response
   * @param objectName the name of object that need to be showed in the json.
   *  The response json may look like this
   *  {
   *    error: ...,
   *    objectName: ...
   *  }
   */
  public Response(String objectName) {
    this.objectName = objectName;
  }
  
  public Map<String, Object> getResponse() {
    Map<String, Object> response = new HashMap<String, Object>();
    response.put("error", error == null ? null : error.getMessage());
    response.put(objectName, object);
    return response;
  }

  public Exception getError() {
    return error;
  }

  public void setError(Exception error) {
    this.error = error;
  }

  public String getObjectName() {
    return objectName;
  }

  public void setObjectName(String objectName) {
    this.objectName = objectName;
  }

  public Object getObject() {
    return object;
  }

  public void setObject(Object object) {
    this.object = object;
  }

}
