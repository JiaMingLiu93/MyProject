package com.liu.parsexml;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.liu.tcp.TcpData;
import lombok.Data;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.util.CollectionUtils;
import org.w3c.dom.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by jam on 2017/2/8.
 */
@Data
public class XmlParse {

    public static final String HOST_IP = "hostIp";
    Map<String,String> metric;
    List<Map<String,String>> metrics = Lists.newArrayList();


    public static void main(String[] args) {
        //StringBuffer sb = TcpData.getStringBuffer();
        //new XmlParse().parseBySax(sb);
        //parseByDom();
        //parseByJdom();
        //parseBySax();
        TcpData tcpData = new TcpData();
        TcpData tcpData1 = new TcpData();
        System.out.println(TcpData.prefix);
    }

    private void parseBySax(StringBuffer stringBuffer){
        SAXParserFactory spf = SAXParserFactory.newInstance();
        System.out.println(stringBuffer.toString());
        InputStream is = new ByteArrayInputStream(stringBuffer.toString().getBytes(StandardCharsets.UTF_8));
        try {
            SAXParser saxParser = spf.newSAXParser();
            //FileInputStream fileInputStream = new FileInputStream("gmond.xml");
            saxParser.parse(is,new SaxHandler());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private Map normalize(List<Map<String,String>> metrics){
        ArrayList<HostMonitorInfo> hostInfos = Lists.newArrayList();
        ArrayList<MysqlMonitorInfo> mysqlInfos = Lists.newArrayList();
        ArrayList<RedisMonitorInfo> redisInfos = Lists.newArrayList();
        ArrayList<ZookeeperMonitorInfo> zookeeperInfos = Lists.newArrayList();
        ArrayList<ProcessSummaryInfo> processSummaryInfos = Lists.newArrayList();

        metrics.forEach(map->{
            hostInfos.add(HostMonitorInfo.getInstanceOfMap(map));
            mysqlInfos.add(MysqlMonitorInfo.getInstanceOfMap(map));
            redisInfos.add(RedisMonitorInfo.getInstanceOfMap(map));
            zookeeperInfos.add(ZookeeperMonitorInfo.getInstanceOfMap(map));
            processSummaryInfos.addAll(ProcessSummaryInfo.getInstanceOfMap(map));
        });

        HashMap<String, List> normalizedMap = Maps.newHashMap();
        normalizedMap.put("hostInfo",hostInfos);
        normalizedMap.put("mysqlInfo",mysqlInfos);
        normalizedMap.put("redisInfo",redisInfos);
        normalizedMap.put("zookeeperInfo",zookeeperInfos);
        normalizedMap.put("processSummaryInfo",processSummaryInfos);
        return normalizedMap;
    }

    class SaxHandler extends DefaultHandler{
        private String host;

        @Override
        public void endDocument() throws SAXException {
            metrics.add(metric);
        }
        @Override
        public void startElement (String uri, String localName,
                                  String qName, Attributes attributes)
                throws SAXException {
            if (qName.equals("CLUSTER")){
                for (int i=0;i<attributes.getLength();i++){
                    if (attributes.getQName(i).equals("LOCALTIME")){
                        Long timestamp = Long.valueOf(attributes.getValue(i));
                        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), TimeZone
                                .getDefault().toZoneId());
                        break;
                    }
                }
            }
            if (qName.equals("HOST")){
                String hostName = attributes.getValue(0);
                String hostIp = attributes.getValue(1);
                if (!hostName.equals(host)){
                    if (!CollectionUtils.isEmpty(metric)){
                        metrics.add(metric);
                    }
                    metric = Maps.newHashMap();
                }
                host = hostName;
                metric.put("hostName",host);
                metric.put(HOST_IP,hostIp);
            }
            if (qName.equals("METRIC")){
                String metricName = attributes.getValue(0);
                String value = attributes.getValue(1);
                metric.put(metricName,value);
            }

        }
    }

    private static void parseByJdom(){
        Ganglia ganglia = new Ganglia();
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            org.jdom.Document document = saxBuilder.build("gmond.xml");
            Element gangliaXml = document.getRootElement();
            String version = gangliaXml.getAttributeValue("VERSION");
            String source = gangliaXml.getAttributeValue("SOURCE");
            ganglia.setVersion(version);
            ganglia.setSource(source);

            List clusters = gangliaXml.getChildren();
            //System.out.println(children.size());
            ArrayList<Cluster> clusterList = Lists.newArrayList();
            ganglia.setClusters(clusterList);
            for (int i=0;i<clusters.size();i++){
                Cluster cluster = new Cluster();
                Element clusterXml = (Element) clusters.get(i);
                //List clusterAttributes = cluster.getAttributes();

                String name = clusterXml.getAttributeValue("NAME");
                String url = clusterXml.getAttributeValue("URL");
                String latlong = clusterXml.getAttributeValue("LATLONG");
                String owner = clusterXml.getAttributeValue("OWNER");
                String localtime = clusterXml.getAttributeValue("LOCALTIME");

                cluster.setName(name);
                cluster.setUrl(url);
                cluster.setLatLong(latlong);
                cluster.setOwner(owner);
                cluster.setLocaltime(localtime);
                clusterList.add(cluster);

                ArrayList<Host> hostList = Lists.newArrayList();
                cluster.setHosts(hostList);

                List hosts = clusterXml.getChildren();
                for (int j=0;j<hosts.size();j++){
                    Host host = new Host();
                    Element hostXml = (Element) hosts.get(j);
                    String gmondStarted = hostXml.getAttributeValue("GMOND_STARTED");
                    String name1 = hostXml.getAttributeValue("NAME");
                    String ip = hostXml.getAttributeValue("IP");
                    String tags = hostXml.getAttributeValue("TAGS");
                    String reported = hostXml.getAttributeValue("REPORTED");
                    String tn = hostXml.getAttributeValue("TN");
                    String tmax = hostXml.getAttributeValue("TMAX");
                    String dmax = hostXml.getAttributeValue("DMAX");
                    String location = hostXml.getAttributeValue("LOCATION");

                    host.setGmondStarted(gmondStarted);
                    host.setName(name1);
                    host.setIp(ip);
                    host.setTags(tags);
                    host.setReported(reported);
                    host.setTN(tn);
                    host.setTMax(tmax);
                    host.setDMax(dmax);
                    host.setLocation(location);

                    hostList.add(host);

                    ArrayList<Metric> metricList = Lists.newArrayList();
                    host.setMetrics(metricList);

                    List metrics = hostXml.getChildren();
                    for (int k=0;k<metrics.size();k++){
                        Metric metric = new Metric();
                        Element metricXml = (Element) metrics.get(k);
                        metricXml.getAttributes();
                        String slope = metricXml.getAttributeValue("SLOPE");
                        String dmax1 = metricXml.getAttributeValue("DMAX");
                        String tmax1 = metricXml.getAttributeValue("TMAX");
                        String tn1 = metricXml.getAttributeValue("TN");
                        String units = metricXml.getAttributeValue("UNITS");
                        String type = metricXml.getAttributeValue("TYPE");
                        String val = metricXml.getAttributeValue("VAL");
                        String name2 = metricXml.getAttributeValue("NAME");

                        metric.setSlope(slope);
                        metric.setDMax(dmax1);
                        metric.setTMax(tmax1);
                        metric.setTN(tn1);
                        metric.setUnits(units);
                        metric.setType(type);
                        metric.setVal(val);
                        metric.setName(name2);

                        metricList.add(metric);

                        ExtraData extraData = new ExtraData();
                        metric.setExtraData(extraData);

                        Element extraDataXml = metricXml.getChild("EXTRA_DATA");

                        ArrayList<ExtraElement> extraElementList = Lists.newArrayList();
                        extraData.setExtraElements(extraElementList);

                        List extraElements = extraDataXml.getChildren();
                        for (int l=0;l<extraElements.size();l++){
                            ExtraElement extraElement = new ExtraElement();
                            Element extraElementXml = (Element) extraElements.get(l);
                            String name3 = extraElementXml.getAttributeValue("NAME");
                            String val1 = extraElementXml.getAttributeValue("VAL");
                            extraElement.setName(name3);
                            extraElement.setVal(val1);
                            extraElementList.add(extraElement);
                        }

                    }

                }


            }

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(ganglia);
    }

    private static void parseByDom() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse("gmond.xml");
            NodeList childNodes = document.getChildNodes();
            System.out.println(childNodes.getLength());
            //printNode(childNodes);
            Node ganglia = childNodes.item(1);
            System.out.println(ganglia.getNodeName());
            NodeList childNodes5 = ganglia.getChildNodes();
            System.out.println(childNodes5.getLength());
            for (int i=0;i<childNodes5.getLength();i++){
                Node cluster = childNodes5.item(i);
                if (cluster instanceof Text){
                    continue;
                }
                System.out.println(cluster.getNodeName());
                NodeList childNodes1 = cluster.getChildNodes();
                System.out.println(childNodes1.getLength());
                for (int j=0;j<childNodes1.getLength();j++){
                    Node host = childNodes1.item(j);
                    if (host instanceof Text){
                        continue;
                    }
                    System.out.println(host.getNodeName());
                    NodeList childNodes2 = host.getChildNodes();
                    System.out.println(childNodes2.getLength());
                    for (int k=0;k<childNodes2.getLength();k++){
                        Node metric = childNodes2.item(k);
                        if (metric instanceof Text){
                            continue;
                        }
                        System.out.println(metric.getNodeName());
                        NodeList childNodes3 = metric.getChildNodes();
                        System.out.println(childNodes3.getLength());
                        for (int l=0;l<childNodes3.getLength();l++){
                            Node extraData = childNodes3.item(l);
                            if (extraData instanceof Text){
                                continue;
                            }
                            System.out.println(extraData.getNodeName());
                            NodeList childNodes4 = extraData.getChildNodes();
                            for (int m=0;m<childNodes4.getLength();m++){
                                Node element = childNodes4.item(m);
                                if (element instanceof Text){
                                    continue;
                                }
                                System.out.println(element.getNodeName());
                                NamedNodeMap attributes = element.getAttributes();
                                if (attributes == null){
                                    continue;
                                }
                                System.out.println(attributes.getNamedItem("NAME"));
                            }
                        }
                    }
                }

            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void printNode(NodeList nodeList){
        for (int i=0;i<nodeList.getLength();i++){
            NodeList childNodes1 = nodeList.item(i).getChildNodes();
            System.out.println(nodeList.item(i).getNodeName());
            System.out.println(childNodes1.getLength());
        }
    }
}
