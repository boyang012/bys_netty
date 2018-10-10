package com.byss.main;

import com.byss.http.server.HttpServer;

public class TestMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		if (args.length != 1) {
            System.err.println(
                    "Usage: " + HttpServer.class.getSimpleName() +
                            " <port>");
            return;
        }
        int port = Integer.parseInt(args[0]);
        System.out.println("port : "+port);
        new HttpServer(port).start();
	}

}
