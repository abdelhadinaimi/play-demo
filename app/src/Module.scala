import com.google.inject.AbstractModule
import utils.{MyExecutionContext, MyExecutionContextImpl}

class Module extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[MyExecutionContext]).to(classOf[MyExecutionContextImpl])
  }
}
