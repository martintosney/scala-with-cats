package jhc.cats.ch1

import org.scalatest._

class PrintableSpec extends FlatSpec with Matchers {
  import PrintableInstances._
  val cat = Cat("Luna", 8, "black")

  "Printable" should "convert a string to the same string" in {
    val testString = "Hello"
    Printable.format(testString) shouldBe testString
  }

  it should "convert an int to a string" in {
    val testInt = 123
    Printable.format(testInt) shouldBe "123"
  }

  it should "print a cat" in {
    Printable.format(cat) shouldBe "Luna is a 8 year-old black cat."
  }
    
  it should "print the same cat using PrintableOps" in {
    import PrintableSyntax._
    cat.format shouldBe "Luna is a 8 year-old black cat."
  }
    
}
