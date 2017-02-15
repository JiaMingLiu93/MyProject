package com.liu.tcp;

import org.apache.commons.net.chargen.CharGenTCPClient;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

/**
 * Created by jam on 2017/2/13.
 */
public class TcpData {
    public static String prefix;
    static {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("configure.properties");
            properties.load(fileInputStream);
            prefix = properties.getProperty("process_summary_info_prefix");
            String[] prefixs = prefix.split(",");
            System.out.println(prefix);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //InputStream inputStreamFromTcpClient = getInputStreamFromTcpClient();
        //try {
        //    assert inputStreamFromTcpClient != null;
        //    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamFromTcpClient));
        //    String line = bufferedReader.readLine();
        //    while (line!=null){
        //        System.out.println(line);
        //        line = bufferedReader.readLine();
        //    }
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}

        //StringBuffer stringBuffer = getStringBuffer();
        //
        //System.out.println(stringBuffer.toString());
        //InputStream is = new ByteArrayInputStream(stringBuffer.toString().getBytes(StandardCharsets.UTF_8));

        //readProperty();
        System.out.println(Float.valueOf("1.2"));

    }

    private static InputStream getInputStreamFromTcpClient() {
        CharGenTCPClient client = new CharGenTCPClient();
        try {
            client.connect("192.168.107.110",8649);
            InputStream inputStream = client.getInputStream();
            client.disconnect();
            return inputStream;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static StringBuffer getStringBuffer(){
        StringBuffer stringBuffer = new StringBuffer();
        try {
            Socket socket = new Socket("192.168.107.110", 8649);
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

    public static void readProperty(){
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("configure.properties");
            properties.load(fileInputStream);
            String prefix = properties.getProperty("process_summary_info_prefix");
            String[] prefixs = prefix.split(",");
            System.out.println(prefix);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
