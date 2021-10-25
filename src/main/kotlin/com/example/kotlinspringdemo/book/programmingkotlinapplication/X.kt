package com.example.kotlinspringdemo.book.programmingkotlinapplication

open class X1 { // A default constructor is created automatically without any constructors.
    val name: String = "X1"
}

open class X2(val name: String = "X2") { // A default constructor is created automatically with all default values.
}

open class X3 {
    val name: String get()="X3"
}

open class X4 {
    val name: String
    init {
        name = "X4"
    }

    constructor() {
        println("X4.constructor()")
    }
}

open class X5 {
    val name: String

    constructor(name: String) {
        this.name = name
    }
}

open class X6 {
    val name: String

    constructor() {
        name = "?"
    }

    constructor(name: String) : this() { /*this.name="X6"*/
    }
}

open class X7 constructor() {
    lateinit var name: String

    constructor(name: String) : this() {
        this.name = name
    }
}

open class X8() {
    var name: String

    init {
        name = "?"
    }

    constructor(name: String) : this() {
        this.name = name
    }
}

open class X9(val name:String, var age:Int=20){}

class Y401 : X4 {
    constructor(){ // :super() is called automatically.
        println("Y401.consturctor()")
    }
}
class Y402 : X4() {
    init { println("Y402.consturctor()") }
} // equivalent to above
class Y403:X4{
    constructor():super(){
        println("Y403.consturctor()")
    }
} // equivalent to above
class Y404():X4(){
    init { println("Y404.consturctor()") }
} // equivalent to above

class Y201 : X2 {
    constructor()
}
class Y202: X2() {}
class Y203(name:String):X2(name) {}
class Y204:X2 {
    constructor(name:String):super(name)
}

class Y901: X9 {
    constructor(name:String):super(name)
}
class Y902: X9 {
    constructor(name:String,age:Int):super(name,age)
}
class Y903(name:String):X9(name){}
class Y904(name:String,age:Int):X9(name,age){}


fun main() {
    println(X1().name)
    println(X2().name)
    println(X3().name)
    println(X4().name)
    println(X5("X5").name)
    println(X6().name)
    println(X7("X7").name)
    println(X8("X8").name)

    Y401()
    Y402()
    Y403()
    Y404()
}