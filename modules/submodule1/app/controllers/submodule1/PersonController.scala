package controllers.submodule1

import javax.inject.Inject
import play.api.libs.json.Json
import play.api.mvc._
import model._
import javax.inject.Singleton

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
