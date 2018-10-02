package com.vsthost.rnd.habitat.console

import cats.tagless._

import scala.language.higherKinds

/**
  * Defines console read/write capabilities.
  *
  * @tparam F Effect type parameter.
  */
@finalAlg
trait Console[F[_]] {
  /**
    * Writes an object to the console.
    */
  def putStr[A](a: A)(implicit writer: ConsoleWriter[A]): F[Unit]

  /**
    * Writes an object to the console followed by a new line.
    */
  def putStrLn[A](a: A)(implicit writer: ConsoleWriter[A]): F[Unit]

  /**
    * Writes an object to the error console.
    */
  def putErr[A](a: A)(implicit writer: ConsoleWriter[A]): F[Unit]

  /**
    * Writes an object to the error console followed by a new line.
    */
  def putErrLn[A](a: A)(implicit writer: ConsoleWriter[A]): F[Unit]

  /**
    * Read data from the console.
    */
  def readLn[A](implicit r: ConsoleReader[A]): F[ConsoleReader.ReadResult[A]]
}
