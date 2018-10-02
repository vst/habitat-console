package com.vsthost.rnd.habitat.console


/**
  * Defines a console reader trait.
  *
  * @tparam A Type of the data which is attempted to be read.
  */
trait ConsoleReader[A] {
  /**
    * Attempts to read the given string into the target type.
    *
    * @param x Input string.
    * @return [[ConsoleReader.ReadResult]]
    */
  def read(x: String): ConsoleReader.ReadResult[A]
}

/**
  * Provides a companion object to ConsoleReader for definitions and convenience functions.
  */
object ConsoleReader {
  /**
    * Defines a type alias for "read result" encoding.
    *
    * @tparam A Type of the data which is successfully read.
    */
  type ReadResult[A] = Either[String, A]
}
