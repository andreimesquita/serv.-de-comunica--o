package com.codigomestre.servidor.ws;

import javax.xml.ws.Endpoint;
 
public class HelloWorldPublisher {
 
   public static void main(String[] args){
       Endpoint.publish("http://localhost:9000/ws/hello", new HelloWorldImpl());
       System.out.println("\nWeb service published @ http://localhost:9000/ws/hello");
       System.out.println("You may call the web service now");
   }
}