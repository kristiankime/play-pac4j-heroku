package controllers

import org.pac4j.core.exception._
import org.pac4j.oauth.client.exception.OAuthCredentialsException
//import play.api.Application
import scala.concurrent.Future
import play.api._
import play.api.Play.current
import play.api.mvc._
import play.api.mvc.Results._

import org.pac4j.play._
import org.pac4j.core.client._
import org.pac4j.core.context._
import org.pac4j.oauth.client._
//import org.pac4j.cas.client._
//import org.pac4j.saml.client._
//import org.pac4j.http.client._
//import org.pac4j.http.profile._
//import org.pac4j.oidc.client._
//import org.pac4j.http.credentials._

object Global extends GlobalSettings {


  override def onError(request: RequestHeader, t: Throwable) = {
    val page = t.getCause match  {
      case e: OAuthCredentialsException => views.html.error500.render("Authentication Error",e.getMessage())
      case e: CredentialsException => views.html.error500.render("Credential Exception",e.printStackTrace().toString)
      case e: HttpCommunicationException => views.html.error500.render("HttpCommunicationException",e.printStackTrace().toString)
      //      case e: CommunicationException => views.html.error500.render("CommunicationException",e.printStackTrace().toString)
      case e: TechnicalException => views.html.error500.render("Technical Exception",e.printStackTrace().toString)
      case e: RequiresHttpAction => views.html.error500.render("RequiresHttpAction",e.printStackTrace().toString)
      case e: Exception => views.html.error500.render("Exception", e.getMessage())
    }
    Future.successful(InternalServerError(page))
  }

  override def onStart(app: Application) {
    val baseUrl = Play.application.configuration.getString("application.baseUrl") match { case Some(url) => url; case None => throw new IllegalStateException("application.baseUrl was not set") }

    val key: String = Play.application.configuration.getString("pac4j.googleKey") match { case Some(url) => url; case None => throw new IllegalStateException("pac4j.googleKey was not set") }
    val secret: String = Play.application.configuration.getString("pac4j.googleSecret") match { case Some(url) => url; case None => throw new IllegalStateException("pac4j.googleSecret was not set") }
    val googleClient = new Google2Client(key, secret)

    val clients = new Clients(baseUrl + "/callback", googleClient)
    Config.setClients(clients)
  }

}
