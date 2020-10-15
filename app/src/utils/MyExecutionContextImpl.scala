package utils

import akka.actor.ActorSystem
import javax.inject.Inject
import play.api.libs.concurrent.CustomExecutionContext


class MyExecutionContextImpl @Inject()(system: ActorSystem)
  extends CustomExecutionContext(system, "default-dispatcher") with MyExecutionContext

