package com.yc.bean1_recursion;

public class Test2 {

	public static void main(String[] args) {
		System.out.println(  fib( 3,1) );
	

	}
	
	//fib����
	private static int fib(int i) {
		//��ֹ������д
		if( i==1 ){
			return 1;
		}else if( i==2 ){
			return 1;
		}else{
			return fib(i-1)+fib(i-2);
		}
	}
	
	//fib����
	private static int fib(int i,int a) {
		//��ֹ������д
		 if( i==1 ){
			return 1;
		}else if( i==2){
			return 1;
		}else{
			return fib(i-1,i*a);
		}
	}
	
	

}
