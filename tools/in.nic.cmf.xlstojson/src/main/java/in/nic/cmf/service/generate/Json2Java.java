package in.nic.cmf.service.generate;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
*formatting a JSON response into Map and List
*/
public class Json2Java {
	
 /**
* getMap provides a Map representation of the JSON Object
* @param jsonResponse The JSON object string
* @return Map of JSONObject.
**/
protected Map<String, Object> getMap(String jsonResponse ) throws Exception {
  Map<String, Object> mapResponse = new HashMap<String, Object>();
  if (jsonResponse.startsWith("{")) {
    JSONObject jsonObj = JSONObject.fromObject(jsonResponse);
    toJavaMap(jsonObj, mapResponse);
  } else {
    throw new Exception("MalFormed JSON Array Response.");
  }
  return mapResponse;
}
/**
* toJavaMap converts the JSONObject into a Java Map
* @param o
* JSONObject to be converted to Java Map
* @param b
* Java Map to hold converted JSONObject response.
**/
private static void toJavaMap(JSONObject o, Map<String, Object> b) {
  Iterator<?> ji = o.keys();
  while (ji.hasNext()) {
    String key = (String) ji.next();
    Object val = o.get(key);
    if (val.getClass() == JSONObject.class) {
      Map<String, Object> sub = new HashMap<String, Object>();
      toJavaMap((JSONObject) val, sub);
      b.put(key, sub);
    } else if (val.getClass() == JSONArray.class) {
      List<Object> l = new ArrayList<Object>();
      JSONArray arr = (JSONArray) val;
      for (int a = 0; a < arr.length(); a++) {
        Map<String, Object> sub = new HashMap<String, Object>();
        Object element = arr.get(a);
        if (element instanceof JSONObject) {
          toJavaMap((JSONObject) element, sub);
          l.add(sub);
        } else {
          l.add(element);
        }
      }
      b.put(key, l);
    } else {
      b.put(key, val);
    }
  }
}
/**
* toJavaList converts JSON's array response into Java's List
* @param ar
* JSONArray to be converted to Java List
* @param ll
* Java List to hold the converted JSONArray response
**/

}