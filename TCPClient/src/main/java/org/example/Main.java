package org.example;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws  Exception{

        Socket socket = new Socket("localhost", 6789);
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        outputStream.write("Salam Server ,necesen?".getBytes());
        socket.close();
    }
}