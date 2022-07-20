package com


import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger
import cats.effect.Sync
import cats.implicits._

object MyThing {
//  // Impure But What 90% of Folks I know do with log4s
//  implicit def unsafeLogger[F[_]: Sync] = Slf4jLogger.getLogger[F]

  // Arbitrary Local Function Declaration
//  def doSomething[F[_]: Sync]: F[Unit] =
//    Logger[F].info("Logging Start Something") *>
//      Sync[F].delay(println("I could be doing anything"))
//        .attempt.flatMap{
//        case Left(e) => Logger[F].error(e)("Something Went Wrong")
//        case Right(_) => Sync[F].pure(())
//      }

  def safelyDoThings[F[_]: Sync]: F[Unit] = for {
    logger <- Slf4jLogger.create[F]
    _ <- logger.info("Logging at start of safelyDoThings")
    something <- Sync[F].delay(println("I could do anything"))
      .onError{case e => logger.error(e)("Something Went Wrong in safelyDoThings")}
    _ <- logger.info("Logging at end of safelyDoThings")
  } yield something

  def passForEasierUse[F[_]: Sync: Logger] = for {
    _ <- Logger[F].info("Logging at start of passForEasierUse")
    something <- Sync[F].delay(println("I could do anything"))
      .onError{case e => Logger[F].error(e)("Something Went Wrong in passForEasierUse")}
    _ <- Logger[F].info("Logging at end of passForEasierUse")
  } yield something
}


