package com.mrwang.happytime.musicserver.aop;

import com.mrwang.happytime.musicserver.annotation.ParameterCheck;
import com.mrwang.happytime.musicserver.annotation.ParameterChecks;
import com.mrwang.happytime.musicserver.exception.AopRuntimeException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class ParameterCheckAspect {

    private static ConcurrentHashMap<Class,List<Method>> cacheGetter = new ConcurrentHashMap();


    @Pointcut("@annotation(com.mrwang.happytime.musicserver.annotation.ParameterChecks)")
    public void parameterChecks(){

    }
    @Pointcut("@annotation(com.mrwang.happytime.musicserver.annotation.ParameterCheck)")
    public void parameterCheck(){

    }
    @Before("parameterChecks() || parameterCheck()")
    public void before(JoinPoint point) throws NoSuchMethodException {
        Object[] objects = point.getArgs();
        if(objects.length == 0){
            throw new AopRuntimeException(0,"参数不符合规范");
        }
        Class[] classes = new Class[objects.length];
        for(int i=0;i<objects.length;i++){
            classes[i] = objects[i].getClass();
        }
        Signature signature = point.getSignature();
        String name = signature.getName();
        Class declaringType = signature.getDeclaringType();
        Method method = declaringType.getMethod(name,classes);
        checkParameters(method,objects,classes);
    }

    /**
     * method为执行的方法 objects为参数集合（为了规范，我们可以只检查第一个PO的参数，所有的PO参数写在第一位）classes为objects数组对应的类
     * 参数检查的实现方式为获取class的所有getter方法，只有检查参数有值，其他的都为null
     * @param method
     * @param objects
     * @param classes
     */
    private void checkParameters(Method method, Object[] objects, Class[] classes) {
        Object o = objects[0];
        Class clazz = classes[0];
        ParameterCheck[] parameterChecks = getParameterAnnotation(method);
        if(parameterChecks.length == 0){
            throw new AopRuntimeException(0,"参数请求错误");
        }
        if(!traverseParameterChecks(parameterChecks,o,clazz)){
            throw new AopRuntimeException(0,"参数请求错误");
        }
    }

    private boolean traverseParameterChecks(ParameterCheck[] parameterChecks, Object o, Class clazz) {
        String[] strings = new String[parameterChecks.length];
        boolean[] booleans = new boolean[parameterChecks.length];
        Class[] types = new  Class[parameterChecks.length];
        for(int i=0;i<parameterChecks.length;i++){
            strings[i] = parameterChecks[i].value();
            types[i] = parameterChecks[i].type();
            booleans[i] = parameterChecks[i].notNull();
        }
        List<Method> methods = getAllGetterMethod(clazz);
        List<Method> getter = new ArrayList<>(methods);
        for(int i=0;i<strings.length;i++){
            String s = strings[i];
            Method method = null;
            try {
                method  = findMethodInGetter(s, getter);
                Object invoke = method.invoke(o);
                if(invoke == null && booleans[i] == true){
                    return false;
                }
            }catch (Exception e){
                return false;
            }
        }
        for(Method m:getter){
            try {
                if(m.invoke(o) != null){
                    return false;
                }
            } catch (Exception e) {

            }
        }
        return true;
    }

    private Method findMethodInGetter(String s, List<Method> getter) {
        for(Method method:getter){
            String name = method.getName();
            if(name.toLowerCase().substring(2).equals(s.toLowerCase()) || name.toLowerCase().substring(3).equals(s.toLowerCase())){
                getter.remove(method);
                return method;
            }
        }
        return null;
    }

    private List<Method> getAllGetterMethod(Class clazz) {
        if(cacheGetter.get(clazz) != null){
            System.out.println("直接从缓存读取");
            return cacheGetter.get(clazz);
        }
        System.out.println("缓存中没有相应的数据，构建缓存");
        List<Method> methods = new ArrayList<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        Method method = null;
        for (Field field : declaredFields) {
            if (field.getType() == Boolean.class) {
                String methodName = "is" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                try{
                    method = clazz.getMethod(methodName);
                    methods.add(method);
                    continue;
                }catch (Exception e){

                }

            }
            String methodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            try{
                method = clazz.getMethod(methodName);
                methods.add(method);
            }catch (Exception e){

            }
        }
        cacheGetter.put(clazz,new ArrayList<>(methods));
        return methods;
    }

    /**
     * 获取方法上的ParameterCheck注解
     * @param method
     * @return
     */
    private ParameterCheck[] getParameterAnnotation(Method method) {
        ParameterCheck parameterCheck = null;
        ParameterCheck[] parameterChecks = null;
        if( method.isAnnotationPresent(ParameterCheck.class)){
            parameterCheck = method.getDeclaredAnnotation(ParameterCheck.class);
            return new ParameterCheck[]{parameterCheck};
        }
        if(method.isAnnotationPresent(ParameterChecks.class)){
            parameterChecks = method.getDeclaredAnnotation(ParameterChecks.class).value();
            return parameterChecks;
        }
        return new ParameterCheck[0];
    }

}
