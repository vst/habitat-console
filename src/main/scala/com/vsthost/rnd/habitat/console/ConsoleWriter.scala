package com.vsthost.rnd.habitat.console


/**
  * Defines a console writer trait.
  *
  * @tparam A Type of the data which is attempted to be written.
  */
trait ConsoleWriter[A] {
  /**
    * Provides a [[String]] representation of the given object.
    *
    * @param x Input object.
    * @return A [[String]] representation of the given object.
    */
  def write(x: A): String
}
