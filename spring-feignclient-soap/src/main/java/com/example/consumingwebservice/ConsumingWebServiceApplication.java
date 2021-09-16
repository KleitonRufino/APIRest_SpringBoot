
package com.example.consumingwebservice;

import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.example.consumingwebservice.feign.ManagerProxy;
import com.example.consumingwebservice.wsdl.ConsultarAssinanteResponse;

@EnableFeignClients
@SpringBootApplication
public class ConsumingWebServiceApplication {

	@Autowired
	private ManagerProxy managerProxy;
	
	public static void main(String[] args) throws JAXBException {
		SpringApplication.run(ConsumingWebServiceApplication.class, args);
		
//		String payload = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"> <soapenv:Header> <mtrace:meta-trace xmlns:mtrace=\""> <mtrace:indicador-rastreabilidade>false</mtrace:indicador-rastreabilidade> </mtrace:meta-trace> </soapenv:Header> <soapenv:Body> </soapenv:Body> </soapenv:Envelope>";
//		HttpResult result;
//		try {
//			result = doHttpCall("uri", payload, "user", "pwd");
//			System.out.println(result.getResponse());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}


	@Bean
	CommandLineRunner lookup() {
		return args -> {
			try {
				ConsultarAssinanteResponse consultarAssinanteResponse = this.managerProxy.consultarAssinante(Long.valueOf(925419947));
				System.out.println(consultarAssinanteResponse);
			} catch (Throwable e) {
				System.out.println("ERRO");
			}
		};
	}

	
	public static HttpResult doHttpCall(String uri, String payload, String user, String password) throws IOException {
		HttpResult httpResult;
		HttpRequestBase request = createPost(uri, payload, user, password);

		try (CloseableHttpClient httpClient = HttpClientBuilder.create()
				.setConnectionTimeToLive(10000, TimeUnit.MILLISECONDS).build();
				CloseableHttpResponse response = httpClient.execute(request)) {

			httpResult = new HttpResult(response, HttpType.SOAP);
		}
		return httpResult;
	}

	public static HttpRequestBase createPost(String uri, String payload, String user, String password) {
		HttpPost post = new HttpPost("url_base" + uri);
		post.setEntity(getBody(payload));
		addHeaders(post, user, password);
		return post;
	}

	public static HttpEntity getBody(String payload) {
		StringEntity entity = new StringEntity(payload, "UTF-8");
		entity.setChunked(true);
		return entity;
	}

	public static void addHeaders(HttpRequestBase httpRequestBase, String user, String password) {
		httpRequestBase.addHeader("Content-Type", "text/xml; charset=utf-8");
		httpRequestBase.addHeader("Accept", "text/xml; charset=utf-8");
		String auth = Base64.getEncoder().encodeToString((user + ":" + password).getBytes());
		httpRequestBase.addHeader("Authorization", "Basic " + auth);
	}

}
