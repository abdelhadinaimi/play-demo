package controllers

import java.nio.file.Path

import actions.{Logging, LoggingAction}
import akka.stream.scaladsl.{FileIO, Source}
import akka.util.ByteString
import javax.inject._
import play.api._
import play.api.http.HttpEntity
import play.api.mvc._
import utils.MyExecutionContext

import scala.concurrent.{ExecutionContext, Future}


/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(loggerAction: LoggingAction, config: Configuration, cc: ControllerComponents) extends AbstractController(cc) {

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

  /*def blocking(): Action[AnyContent] = Action.async {
    val executionContext: ExecutionContext = mec
    Future {
      // blocking api
     Ok("result ok")
    }(mec)
  }*/

  def streamed: Action[AnyContent] = Action {

    val file = new java.io.File("/home/abdelhadi/A17_FlightPlan.pdf")
    val path: Path = file.toPath
    val source: Source[ByteString, _] = FileIO.fromPath(path)

    val length = Some(file.length())

    Result(
      header = ResponseHeader(200, Map.empty),
      body = HttpEntity.Streamed(source, length, Some("application/pdf"))
    )
  }


  def chunked: Action[AnyContent] = Action {
    val source = Source.apply(List("baz", "foo", "bar"))
    Ok.chunked(source)
  }

  //
}