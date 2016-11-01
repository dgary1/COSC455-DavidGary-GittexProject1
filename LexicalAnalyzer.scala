package edu.towson.cosc.cosc455.dgary1

/**
  * Created by davidgary on 10/11/16.
  */
trait LexicalAnalyzer {
  def addChar() : Unit
  def getChar() : Char
  def getNextToken() : Unit
  def lookup() : Boolean
}
