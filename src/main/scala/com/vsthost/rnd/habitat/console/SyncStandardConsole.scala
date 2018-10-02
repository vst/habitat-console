package com.vsthost.rnd.habitat.console

import cats.effect.Sync

import scala.language.higherKinds


/**
  * Provides a standard console implementation for [[Sync]] effect type.
  *
  * @param F  Effect type parameter.
  * @tparam F Effect type parameter.
  */
class SyncStandardConsole[F[_]](implicit F: Sync[F]) extends Console[F] {
  /**
    * Writes an object to the console.
    */
  override def putStr[A](a: A)(implicit writer: ConsoleWriter[A]): F[Unit] =
    F.delay(print(writer.write(a)))

  /**
    * Writes an object to the console followed by a new line.
    */
  override def putStrLn[A](a: A)(implicit writer: ConsoleWriter[A]): F[Unit] =
    F.delay(println(writer.write(a)))

  /**
    * Writes an object to the error console.
    */
  override def putErr[A](a: A)(implicit writer: ConsoleWriter[A]): F[Unit] =
    F.delay(System.err.print(writer.write(a)))

  /**
    * Writes an object to the error console followed by a new line.
    */
  override def putErrLn[A](a: A)(implicit writer: ConsoleWriter[A]): F[Unit] =
    F.delay(System.err.println(writer.write(a)))

  /**
    * Read a vector of characters from the console.
    */
  override def readLn[A](implicit r: ConsoleReader[A]): F[ConsoleReader.ReadResult[A]] =
    F.delay(r.read(scala.io.StdIn.readLine))
}
