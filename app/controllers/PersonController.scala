package controllers

import javax.inject.{Inject, Singleton}
import model.Person
import play.api.libs.json._
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}


@Singleton
class PersonController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  var persons = Seq(
    Person("Abdelhadi", "Naimi", "abdelhadi@mail.com"),
    Person("John", "Lee", "johnlee@mail.com")
  )

  def getAll: Action[AnyContent] = Action {
    implicit request: Request[AnyContent] => {
      Ok(Json.toJson(persons))
    }
  }

  def create: Action[AnyContent] = Action {
    implicit request: Request[AnyContent] => {
      val person = Json.fromJson[Person](request.body.asJson.orNull).get
      persons = persons :+ person
      Ok(Json.toJson(person))
    }
  }
}
