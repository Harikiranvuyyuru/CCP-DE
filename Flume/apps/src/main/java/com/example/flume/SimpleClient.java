package com.example.flume;

import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientFactory;
import org.apache.flume.event.EventBuilder;
import java.nio.charset.Charset;

public class SimpleClient {

	public static void main(String[] args){
		System.out.println("*********** Starting");
		RPCClientFacade cf = new RPCClientFacade();
		cf.init("localhost", 41414);
		System.out.println("Connection Established!!");

		// Send 10 events to the remote Flume agent. That agent should be
		// configured to listen with an AvroSource.
		System.out.println("\nSending data ...");
		String sampleData = "Hello Flume!";
		for (int i = 0; i < 10; i++) {
			cf.sendDataToFlume(sampleData);
		}
		System.out.println("\nData sent!!\n*********** Closing connection");
		cf.cleanUp();
	}	
	static class RPCClientFacade {

		private RpcClient client;
		private String hostname;
		private int port;

		public void init(String hostname, int port) {
			this.hostname = hostname;
			this.port = port;

			this.client = RpcClientFactory.getDefaultInstance(hostname, port);
			//Use the following method to create a thrift client (instead of the above line):
			// this.client = RpcClientFactory.getThriftInstance(hostname, port);
		}

		public void sendDataToFlume(String data){
			//Create flume event object that encapsulates user data.
			Event event = EventBuilder.withBody(data, Charset.forName("UTF-8"));

			try {
				//Send data.
				client.append(event);
			} catch (EventDeliveryException de) {
				//Re-establish connection.
				client.close();
				client = null;
				client = RpcClientFactory.getDefaultInstance(hostname, port);
			//Use the following method to create a thrift client (instead of the above line):
			// this.client = RpcClientFactory.getThriftInstance(hostname, port);
			}
		}

		public void cleanUp(){

			//Close connection.
			client.close();
		}
	}
}	
