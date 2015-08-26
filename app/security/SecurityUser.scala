package security

import dao.UserDAO
import models.User
import org.pac4j.core.profile.CommonProfile
import org.pac4j.play.scala.Security
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.{Action, Result}

trait SecurityUser[P<:CommonProfile] extends Security[P] {
  val userDAO: UserDAO

  def RequiresAuthenticationUser(clientName: String, targetUrl: String = "", isAjax: Boolean = false)(f : (User) => Result) = RequiresAuthentication("Google2Client") { profile =>
    Action.async { request => userDAO.getOrCreate(profile).map( f ) }
  }

}
