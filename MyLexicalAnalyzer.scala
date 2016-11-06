package edu.towson.cosc.cosc455.dgary1
import scala.collection.immutable.Queue

/**
  * Created by davidgary on 10/11/16.
  */
class MyLexicalAnalyzer extends LexicalAnalyzer{
  var truth : Boolean = true
  val lexemes : List[String] = List("\\BEGIN", "\\END", "\\TITLE[", "\\USE[", "\\DEF[", "\\PARE", "\\PARB", "]", "#", "*", "**", "+", "\\", "[", "(", ")", "![", "=")
  var character : Char = ' '
  var stringofChar : String = ""
  // using a mutable queue because we want to add, remove, or change the elements of the queue
  var token = new scala.collection.mutable.Queue[Char]

  override def addChar() : Unit = {
    token.enqueue(character)
    stringOfChar = token.mkString
  }
  override def getChar() : Char = {
    Compiler.position+=1
    Compiler.fileContents.charAt(Compiler.position)
  }
  override def getNextToken() : Unit = {
    character = getChar()
    // checks to see if char retrieved is a space or newline
    if(character.equals(' ') || character.equals('\n')) {
      while(character.equals(' ')) {
        character = getChar()
      }
      // if char equals a special char, then it'll start creating a token
    } else if (character.equals('*') || character.equals('+') || character.equals('#') || character.equals('\\')) {
      addChar()
      character = getChar()
      // the compiler will continue to build the token if it does not hit a special char
      while(!(character.equals('\n') || character.equals('[') || character.equals('('))) {
        addChar()
        character = getChar()
      }
      // method lookUp checks to see if the token is a legitimate token or not
      if (lookUp()) {
        Compiler.token = stringofChar
        while(!token.isEmpty) {
          token.dequeue()
        }
        // if not, throws a lexical error because its not a token and exists program
      } else {
        println("Lexical Error: Token does not exist.")
        System.exit(1)
      }
    }
  }
  override def lookUp() : Boolean = {
  }
}
