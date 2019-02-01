package com.example.sbdemo.base;

public class TestWhileAndFor {

    public static void main(String[] args) {
        testWhile();
    }

    public static void testFor() {
        for ( ; ; ) {
            System.out.println("for");
        }
    }

    public static void testWhile() {
        while (true){
            System.out.println("while");
        }
    }

    /**
     * javac Test.java
     * javap -c Test
     *
     * public class org.trustrader.message.mail.Test {
     *   public org.trustrader.message.mail.Test();
     *     Code:
     *       0: aload_0
     *       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     *       4: return
     *
     *   public static void main(java.lang.String[]);
     *     Code:
     *       0: invokestatic  #2                  // Method testWhile:()V
     *       3: return
     *
     *   public static void testFor();
     *     Code:
     *       0: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *       3: ldc           #4                  // String for
     *       5: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *       8: goto          0
     *
     *   public static void testWhile();
     *     Code:
     *       0: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *       3: ldc           #6                  // String while
     *       5: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *       8: goto          0
     * }
     *
     *
     * note:
     *   private method没有compile，public method才会compile
     */

}
