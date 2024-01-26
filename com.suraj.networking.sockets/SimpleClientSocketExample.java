package com.suraj.networking.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class SimpleClientSocketExample {
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("USAGE: SimpleClientSocketExample <Server> <path>");
			System.exit(0);
		}
    /*
    * Example of args => www.google.com /search?q=How+to+call+someone
    */
		String server = args[0];  //  www.google.com
		String path = args[1];  // /search?q=How+to+call+someone

		System.out.println("Loading Contents of URL: " + server);

		try {
			// Connect to the server
			Socket sc = new Socket(server, 80); // port = 80

			// Create the input/output Stream to read from and write to the server
			PrintStream out = new PrintStream(sc.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(sc.getInputStream()));

			// Sending GET Request in this Example
			// Follow the HTTP protocol of GET <path> HTTP/1.0 followed by an empty line
			out.println("GET " + path + " HTTP/1.0");
			out.println();

			// Read data from server until we finish reading the document
			int Count = 0;
			String line = in.readLine();

			while (line != null) {
				++Count;
				System.out.println(line);
				line = in.readLine();
				if (Count == 10) {
					Count = 0;
				}
			}

			// Once Read/Write done close all open items
			in.close();
			out.close();
			sc.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
