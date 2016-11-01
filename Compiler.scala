package edu.towson.cosc.cosc455.dgary1

/**
  * Created by davidgary on 10/11/16.
  */
object Compiler {

  var fileContents : String = ""
  var currentToken : String = ""

  def main(args : Array[String]) = {
    // check usage
    checkFile(args)
    readFile(args(0))

    // get first Token
    Scanner.getNextToken()

    //calls start state of BNF is SyntaxAnalyzer
    Parser.gittex()
  }

  def readFile(file : String) = {
    val source = scala.io.Source.fromFile(file)
    fileContents = try source.mkString finally source.close()

  }
  def checkFile(args: Array[String]) = {
    if (args.length != 1 ){
      println("Usage Error: wrong number of args fool!")
      System.exit(1)
    }
    else if(!args(0).endsWith((".mkd"))){
      println("Usage Error: wrong extension fool!")
      System.exit(1)
    }
  }
}
