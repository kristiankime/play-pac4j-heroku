package controllers

import org.pac4j.core.profile.CommonProfile
import org.pac4j.play.scala.ScalaController
import play.api._
import play.api.mvc._

object Application extends ScalaController[CommonProfile] {

  def index = Action { request =>
    val newSession = getOrCreateSessionId(request)
    val urlGoogle = getRedirectAction(request, newSession, "Google2Client", "/").getLocation()
    val profile = getUserProfile(request)
    Ok(views.html.index(profile, urlGoogle))
  }

  def protectedIndex = RequiresAuthentication("Google2Client") { profile =>
    Action { request =>
      Ok(views.html.protectedIndex(profile))
    }
  }

}