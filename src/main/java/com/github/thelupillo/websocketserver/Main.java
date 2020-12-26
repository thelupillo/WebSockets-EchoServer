package com.github.thelupillo.websocketserver;

import spark.Spark;

public class Main {

	public static void main(String[] args) {
		Spark.port(4567);
		Spark.webSocket("/echo", EchoWebSocket.class);
		Spark.staticFileLocation("/public");
		Spark.init();
	}
}
