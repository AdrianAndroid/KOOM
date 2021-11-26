package com.kwai.koom.demo.javaleak.test;

/**
 * Time:2021/12/7 17:32
 * Author: flannery
 * Description: https://blog.csdn.net/weixin_34123613/article/details/85977614
 */
public class THISLeakMaker {
    public void main1() {
        detectMethod(new IMValueCallback() {
            @Override
            public void onSuccess(Object o) {
                testMethod();
            }
        });
    }

    public void testMethod() {

    }

    public void detectMethod(IMValueCallback callback) {

    }

    public interface IMValueCallback<T> {
        void onSuccess(T t);
    }
}

/*
/usr/bin/javap -c -v -p -sysinfo -constants -s -l -version com.kwai.koom.demo.javaleak.test.TestClass
11.0.9
Classfile /Users/flannery/Desktop/mylibrary/KOOM/koom-demo/build/intermediates/javac/debug/classes/com/kwai/koom/demo/javaleak/test/TestClass.class
  Last modified 2021年12月7日; size 962 bytes
  MD5 checksum 18a8609713fe90710dfdfabc40676036
  Compiled from "TestClass.java"
public class com.kwai.koom.demo.javaleak.test.TestClass
  minor version: 0
  major version: 52
  flags: (0x0021) ACC_PUBLIC, ACC_SUPER
  this_class: #5                          // com/kwai/koom/demo/javaleak/test/TestClass
  super_class: #6                         // java/lang/Object
  interfaces: 0, fields: 0, methods: 4, attributes: 2
Constant pool:
   #1 = Methodref          #6.#26         // java/lang/Object."<init>":()V
   #2 = Class              #27            // com/kwai/koom/demo/javaleak/test/TestClass$1
   #3 = Methodref          #2.#28         // com/kwai/koom/demo/javaleak/test/TestClass$1."<init>":(Lcom/kwai/koom/demo/javaleak/test/TestClass;)V
   #4 = Methodref          #5.#29         // com/kwai/koom/demo/javaleak/test/TestClass.detectMethod:(Lcom/kwai/koom/demo/javaleak/test/TestClass$IMValueCallback;)V
   #5 = Class              #30            // com/kwai/koom/demo/javaleak/test/TestClass
   #6 = Class              #31            // java/lang/Object
   #7 = Class              #32            // com/kwai/koom/demo/javaleak/test/TestClass$IMValueCallback
   #8 = Utf8               IMValueCallback
   #9 = Utf8               InnerClasses
  #10 = Utf8               <init>
  #11 = Utf8               ()V
  #12 = Utf8               Code
  #13 = Utf8               LineNumberTable
  #14 = Utf8               LocalVariableTable
  #15 = Utf8               this
  #16 = Utf8               Lcom/kwai/koom/demo/javaleak/test/TestClass;
  #17 = Utf8               main1
  #18 = Utf8               testMethod
  #19 = Utf8               detectMethod
  #20 = Utf8               (Lcom/kwai/koom/demo/javaleak/test/TestClass$IMValueCallback;)V
  #21 = Utf8               callback
  #22 = Utf8               Lcom/kwai/koom/demo/javaleak/test/TestClass$IMValueCallback;
  #23 = Utf8               MethodParameters
  #24 = Utf8               SourceFile
  #25 = Utf8               TestClass.java
  #26 = NameAndType        #10:#11        // "<init>":()V
  #27 = Utf8               com/kwai/koom/demo/javaleak/test/TestClass$1
  #28 = NameAndType        #10:#33        // "<init>":(Lcom/kwai/koom/demo/javaleak/test/TestClass;)V
  #29 = NameAndType        #19:#20        // detectMethod:(Lcom/kwai/koom/demo/javaleak/test/TestClass$IMValueCallback;)V
  #30 = Utf8               com/kwai/koom/demo/javaleak/test/TestClass
  #31 = Utf8               java/lang/Object
  #32 = Utf8               com/kwai/koom/demo/javaleak/test/TestClass$IMValueCallback
  #33 = Utf8               (Lcom/kwai/koom/demo/javaleak/test/TestClass;)V
{
  public com.kwai.koom.demo.javaleak.test.TestClass();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 8: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcom/kwai/koom/demo/javaleak/test/TestClass;

  public void main1();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=4, locals=1, args_size=1
         0: aload_0
         1: new           #2                  // class com/kwai/koom/demo/javaleak/test/TestClass$1
         4: dup
         5: aload_0
         6: invokespecial #3                  // Method com/kwai/koom/demo/javaleak/test/TestClass$1."<init>":(Lcom/kwai/koom/demo/javaleak/test/TestClass;)V
         9: invokevirtual #4                  // Method detectMethod:(Lcom/kwai/koom/demo/javaleak/test/TestClass$IMValueCallback;)V
        12: return
      LineNumberTable:
        line 10: 0
        line 16: 12
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      13     0  this   Lcom/kwai/koom/demo/javaleak/test/TestClass;

  public void testMethod();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=0, locals=1, args_size=1
         0: return
      LineNumberTable:
        line 20: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       1     0  this   Lcom/kwai/koom/demo/javaleak/test/TestClass;

  public void detectMethod(com.kwai.koom.demo.javaleak.test.TestClass$IMValueCallback);
    descriptor: (Lcom/kwai/koom/demo/javaleak/test/TestClass$IMValueCallback;)V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=0, locals=2, args_size=2
         0: return
      LineNumberTable:
        line 24: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       1     0  this   Lcom/kwai/koom/demo/javaleak/test/TestClass;     // TODO this指针
            0       1     1 callback   Lcom/kwai/koom/demo/javaleak/test/TestClass$IMValueCallback;
    MethodParameters:
      Name                           Flags
      callback
}
SourceFile: "TestClass.java"
InnerClasses:
  public static #8= #7 of #5;             // IMValueCallback=class com/kwai/koom/demo/javaleak/test/TestClass$IMValueCallback of class com/kwai/koom/demo/javaleak/test/TestClass
  #2;                                     // class com/kwai/koom/demo/javaleak/test/TestClass$1

Process finished with exit code 0

 */
