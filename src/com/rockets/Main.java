package com.rockets;

public class Main {

    public static void main(String[] args) {
	    System.out.println("Hey there, rockets!");
	    Message m = new Message("some_program", "content is so good");
	    Message m1 = new Message("some_program", "yummy yummy content so yummy in my tummy");
        Queue q = new Queue("some_program");
        q.add(m); q.add(m1);
        System.out.println(q);
    }
}
