package com.javaLang;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class Reflection {
	
	public static void main(String []args) throws ClassNotFoundException{
		String className = "com.javaLang.Van";
		if(args.length < 0){
			className = args[0];
		}
		Class<?> objClass = Class.forName(className);
		Method []methods = objClass.getMethods();
		int len = methods.length;
		System.out.println(len);
		for(Method m: methods){
			String methodName = m.getName();
			Parameter []params = m.getParameters();
			String paramsName = "";
			int len2 = params.length;
			for(int i = 0; i < len2; i++){
				if(i == len2-1)
					paramsName += params[i].getClass().getName() + " " + params[i].getName();
				else
					paramsName += params[i].getClass().getSimpleName() + " " + params[i].getName() + ", ";
				
			}
			String returnType = m.getReturnType().getSimpleName(); 
			System.out.println(returnType + " " + methodName + "(" + paramsName + ")");
		}
	
	}
	
}
