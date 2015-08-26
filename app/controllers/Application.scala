package controllers

import dao.UserDAO
import models.User
import org.pac4j.core.profile.CommonProfile
import org.pac4j.play.scala.Security
import play.api._
import play.api.db.slick.DatabaseConfigProvider
import play.api.mvc._
import javax.inject.Inject

import security.SecurityUser
import slick.driver.JdbcProfile

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class Application @Inject()(val userDAO: UserDAO) extends Controller with SecurityUser[CommonProfile] {

  def index = Action { request =>
    val newSession = getOrCreateSessionId(request)
    val urlGoogle = getRedirectAction(request, newSession, "Google2Client", "/").getLocation()
    val profileOp = Option(getUserProfile(request))
    Ok(views.html.index(profileOp, urlGoogle)).withSession(newSession)
  }

//  def protectedIndex = RequiresAuthentication("Google2Client") { profile =>
//      Action.async { request =>
//        userDAO.getOrCreate(profile).map(user => Ok(views.html.userIndex(user)))
//      }
//  }

  def protectedIndex = RequiresAuthenticationUser("Google2Client") { user =>
     Ok(views.html.userIndex(user))
  }

}

object Application {
  val textOnIndex = "This should be on index"
  val textOnProtected = "This should be on protected"
}