package com.example.consumingwebservice.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.jaxb.JAXBContextFactory;

@Configuration
public class SoapClientConfiguration {

	private static final JAXBContextFactory jaxbFactory = new JAXBContextFactory.Builder()
			.withMarshallerJAXBEncoding("UTF-8")
			// .withMarshallerSchemaLocation("http://apihost http://apihost/schema.xsd")
			.build();

	@Bean
	public Encoder feignEncoder() {
		return new SOAPEncoder(jaxbFactory);
	}

	@Bean
	public Decoder feignDecoder() {
		return new SOAPDecoder(jaxbFactory);
	}

	@Bean
	public ErrorDecoder feignErrorDecoder() {
		return new SOAPErrorDecoder();
	}
	
	
}
