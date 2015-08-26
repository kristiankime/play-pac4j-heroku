# --- !Ups

create table "application_users" (
  "id" SERIAL NOT NULL PRIMARY KEY,
  "profile_id" VARCHAR NOT NULL,
  "name" VARCHAR NOT NULL,
  "email" VARCHAR NOT NULL,
  "email_game_updates" BOOLEAN NOT NULL,
  "consented" BOOLEAN NOT NULL,
  "allow_auto_match" BOOLEAN NOT NULL
);
create index "application_users_name_idx" on "application_users" ("name");
create index "application_users_profile_id_idx" on "application_users" ("profile_id");

# --- !Downs

drop table "application_users";
