package com.liu.tcp;

import com.google.common.base.Strings;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 * Created by Jam on 2017/2/28.
 */
@Component
public class TcpTest implements InitializingBean{
    @Autowired
    TcpSocket socket;

    @Value("${tcp_service_host:192.168.107.110}")
    private String host;
    @Value("${tcp_service_port:8649}")
    private int port;


    private BufferedReader bufferedReader;

    @Scheduled(cron = "0/35 * * * * ?")
    public void test() throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        //Socket socket1 = new Socket(host,port);
        //InputStream inputStream = socket1.getInputStream();
        //Optional<InputStream> inputStreamOptional = socket.getInputStream();
        //Optional<InputStream> inputStreamOptional = socket.getInputStream();
        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamOptional.get()));
        Optional<InputStream> inputStreamOptional = socket.getInputStream();
        bufferedReader = new BufferedReader(new InputStreamReader(inputStreamOptional.get()));
        String line;
        try {
            line = bufferedReader.readLine();
            while (line != null){
                stringBuffer.append(line);
                line = bufferedReader.readLine();
            }
            if (Strings.isNullOrEmpty(stringBuffer.toString())){
                System.out.println("stringBuffer is null -===========");
            }else {
                System.out.println("stringBuffer is not null==========");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Optional<InputStream> inputStreamOptional = socket.getInputStream();
        bufferedReader = new BufferedReader(new InputStreamReader(inputStreamOptional.get()));
    }
}
