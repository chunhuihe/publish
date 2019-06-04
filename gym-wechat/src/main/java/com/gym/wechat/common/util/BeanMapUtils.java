package com.gym.wechat.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/** 
 * @author huangjiangnan 
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年9月21日 下午6:27:44 
 * 类说明 
 */
public class BeanMapUtils {  
    /** 
     * bean 转化为实体 
     * @param bean 
     * @return 
     */  
    public static HashMap<String,String> beanToMap(Object bean){  
        HashMap<String,String> map = new HashMap<String,String>();  
        if(null == bean){  
            return map;  
        }  
        Class<?> clazz = bean.getClass();  
        BeanInfo beanInfo = null;  
        try {  
            beanInfo = Introspector.getBeanInfo(clazz);  
        } catch (IntrospectionException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();  
        for(PropertyDescriptor descriptor : descriptors){  
            String propertyName = descriptor.getName();  
            if(!"class".equals(propertyName)&&!"fail".equals(propertyName)&&!"success".equals(propertyName)){  
                Method method = descriptor.getReadMethod();  
                Object result;  
                try {  
                    result = method.invoke(bean);  
                    if(null != result){  
                    	if(result instanceof Number){
                    		Number num=(Number)result;
                    		if(num.doubleValue()<=0){
                    			continue;
                    		}
                    	}
                    		
                        map.put(propertyName, result.toString());  
                    }/*else{  
                        map.put(propertyName, "");  
                    }  */
                } catch (IllegalArgumentException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                } catch (IllegalAccessException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                } catch (InvocationTargetException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
            }  
        }  
          
        return map;  
    }  
    /** 
     * map 转化为 bean 
     * @param clazz 
     * @param map 
     * @return 
     */  
	@SuppressWarnings("unchecked")
	public static <T>T mapToBean(Class<T> clazz,Map<String,String> map){  
        Object object = null;  
        try {  
            object = clazz.newInstance();  
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);  
              
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();  
            for(PropertyDescriptor descriptor : descriptors){  
                String propertyName = descriptor.getName();  
                if(map.containsKey(propertyName)){  
                    String value = map.get(propertyName);  
                    Object[] args = new Object[1];  
                    args[0] = descriptor.getPropertyType().cast(value); 
                    descriptor.getWriteMethod().invoke(object, args);  
                }  
            }  
              
        } catch (InstantiationException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }catch (IntrospectionException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }catch (IllegalArgumentException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (InvocationTargetException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return (T)object;  
    }  
	
}  