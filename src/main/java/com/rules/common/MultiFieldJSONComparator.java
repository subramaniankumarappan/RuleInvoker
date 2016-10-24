/**
* File  : RulesJSONComparator.java
* Description          : This RulesJSONComparator is a dynamic comparator class for JSON Objects based on list of field names   
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Oct 24, 2016      	595251  	 Initial version
*/
package com.rules.common;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.simple.JSONObject;

/**
 * @author 595251
 *
 */
public class MultiFieldJSONComparator implements Comparator<JSONObject> {
	

	private final List<String> fieldNameList;
	
    public MultiFieldJSONComparator(List<String> fieldNameList) {
        this.fieldNameList = fieldNameList;
        
    }

    @Override
	public int compare(JSONObject obj1, JSONObject obj2) {
    	 
    	int compareValue =0;  
    	String value1=null, value2=null;
    	if(fieldNameList != null && fieldNameList.size() > 0){
        	for (int i=0; i< fieldNameList.size(); i++){     
        		
        		value1 = (Object)obj1.get(fieldNameList.get(i)) == null ? "":((Object)obj1.get(fieldNameList.get(i))).toString();
        		value2 = (Object)obj2.get(fieldNameList.get(i)) == null ? "":((Object)obj2.get(fieldNameList.get(i))).toString();

        		compareValue = value1.compareTo(value2);
        		if (compareValue != 0)
        			return compareValue;
        	}
        }else{//if no fields are specified
        	
        }
    	
    	return compareValue;
    }

    
}
