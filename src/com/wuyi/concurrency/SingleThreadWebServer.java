package com.wuyi.concurrency;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by LENOVO on 2017/5/23.
 */
public class SingleThreadWebServer {
    public static void main(String[] args) throws IOException{
        ServerSocket socket=new ServerSocket(80);
        while (true){
            System.out.println("为真");
            Socket connection=socket.accept();
        }
    }
}
