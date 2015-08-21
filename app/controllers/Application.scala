package controllers

import org.pac4j.core.profile.CommonProfile
import org.pac4j.play.scala.Security
import play.api._
import play.api.mvc._

class Application extends Controller with Security[CommonProfile] {

  def index = Action { request =>
    val newSession = getOrCreateSessionId(request)
    val urlGoogle = getRedirectAction(request, newSession, "Google2Client", "/").getLocation()
    val profile = getUserProfile(request)
    Ok(views.html.index(profile, urlGoogle)).withSession(newSession)
  }

  def protectedIndex = RequiresAuthentication("Google2Client") { profile =>
    Action { request =>
      profile.getId
      profile.getTypedId
      Ok(views.html.protectedIndex(profile))
    }
  }

}

object  Application {
  val textOnIndex = "This should be on index"
  val textOnProtected = "This should be on protect"
}