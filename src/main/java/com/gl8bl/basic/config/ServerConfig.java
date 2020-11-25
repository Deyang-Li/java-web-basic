package com.gl8bl.basic.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AbstractAjpProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {

  @Value("${ajp.port}")
  int ajpPort;

  @Value("${ajp.enabled}")
  boolean ajpEnabled;

  @Value("${ajp.remoteauthentication}")
  boolean remoteAuthentication;

  @Bean
  public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer() {
    return server -> {
      if (server instanceof TomcatServletWebServerFactory) {
        ((TomcatServletWebServerFactory) server).addAdditionalTomcatConnectors(redirectConnector());
      }
    };
  }

  private Connector redirectConnector() {
    Connector connector = new Connector("AJP/1.3");
    connector.setScheme("http");
    connector.setPort(ajpPort);
    connector.setSecure(false);
    connector.setAllowTrace(false);
    ((AbstractAjpProtocol<?>) connector.getProtocolHandler())
        .setSecretRequired(remoteAuthentication);
    return connector;
  }
}
