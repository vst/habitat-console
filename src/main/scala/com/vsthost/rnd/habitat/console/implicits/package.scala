package com.vsthost.rnd.habitat.console

import cats.effect.IO

import scala.util.Try

/**
  * Provides a bunch of implicits for convenience.
  */
package object implicits {
  /**
    * Defines an [[IO]]-based standard console implementation.
    */
  implicit val stdio: SyncStandardConsole[IO] =
    new SyncStandardConsole[IO]()

  /**
    * Defines some writer implicits for convenience.
    */
  object writer {
    /**
      * Defines an object for wildcard imports on basic types.
      */
    object primitives {
      /**
        * Converts a string to a string.
        */
      implicit val str: ConsoleWriter[String] =
        identity[String]
    }

    /**
      * Defines an object for wildcard imports on basic types with some art.
      */
    object fancy {
      /**
        * Converts a string to a red string.
        */
      implicit val strRed: ConsoleWriter[String] =
        (x: String) => s"${scala.Console.RED}$x${scala.Console.RESET}"

      /**
        * Converts a string to a green string.
        */
      implicit val strGreen: ConsoleWriter[String] =
        (x: String) => s"${scala.Console.GREEN}$x${scala.Console.RESET}"

      /**
        * Converts a string to a blue string.
        */
      implicit val strBlue: ConsoleWriter[String] =
        (x: String) => s"${scala.Console.BLUE}$x${scala.Console.RESET}"

      /**
        * Converts a string to a yellow string.
        */
      implicit val strYellow: ConsoleWriter[String] =
        (x: String) => s"${scala.Console.YELLOW}$x${scala.Console.RESET}"

      /**
        * Converts a string to a magenta string.
        */
      implicit val strMagenta: ConsoleWriter[String] =
        (x: String) => s"${scala.Console.MAGENTA}$x${scala.Console.RESET}"

      /**
        * Converts a string to a cyan string.
        */
      implicit val strCyan: ConsoleWriter[String] =
        (x: String) => s"${scala.Console.CYAN}$x${scala.Console.RESET}"
    }

    /**
      * Defines an object for wildcard importing of writers generated from a toString method.
      */
    object fromToString{
      /**
        * Converts the given object to a [[String]] using `toString` method.
        *
        * You almost never want to use it.
        *
        * @tparam A Type parameter defining the type of the input object.
        * @return [[ConsoleWriter]] for the type of the object which it will consume.
        */
      implicit def fromToString[A]: ConsoleWriter[A] = (x: A) => x.toString
    }
  }


  /**
    * Defines some reader implicits for convenience.
    */
  object reader {
    /**
      * Defines the string reader.
      *
      * Seems trivial and overspecified, but in fact there are certain environments where there is a timeout
      * set in STDIN where we may get a null instead of a string.
      */
    implicit val str: ConsoleReader[String] =
      (x: String) => Option(x).toRight("No input received...")

    /**
      * Defines an [[Int]] reader.
      */
    implicit val int: ConsoleReader[Int] =
      (x: String) => Try(x.toInt).fold(_ => Left(s"'$x' is not a valid integer number"), Right(_))

    /**
      * Defines an [[Long]] reader.
      */
    implicit val long: ConsoleReader[Long] =
      (x: String) => Try(x.toLong).fold(_ => Left(s"'$x' is not a valid long number"), Right(_))

    /**
      * Defines an [[Double]] reader.
      */
    implicit val double: ConsoleReader[Double] =
      (x: String) => Try(x.toDouble).fold(_ => Left(s"'$x' is not a valid double number"), Right(_))

    /**
      * Defines an [[BigDecimal]] reader.
      */
    implicit val decimal: ConsoleReader[BigDecimal] =
      (x: String) => Try(BigDecimal(x)).fold(_ => Left(s"'$x' is not a valid decimal number"), Right(_))
  }
}
