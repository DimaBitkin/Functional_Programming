file:///C:/Users/dima-/OneDrive/Desktop/Функциональное%20и%20логическое%20программирование/lab1/hello-world/src/main/scala/Main.scala
### dotty.tools.dotc.MissingCoreLibraryException: Could not find package scala from compiler core libraries.
Make sure the compiler core libraries are on the classpath.
   

occurred in the presentation compiler.

action parameters:
uri: file:///C:/Users/dima-/OneDrive/Desktop/Функциональное%20и%20логическое%20программирование/lab1/hello-world/src/main/scala/Main.scala
text:
```scala
object Main extends App {
  println("Hello, World!")
}
```



#### Error stacktrace:

```
dotty.tools.dotc.core.Denotations$.select$1(Denotations.scala:1317)
	dotty.tools.dotc.core.Denotations$.recurSimple$1(Denotations.scala:1345)
	dotty.tools.dotc.core.Denotations$.recur$1(Denotations.scala:1347)
	dotty.tools.dotc.core.Denotations$.staticRef(Denotations.scala:1351)
	dotty.tools.dotc.core.Symbols$.requiredPackage(Symbols.scala:917)
	dotty.tools.dotc.core.Definitions.ScalaPackageVal(Definitions.scala:215)
	dotty.tools.dotc.core.Definitions.ScalaPackageClass(Definitions.scala:218)
	dotty.tools.dotc.core.Definitions.AnyClass(Definitions.scala:280)
	dotty.tools.dotc.core.Definitions.syntheticScalaClasses(Definitions.scala:2129)
	dotty.tools.dotc.core.Definitions.syntheticCoreClasses(Definitions.scala:2143)
	dotty.tools.dotc.core.Definitions.init(Definitions.scala:2159)
	dotty.tools.dotc.core.Contexts$ContextBase.initialize(Contexts.scala:900)
	dotty.tools.dotc.core.Contexts$Context.initialize(Contexts.scala:523)
	dotty.tools.dotc.interactive.InteractiveDriver.<init>(InteractiveDriver.scala:41)
	dotty.tools.pc.MetalsDriver.<init>(MetalsDriver.scala:32)
	dotty.tools.pc.ScalaPresentationCompiler.newDriver(ScalaPresentationCompiler.scala:96)
```
#### Short summary: 

dotty.tools.dotc.MissingCoreLibraryException: Could not find package scala from compiler core libraries.
Make sure the compiler core libraries are on the classpath.
   