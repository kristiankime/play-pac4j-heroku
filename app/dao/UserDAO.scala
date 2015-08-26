package dao

import akka.actor.Status.Success
import models.table.UsersTable
import org.pac4j.core.profile.CommonProfile

import scala.Option
import scala.concurrent.{ExecutionContext, Future}

import javax.inject.Inject
import models.User
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile

class UserDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  private val Users = TableQuery[UsersTable]

  private val NoId = -1;

  def all(): Future[Seq[User]] = db.run(Users.result)

  def insert(user: User): Future[Unit] = db.run(Users += user).map { _ => () }

  def findById(id: Long) : Future[Option[User]] = db.run(Users.filter(_.id === id).result.headOption)

  def findByProfile(profile: CommonProfile) : Future[Option[User]] = db.run(Users.filter(_.profileId === profile.getTypedId).result.headOption)

  def create(user: User) : Future[User] = {
    val action = (Users returning Users.map(_.id)) += user
    db.run(action).map( id => user.copy(userId = id))
  }
  
  def getOrCreate(profile: CommonProfile): Future[User] =
    findByProfile(profile) flatMap ( _ match {
        case Some(user) => Future.successful(user)
        case None => create(User(NoId, profile.getTypedId, profile.getDisplayName, profile.getEmail, true, false, true))
      })


//  private class UsersTable(tag: Tag) extends Table[User](tag, "application_users") {
//
//    def profileId = column[String]("profile_id", O.PrimaryKey)
//    def name = column[String]("name")
//    def email = column[String]("email")
//    def color = column[String]("color")
//
//    def * = (profileId, name, email, color) <> (User.tupled, User.unapply _)
//  }
}