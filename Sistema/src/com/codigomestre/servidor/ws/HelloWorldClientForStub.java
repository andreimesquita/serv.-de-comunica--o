package com.codigomestre.servidor.ws;

public class HelloWorldClientForStub {
	public static void main(String[] args) {
		HelloWorldImplService helloService = new HelloWorldImplService();
		HelloWorld hello = helloService.getHelloWorldImplPort();
		System.out.println(hello.getHelloWorldMessage("Manisha"));
	}
}