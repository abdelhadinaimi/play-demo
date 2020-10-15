package controllers.submodule1

import java.nio.file.Path

import akka.stream.scaladsl.{FileIO, Source}
import akka.util.ByteString
import javax.inject.Inject
import play.api.http.HttpEntity
import play.api.mvc._
import actions._
import javax.inject.Singleton
import play.api.Configuration
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(loggerAction: LoggingAction, config: Configuration, cc: ControllerComponents) extends AbstractController(cc) {

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