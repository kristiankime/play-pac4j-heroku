package dao

import scala.concurrent.Future

import javax.inject.Inject
import models.User
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile

class UserDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  private val Users = TableQuery[UsersTable]

  def all(): Future[Seq[User]] = db.run(Users.result)

  def insert(cat: User): Future[Unit] = db.run(Users += cat).map { _ => () }

  private class UsersTable(tag: Tag) extends Table[User](tag, "application_users") {

    def profileId = column[String]("profile_id", O.PrimaryKey)
    def name = column[String]("name")
    def email = column[String]("email")
    def color = column[String]("color")

    def * = (profileId, name, email, color) <> (User.tupled, User.unapply _)
  }
}