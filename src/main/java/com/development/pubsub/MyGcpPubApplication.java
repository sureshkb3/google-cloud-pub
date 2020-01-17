package com.development.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
/**
 * @author suresha
 * Created on 2020-01-15
 */
@SpringBootApplication
public class MyGcpPubApplication {

	@Autowired
	private Environment environment;
	public static void main(String[] args) {
		SpringApplication.run(MyGcpPubApplication.class, args);
	}

	@Bean
	@ServiceActivator(inputChannel = "myOutputChannel")
	public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
		return new PubSubMessageHandler(pubsubTemplate, environment.getProperty("spring.cloud.pubsub.topic-name"));
	}

	@MessagingGateway(defaultRequestChannel = "myOutputChannel")
	public interface pubsubOutboundGateway {
		void sendToPubsub(String text);
	}

}
