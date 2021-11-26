package com.example.kotlin_lib

class MyClass {
}

fun main() {
    println("hello world!")
    for (entry: Map.Entry<Int, Int> in PrimitiveType.byteSizeByHprofType) {
        println("key = ${entry.key} , value = ${entry.value}")
    }
    println("===============")
    for (entry: Map.Entry<Int, PrimitiveType> in PrimitiveType.primitiveTypeByHprofType) {
        println("key = ${entry.key} , value = ${entry.value}")
    }
    println("===============")
    for (value: PrimitiveType in PrimitiveType.values()) {
        println("$value")
    }
}