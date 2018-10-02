# habitat-console

> **TODO:** Add project description.

## Example

```scala
import cats.effect._
import cats.implicits._
import com.vsthost.rnd.habitat.console.Console
import com.vsthost.rnd.habitat.console.Console.implicits._

import scala.language.higherKinds

def instructions[F[_]: Sync: Console]: F[Unit] = for {
  _ <- Console[F].putStrLn("Hello!")
  _ <- Console[F].putStr("What is your name?")
  i <- Console[F].readLn[String]
  _ <- i match {
    case Left(e) => Console[F].putErrLn(e)
    case Right(n) => Console[F].putStrLn(s"Nice to meet you $n.")
  }
} yield ()

instructions[IO].unsafeRunSync()
```

## License

Copyright (c) 2018 Vehbi Sinan Tunalioglu &lt;vst@vsthost.com&gt;

habitat-console is licensed under [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0).
