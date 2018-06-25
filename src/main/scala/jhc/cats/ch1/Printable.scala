package jhc.cats.ch1

//Define a type class Printable[A] containing a single method format. format should accept
//a value of type A and return a string.
//
//Create an object PrintableInstances containing instances of Printable for String and Int.
//
//Define an object Printable with two generic interface methods.

trait Printable[A] {
  def format(value: A): String
}

object PrintableInstances {
  implicit val stringFormatter: Printable[String] =
    new Printable[String] {
      def format(value: String): String =
        value
    }

  implicit val intFormatter: Printable[Int] =
    new Printable[Int] {
      def format(value: Int): String =
        value.toString
    }

  implicit val catFormatter: Printable[Cat] =
    new Printable[Cat] {
      def format(value: Cat): String = 
        s"${value.name} is a ${value.age} year-old ${value.color} cat."
    }
}

object Printable {
  def format[A](value: A)(implicit p: Printable[A]): String =
    p.format(value)

  def print[A](value: A)(implicit p: Printable[A]): Unit =
    println(format(value)(p))
}

final case class Cat(name: String, age: Int, color: String)

object PrintableSyntax {
  implicit class PrintableOps[A](a: A) {
    def format(implicit p: Printable[A]): String = Printable.format(a)
    def print(implicit p: Printable[A]): Unit = Printable.print(a)
  }
}
