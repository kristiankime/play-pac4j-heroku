package security.impl

import javax.inject.{Inject, Singleton}

import org.pac4j.core.client.Clients
import org.pac4j.http.client.BasicAuthClient
import org.pac4j.http.credentials.SimpleTestUsernamePasswordAuthenticator
import org.pac4j.http.profile.UsernameProfileCreator
import org.pac4j.play.Config
import play.api.{Configuration, Logger}
import security.SecurityConfig

@Singleton
class DummyBasicAuthSecurityConfig @Inject() (val configuration: Configuration) extends SecurityConfig {

  val logger = Logger("DummyBasicAuthSecurityConfig")

  logger.info("Configuring basic authentication security")

  val basicAuthClient = new BasicAuthClient(new SimpleTestUsernamePasswordAuthenticator(), new UsernameProfileCreator())
  basicAuthClient.setName("BasicAuthClient")

  val baseUrl = configuration.getString("baseUrl").get

  val clients = new Clients(baseUrl + "/callback", basicAuthClient)
  Config.setClients(clients)

}