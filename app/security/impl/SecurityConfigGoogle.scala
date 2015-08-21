package security.impl

import javax.inject.{Inject, Singleton}

import org.pac4j.core.client.Clients
import org.pac4j.core.context.BaseConfig
import org.pac4j.oauth.client.Google2Client
import org.pac4j.oauth.client.Google2Client.Google2Scope
import org.pac4j.play.Config
import play.api.Configuration
import security.SecurityConfig

/**
 * Implementation of {@link security.SecurityConfig} that will add
 * all {@link org.pac4j.core.client.Client}s to the config.
 */
@Singleton
class SecurityConfigGoogle @Inject()(configuration: Configuration) extends SecurityConfig {

  BaseConfig.setErrorPage401(views.html.error401.render().toString())
  BaseConfig.setErrorPage403(views.html.error403.render().toString())

  val baseUrl = configuration.getString("baseUrl").get

  val key: String = configuration.getString("pac4j.googleKey") match { case Some(url) => url; case None => throw new IllegalStateException("pac4j.googleKey was not set") }
  val secret: String = configuration.getString("pac4j.googleSecret") match { case Some(url) => url; case None => throw new IllegalStateException("pac4j.googleSecret was not set") }
  val googleClient = new Google2Client(key, secret)
  googleClient.setScope(Google2Scope.EMAIL_AND_PROFILE)

  val clients = new Clients(baseUrl + "/callback", googleClient)

  Config.setClients(clients)
  // for test purposes : profile timeout = 60 seconds
  // Config.setProfileTimeout(60)

}
