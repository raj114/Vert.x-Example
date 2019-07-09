package com.demo1.vertex.tutorials1;

import java.util.HashSet;
import java.util.Set;

import io.vertx.core.AbstractVerticle;

import io.vertx.core.Future;


import io.vertx.core.http.HttpMethod;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

public class ApplicationVerticle extends AbstractVerticle
{
	
	@Override
	public void start(Future<Void> startFuture) throws Exception {
			System.out.println("raj");
			Router router = Router.router(vertx);
	
			 Set<String> allowedHeaders = new HashSet<>();
			    allowedHeaders.add("x-requested-with");
			    allowedHeaders.add("Access-Control-Allow-Origin");
			    allowedHeaders.add("origin");
			    allowedHeaders.add("Content-Type");
			    allowedHeaders.add("accept");
			    

			    Set<HttpMethod> allowedMethods = new HashSet<>();
			    allowedMethods.add(HttpMethod.GET);
			    allowedMethods.add(HttpMethod.POST);
			    allowedMethods.add(HttpMethod.OPTIONS);
			    /*
			     * these methods aren't necessary for this sample, 
			     * but you may need them for your projects
			     */
			    allowedMethods.add(HttpMethod.DELETE);
			    allowedMethods.add(HttpMethod.PATCH);
			    allowedMethods.add(HttpMethod.PUT);

			    router.route().handler(CorsHandler.create("*").allowedHeaders(allowedHeaders).allowedMethods(allowedMethods));

		    
		 	 router.route("/").handler(routingContext -> {
		      HttpServerResponse response = routingContext.response();
		      response
		          .putHeader("content-type", "text/html")
		          .end("<h1>Hello from my first Vert.x 3 application</h1>");
		    });
		 	 
		 	 
		 	router.route().handler(BodyHandler.create());
		    router.post("/postval").handler(this::addOne);
		    router.get("/getval/:id").handler(this::getOne);
		    router.put("/changeval/:id").handler(this::updateOne);
		    

		    // Create the HTTP server and pass the "accept" method to the request handler.
		 	 vertx
		        .createHttpServer()
		        .requestHandler(router::accept)
		        .listen(8686
		           
		        );

		  }
	
		
		 @Override
		  public void stop() throws Exception {
		    System.out.println("Garade");
		  }

		  
		 
		  
		  private void getOne(RoutingContext routingContext) {
			  String id = routingContext.request().getParam("id");
			  Postgresql ps = new Postgresql();
			
			  
			  HttpServerResponse response=routingContext.response();
		   		response
		   		.putHeader("content-type", "application/json; charset=utf-8")
		        .end((String)ps.GetUsername(id));
			  
		  }
		  
		  
		  private void addOne(RoutingContext routingContext) {
			  JsonObject body = routingContext.getBodyAsJson();
			 
			  Postgresql ps = new Postgresql();
			  ps.AddValues(body);
			    HttpServerResponse response=routingContext.response();
		   		response
		   		.putHeader("content-type", "application/json; charset=utf-8")
		        .end(Json.encodePrettily("Success"));
		
			  }
		  
		  
		  
		  private void updateOne(RoutingContext routingContext) {
			    final String id = routingContext.request().getParam("id");
			    System.out.println(id);
			    JsonObject json = routingContext.getBodyAsJson();
			    System.out.println(json);
			    if (id == null || json == null) {
			      routingContext.response().setStatusCode(400).end();
			    } else {
			    	 Postgresql ps = new Postgresql();
			    	 ps.UpdateValues(id,json);
			    	 
			    	HttpServerResponse response=routingContext.response();
			   		response
			   		.putHeader("content-type", "application/json; charset=utf-8")
			        .end(Json.encodePrettily("Success"));
			
			    }
			  }
		  
		  
		  
		  
		  
//		 public static void main(String[] args) {
//	          Vertx vertx = Vertx.vertx();
//	          vertx.deployVerticle(ApplicationVerticle.class.getName() );
//          
//		  }
			  
		  
		 
}