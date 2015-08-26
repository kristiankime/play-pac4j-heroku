package models.table

import models.User

//import slick.driver.JdbcDriver.api._
import slick.driver.PostgresDriver.api._
import slick.lifted._

class UsersTable(tag: Tag) extends Table[User](tag, "application_users") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def profileId = column[String]("profile_id")
  def name = column[String]("name")
  def email = column[String]("email")
  def emailGameUpdates = column[Boolean]("email_game_updates")
  def consented = column[Boolean]("consented")
  def allowAutoMatch = column[Boolean]("allow_auto_match")

  def * = (id, profileId, name, email, emailGameUpdates, consented, allowAutoMatch) <> (User.tupled, User.unapply _)

  def profileIdIndex = index("application_users_profile_id_idx", profileId)
  def nameIndex = index("application_users_name_idx", name)
}
