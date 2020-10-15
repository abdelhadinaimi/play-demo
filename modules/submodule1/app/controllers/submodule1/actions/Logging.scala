package controllers.submodule1.actions

import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

case class Logging[A](action: Action[A]) extends Action[A]{

  override def apply(request: Request[A]): Future[Result] = {
    println("Calling action from case class")
    action(request)
  }

  override def parser: BodyParser[A] = action.parser
  override def executionContext: ExecutionContext = action.executionContext

}

