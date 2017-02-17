package com.liu.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/2/13.
 */
@Component
public class XmlParser implements ParserService<Map<String,String>> {

    public static final String HOST_IP = "hostIp";
    Map<String,String> metric;
    List<Map<String,String>> metrics = Lists.newArrayList();
    private String timestamp;
    public Collection<Map<String, String>> parse(InputStream inputStream) {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = spf.newSAXParser();
            //FileInputStream fileInputStream = new FileInputStream("gmond.xml");
            saxParser.parse(inputStream,new SaxHandler());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return metrics;
    }

    class SaxHandler extends DefaultHandler {
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
                        timestamp = attributes.getValue(i);
                        //LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), TimeZone
                        //        .getDefault().toZoneId());
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
                metric.put("timestamp",timestamp);
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
}
