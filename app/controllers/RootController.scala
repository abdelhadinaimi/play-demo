package controllers
import java.nio.file.Path

import controllers.submodule1.actions.{Logging, LoggingAction}
import javax.inject.Inject
import play.api.mvc._
import javax.inject.Singleton
import play.api.Configuration

@Singleton
class RootController @Inject()(loggerAction: LoggingAction, config: Configuration, cc: ControllerComponents) extends AbstractController(cc){
  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def explore(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.explore())
  }

  def tutorial(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.tutorial())
  }

  def hello(name: String): Logging[AnyContent] = Logging {
    loggerAction { implicit request: Request[AnyContent] =>
      Ok(views.html.hello(name))
    }
  }

}
