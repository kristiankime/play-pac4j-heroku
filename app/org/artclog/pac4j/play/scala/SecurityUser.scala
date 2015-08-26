package org.artclog.pac4j.play.scala


import dao.UserDAO
import org.pac4j.core.client.RedirectAction
import org.pac4j.core.context.{BaseConfig, Pac4jConstants}
import org.pac4j.core.exception.{RequiresHttpAction, TechnicalException}
import org.pac4j.core.profile.CommonProfile
import org.pac4j.play.scala.Security
import org.slf4j.LoggerFactory
import play.api.db.slick._
import play.api.mvc.{Result, AnyContent, Action, BodyParser}

import models.User

import play.api.mvc.Action

import scala.concurrent.Future

trait SecurityUser[P<:CommonProfile] extends Security[P] {
  val userDAO: UserDAO

  private val logger = LoggerFactory.getLogger("org.pac4j.play.scala.Security")

//  protected def RequiresAuthenticationUser[A](clientName: String, targetUrl: String, parser: BodyParser[A], isAjax: Boolean)(action: User => Action[A]) = Action.async(parser) { request =>
//    logger.debug("Entering RequiresAuthenticationUser")
//    var newSession = getOrCreateSessionId(request)
//    val sessionId = newSession.get(Pac4jConstants.SESSION_ID).get
//    logger.debug("sessionId : {}", sessionId)
//    val profile = getUserProfile(request)
//    logger.debug("profile : {}", profile)
//
//    if (profile == null) {
//      try {
//        val redirectAction = getRedirectAction(request, newSession, clientName, targetUrl, true, isAjax)
//        logger.debug("redirectAction : {}", redirectAction)
//        redirectAction.getType() match {
//          case RedirectAction.RedirectType.REDIRECT => Future.successful(Redirect(redirectAction.getLocation()).withSession(newSession))
//          case RedirectAction.RedirectType.SUCCESS => Future.successful(Ok(redirectAction.getContent()).withSession(newSession).as(HTML))
//          case _ => throw new TechnicalException("Unexpected RedirectAction : " + redirectAction.getType)
//        }
//      } catch {
//        case ex: RequiresHttpAction => {
//          val code = ex.getCode()
//          if (code == 401) {
//            Future.successful(Unauthorized(BaseConfig.getErrorPage401()).as(HTML))
//          } else if (code == 403) {
//            Future.successful(Forbidden(BaseConfig.getErrorPage403()).as(HTML))
//          } else {
//            throw new TechnicalException("Unexpected HTTP code : " + code)
//          }
//        }
//      }
//    } else {
//      userDAO.getOrCreate(profile).flatMap(u => action(u)(request) )
//    }
//  }

}
