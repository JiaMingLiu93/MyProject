package com.liu.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Jam on 2017/2/13.
 */
@Service
public class TcpService {

    @Value("${tcp_service_host:192.168.107.110}")
    private String host;
    @Value("${tcp_service_port:8649}")
    private int port;

    public StringBuffer getStringBuffer(){
        StringBuffer stringBuffer = new StringBuffer();
        try {
            Socket socket = new Socket(host, port);
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();
            while (line != null){
                stringBuffer.append(line);
                line = bufferedReader.readLine();
            }
            socket.close();
            return stringBuffer;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
