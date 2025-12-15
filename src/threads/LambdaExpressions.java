package threads;

import java.util.Scanner;

public class LambdaExpressions {
    public static void main(String[] args) {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Hello");
//            }
//        };
//        Runnable runnable = () -> System.out.println("Hello"); // can use lambda expression
//        Thread t1 = new Thread(runnable);
        Thread t1 = new Thread(() -> System.out.println("Hello"));
        t1.start();
        Student student = (name) -> { // this is the implementation of interface Student
//            System.out.println("The name of the student is " + name);
            return name;
        };

        String studentName = student.getName("Max");
        System.out.println("The name of the student is " + studentName);
    }
}
