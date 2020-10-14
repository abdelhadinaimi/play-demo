package model

import play.api.libs.functional.syntax.{toApplicativeOps, toFunctionalBuilderOps}
import play.api.libs.json.Reads._
import play.api.libs.json.{Json, Reads, Writes, __}

case class Person(firstname: String, lastname: String, email: String/*, gender: Genders.Value*/)

object Person {
  implicit val writes: Writes[Person] = (o: Person) => Json.obj(
    ("first_name", o.firstname), // same as the others
    "last_name" -> o.lastname,
    "email" -> o.email
  )

  implicit val reads : Reads[Person] = (
    (__ \ "first_name").read[String](maxLength[String](63) ~> minLength[String](2)) ~
      (__ \ "last_name").read[String] ~
      (__ \ "email").read[String](maxLength[String](256) ~> minLength[String](2) ~> email)
    )(Person.apply _) // apply is the constructor function
}

// ~ and
// ~> keepAnd, <~ andKeep