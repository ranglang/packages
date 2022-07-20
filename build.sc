import $ivy.`com.goyeau::mill-git::0.2.3`
import $ivy.`com.lihaoyi::mill-contrib-bloop:$MILL_VERSION`
import $ivy.`com.goyeau::mill-scalafix::0.2.8`
import $ivy.`io.github.davidgregory084::mill-tpolecat::0.3.1`
import $file.project.Dependencies
import Dependencies.Dependencies._
// import $file.project.{SwaggerModelGenerator => SwaggerModelGeneratorFile}
// import SwaggerModelGeneratorFile.SwaggerModelGenerator
import com.goyeau.mill.git.{GitVersionModule, GitVersionedPublishModule}
import com.goyeau.mill.scalafix.StyleModule
import io.github.davidgregory084.TpolecatModule
import mill._
import mill._, scalalib._
import mill.scalalib.TestModule.Munit
import mill.scalalib._
import mill.scalalib.api.Util.isScala3
import mill.scalalib.publish.{Developer, License, PomSettings, VersionControl}


import mill._, scalalib._

object kubernetes extends ScalaModule {
  // def scalaVersion = "3.0.2"
 override def scalaVersion ="3.1.3"
  override def scalacOptions =
    super
      .scalacOptions()
      .filterNot(Set("-Xfatal-warnings")) ++ Seq("-language:Scala2", "-Xmax-inlines", "50")
      // (if (isScala3(scalaVersion()))  else Seq.empty)

  override def ivyDeps =
    super.ivyDeps() ++ http4s ++ circe ++ circeYaml ++ bouncycastle ++ collectionCompat ++ logging ++ airframe
  override def scalacPluginIvyDeps = super.scalacPluginIvyDeps() 
  // ++
  //   (if (isScala3(scalaVersion())) Agg.empty else Agg(ivy"org.typelevel:::kind-projector:0.13.2"))
}


// object `kubernetes`extends KubernetesClientModule
// extends Cross[KubernetesClientModule]("3.1.3")
// object kubernetes
//     extends  ScalaModule {


//   object test extends Tests with Munit {
//     override def forkArgs = super.forkArgs() :+ "-Djdk.tls.client.protocols=TLSv1.2"
//     override def ivyDeps  = super.ivyDeps() ++ tests ++ logback
//   }
// }
