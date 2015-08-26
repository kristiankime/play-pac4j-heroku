package models

case class User(userId: Long, profileId: String, name: String, email: String, emailGameUpdates: Boolean, consented: Boolean, allowAutoMatch: Boolean)
