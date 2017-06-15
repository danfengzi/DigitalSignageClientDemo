package com.aktek.digitalsignage.util;

import java.util.List;

public class GlobalUtil {
	public static String toStringByEmpty(String value){
		if (value==null){
			return "";
		}		
		return value; 	
	}	
	public static String toString4Array(Object[] array, int index){
		if (array==null){
			return "";
		}		
		else if (array[index]==null){
			return "";
		}
		
		return array[index].toString(); 	
	}
	public static String toString4List(@SuppressWarnings("rawtypes") List list, int index){
		if (list==null || list.size() == 0 || list.get(index)==null){
			return "";
		}		
		
		return list.get(index).toString(); 	
	}
	
	public static Object toObject4ListElement(@SuppressWarnings("rawtypes") List list, int index){
		if (list==null || list.size() == 0 || list.get(index)==null){
			return null;
		}		
		
		return (Object)list.get(index); 	
	}
}
