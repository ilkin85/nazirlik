package main.thread;

public class MyConsumer extends Thread {
    public  String name;

    public MyConsumer(String name){
        this.name = name;
    }
    @Override
    public void run(){
        while (true) {
            int lastIndex = Base.list.size() - 1;
             System.out.println("lastIndex = " + lastIndex);
            if (lastIndex > -1) {
                String s = Base.list.remove(lastIndex);
                System.out.println("s = " + s);
            }
        }
    }
}
