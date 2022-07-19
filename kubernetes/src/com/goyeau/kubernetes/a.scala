package e


import cats.effect._, org.http4s._, org.http4s.dsl.io._

import cats.effect.unsafe.IORuntime

object M extends IOApp  {
  println("ok")

//  implicit val runtime: IORuntime = cats.effect.unsafe.IORuntime.global

  val helloWorldService = HttpRoutes.of[IO] {
    case GET -> Root / "hello" / name =>
      Ok(s"Hello, $name.")
  }

  import cats.syntax.all._
  import com.comcast.ip4s._
  import org.http4s.ember.server._
  import org.http4s.implicits._
  import org.http4s.server.Router
  import scala.concurrent.duration._
//  val services = tweetService <+> helloWorldService
  // services: cats.data.Kleisli[cats.data.OptionT[IO, β$0$], Request[IO], Response[IO]] = Kleisli(
  //   run = cats.data.KleisliSemigroupK$$Lambda$20809/607901384@6d3944e4
  // )
  val httpApp = Router("/" -> helloWorldService).orNotFound
  // httpApp: cats.data.Kleisli[IO[A], Request[IO[A]], Response[IO[A]]] = Kleisli(
  //   run = org.http4s.syntax.KleisliResponseOps$$Lambda$21084/1405153553@7bea990f
  // )
  val server = EmberServerBuilder
    .default[IO]
    .withHost(ipv4"0.0.0.0")
    .withPort(port"8080")
    .withHttpApp(httpApp)
    .build

//  server.stream[IO].compile.drain.as(ExitCode.Success)
//    .allocated.unsafeRunSync()._2
  val shutdown = server
    .use(_ => IO.never)
    .as(ExitCode.Success)

//  object Main extends IOApp {

//    val helloWorldService = HttpRoutes.of[IO] {
  //      case GET -> Root / "hello" / name =>
  //        Ok(s"Hello, $name.")
  //    }.orNotFound

    def run(args: List[String]): IO[ExitCode] =
      EmberServerBuilder
        .default[IO]
        .withHost(ipv4"0.0.0.0")
        .withPort(port"8080")
        .withHttpApp(httpApp)
        .build
        .use(_ => IO.never)
        .as(ExitCode.Success)
}
