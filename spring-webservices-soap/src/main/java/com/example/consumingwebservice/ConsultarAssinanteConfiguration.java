
package com.example.consumingwebservice;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.Marshaller;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

@Configuration
public class ConsultarAssinanteConfiguration {

	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPath("com.example.consumingwebservice.wsdl");
		Map<String, Object> properties = new HashMap<>();
		properties.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		properties.put(Marshaller.JAXB_SCHEMA_LOCATION, "");
		properties.put("com.sun.xml.bind.namespacePrefixMapper", new MyNamespaceMapper());

		jaxb2Marshaller.setMarshallerProperties(properties);
		return jaxb2Marshaller;
	}

	public Jaxb2Marshaller unmarshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPath("com.example.consumingwebservice.wsdl");
		Map<String, Object> properties = new HashMap<>();
		properties.put("com.sun.xml.bind.namespacePrefixMapper", new MyNamespaceMapper());
		jaxb2Marshaller.setMarshallerProperties(properties);
		return jaxb2Marshaller;
	}

	@Bean
	public ConsultarAssinanteClient consultarAssinanteClient() {
		ConsultarAssinanteClient client = new ConsultarAssinanteClient();
		client.setDefaultUri("url");
		client.setMarshaller(marshaller());
		client.setUnmarshaller(unmarshaller());
		client.setMessageSender(httpComponentsMessageSender());
		return client;
	}
	
	@Bean
	public HttpComponentsMessageSender httpComponentsMessageSender() {
		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
		httpComponentsMessageSender.setCredentials(usernamePasswordCredentials());
		return httpComponentsMessageSender;
	}

	@Bean
	public UsernamePasswordCredentials usernamePasswordCredentials() {
		return new UsernamePasswordCredentials("user", "password");
	}

}
