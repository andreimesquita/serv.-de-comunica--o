package com.codigomestre.servidor.ws;

import javax.jws.WebService;

@WebService(endpointInterface="com.codigomestre.servidor.ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld {
	
    @Override
    public String getHelloWorldMessage(String myName){
        return("Hello World WS! ".concat(myName));
    }
    
	@Override
	public String getMyName(String nome) {
		return("Your name is ".concat(nome));
	}
	
}