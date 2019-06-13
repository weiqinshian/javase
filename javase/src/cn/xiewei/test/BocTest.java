package cn.xiewei.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class BocTest {
    public static void main(String[] args) throws Exception {
        String xmlReq ="<?xml version=\"1.0\" encoding=\"gb2312\"?><bocb2e version=\"110\" security=\"true\" locale=\"zh_CN\"><head><termid>E127000000001</termid><trnid>251031665509</trnid><custid>12345678</custid><cusopr>BOC</cusopr><trncod>b2e0001</trncod></head><trans><trn-b2e0001-rq><b2e0001-rq><custdt>20251031141453</custdt><oprpwd>rRaS4wMq</oprpwd><ceitinfo>MIIE9AYJKoZIhvcNA</ceitinfo><usbkey>b8888888</usbkey></b2e0001-rq></trn-b2e0001-rq></trans></bocb2e>";
        HashMap<String,String> httpConnParams = new HashMap<String,String>();
        setValuesToHttpConnParams(httpConnParams);
        String resXml = "";
        resXml =sendXMLByHttp(xmlReq,httpConnParams);
        System.out.println(resXml);
       }
    
    public static  void setValuesToHttpConnParams(HashMap<String,String> httpConnParams){
        
        httpConnParams.put("ip", "10.2.124.23");
        httpConnParams.put("port", "9080");
        httpConnParams.put("uri", "/B2EC/E2BServlet");
        httpConnParams.put("contentType", "application/xml");
        httpConnParams.put("charset", "GBK");
      //    httpConnParams.put(TransactionUtil.REQUEST_CHARSET, "UTF-8");
    }
    
    public  static String sendXMLByHttp(String sendData,HashMap<String,String> httpConnParams)throws Exception {
        String connectException = null;
        String http = httpConnParams.get("http");
        if(http == null ||"".equals(http)){
            http ="http";
        }
        StringBuffer responsorUrl = new StringBuffer();
        responsorUrl.append(http).append("://").append(httpConnParams.get("ip")).append(":").append(httpConnParams.get("port"));
        if(httpConnParams.get("uri") != null){
            responsorUrl.append(httpConnParams.get("uri"));
        }
        HttpURLConnection  urlConnection=null;
        String iv_return_data ="";
        try {
            java.net.URL aURL = new URL(responsorUrl.toString());

            urlConnection= (java.net.HttpURLConnection) aURL.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);            
            if(httpConnParams.get("userAgent") != null && !"".equals(httpConnParams.get("userAgent"))){
                urlConnection.setRequestProperty("User-Agent",httpConnParams.get("userAgent"));
            }
            if(httpConnParams.get("contentType") != null && !"".equals(httpConnParams.get("contentType"))){
                urlConnection.setRequestProperty("Content-type", httpConnParams.get("contentType"));
            }
            if(httpConnParams.get("ContentLength") != null && !"".equals(httpConnParams.get("ContentLength"))){
                urlConnection.setRequestProperty("Content-Length", httpConnParams.get("ContentLength"));
            }
            urlConnection.connect();
            if (sendData != null && sendData.trim().length() != 0) {
                String encoding = httpConnParams.get("charset");
                if(encoding != null && !"".equals(encoding)){
                    urlConnection.getOutputStream().write(sendData.getBytes(encoding));
                }else{
                    urlConnection.getOutputStream().write(sendData.getBytes());
                }
                
            }
            InputStreamReader in_stream = null;
            in_stream = new InputStreamReader(urlConnection.getInputStream(),"ISO8859-1");
            BufferedReader in_buffer = new BufferedReader(in_stream);
            String line;
            StringBuffer i_out = new StringBuffer();
            while((line = in_buffer.readLine())!= null)
            {
               i_out.append(line);
            }
            in_buffer.close();
            in_stream.close();
            urlConnection.disconnect();
            iv_return_data = new String(i_out.toString().getBytes("ISO8859-1"),"UTF-8");
            urlConnection.disconnect();

        } catch(ConnectException  e){
            e.printStackTrace();
            connectException =  e.getMessage();
            if (urlConnection != null) {
                try {
                    urlConnection.disconnect();
                    urlConnection = null;
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        }
        catch (Throwable e) {
            e.printStackTrace();
            if (urlConnection != null) {
                try {
                    urlConnection.disconnect();
                    urlConnection = null;
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        } finally {
            if (urlConnection != null) {
                try {
                    urlConnection.disconnect();
                    urlConnection = null;
                } catch (Exception ee) {
                }
            }
        }
        if(connectException!=null && !"".equals(connectException)){
            throw new ConnectException("连接前置机失败，请联系技术支持人员检查系统");
        }
        return iv_return_data;
    }
}
