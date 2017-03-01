package com.liu.tcp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Optional;

/**
 * Created by Jam on 2017/2/27.
 */
@Component
@Slf4j
public class TcpSocket {
    @Value("${tcp_service_host:192.168.107.110}")
    private String host;
    @Value("${tcp_service_port:8649}")
    private int port;

    private Socket socket;
    private InputStream inputStream;

    @PostConstruct
    public void init() {
        try {

            socket = new Socket(host,port);
            socket.setKeepAlive(true);
        } catch (IOException e) {
            log.error("Dingplus:[]maintenance[]TcpSocket[]failed to init,the ip is: "+host+",the port is : "+port+" cause of [{}]",e);
        }
    }

    public boolean isConnect(){
        return socket.isConnected();
    }

    public Optional<InputStream> getInputStream(){
        InputStream in = null;
        try {
            System.out.println("the socket is keep alive?"+socket.getKeepAlive());
            in = socket.getInputStream();
        } catch (IOException e) {
            log.error("Dingplus:[]maintenance[]TcpSocket[]failed to getInputStream,the ip is: "+host+",the port is : "+port+" cause of [{}]",e);
        }
        return Optional.ofNullable(in);
    }

    @PreDestroy
    public void close(){
        try {
            socket.close();
        } catch (IOException e) {
            log.error("Dingplus:[]maintenance[]TcpSocket[]failed to close,the ip is: "+host+",the port is : "+port+" cause of [{}]",e);
        }
    }
}
