package com.test.demo.http;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnRoutePNames;

import org.apache.http.impl.client.ContentEncodingHttpClient;
import org.apache.http.impl.client.DecompressingHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.EntityUtils;



public class HttpClientDemo {

	private static String vote() throws IOException{
		String urlStr = "http://jkbb.zgwhfe.com/Tools/VotingUpload.ashx";
		
		String myip = "113.116.61.255";

//		代理服务器IP
		String proxyHost = "59.172.208.186";
//		代理服务器端口
		int port = 8080;
		int mycheck = 4929;
			DefaultHttpClient client = new DefaultHttpClient();
			try {
			HttpHost proxy = new HttpHost(proxyHost, port, "http");	
			client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
			
			HttpHost targetHost = new HttpHost("jkbb.zgwhfe.com", 80, "http");
			HttpGet request = new HttpGet("/Tools/VotingUpload.ashx?rnd=" + String.valueOf(Math.random()) + "&myid=" + 2186 + "&myip=" + myip + "&mycheck=" + mycheck);
	//		HttpPost request = new HttpPost(urlStr);
			request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; rv:19.0) Gecko/20100101 Firefox/19.0");
			request.setHeader("Accept", "application/json, text/javascript, */*");
			request.setHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
			request.setHeader("Accept-Encoding", "gzip, deflate");
			request.setHeader("X-Requested-With", "XMLHttpRequest");
			request.setHeader("Referer", "http://jkbb.zgwhfe.com/BabyDetail.aspx?BabyId=2186");
			request.setHeader("Cookie", "ASP.NET_SessionId=jejop2aljft0meyzac3a4an5");
			request.setHeader("Connection", "keep-alive");
			request.setHeader("Pragma", "no-cache");
			request.setHeader("Cache-Control", "no-cache");
			
	//		request.setHeader("X-Forwarded-For", ip);
	//		request.setHeader("x-forwarded-for",ip);
	//		request.setHeader("Proxy-Client-IP",ip);
	//		request.setHeader("WL-Proxy-Client-IP",ip);		
			
	//		List<NameValuePair> params = new ArrayList<NameValuePair>();	//		
	//		params.add(new BasicNameValuePair("rnd", String.valueOf(Math.random())));
	//		params.add(new BasicNameValuePair("myid", "2186"));
	//		params.add(new BasicNameValuePair("myip", proxyHost));
	//		params.add(new BasicNameValuePair("mycheck", ""));
	//		HttpEntity entity = new UrlEncodedFormEntity(params);
	//		request.setEntity(entity);
		
			System.out.println(request.getRequestLine());
			System.out.println(request.getURI());
			HttpResponse response = client.execute(targetHost,request);
			String responseHtml = EntityUtils.toString(response.getEntity());
			return responseHtml;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            client.getConnectionManager().shutdown();
        }
		return "";		
	}
	/**
	 * @param args
	 * @throws HttpException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		System.out.println(HttpClientDemo.vote());
	}

}
