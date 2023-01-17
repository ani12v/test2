//import java.lang.annotation.Native;

class Tortoise extends Thread{//乌龟类
    int sleepTime=0,liveLength=0;

    Tortoise(int sleepTime,String name,int liveLength){
        this.sleepTime=sleepTime;
        this.liveLength=liveLength;
        setName(name);//设置线程的名字代码为name
    }
    public void run(){
        while (true){
            liveLength--;
            System.out.print("@");
            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (liveLength==0){
                System.out.println("乌龟进入死亡状态");
                return;
            }
        }
    }
}
class Rabbit extends Thread{//兔子类
    int sleepTime=0,liveLength;
    Rabbit(int sleepTime,String name,int liveLength){
        this.sleepTime=sleepTime;
        this.liveLength=liveLength;
        setName(name);//设置线程的名字代码为name
    }
    public void run(){
        while (true){
            liveLength--;
            System.out.print("*");
            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (liveLength==0){
                System.out.println("兔子进入死亡状态");
                return;
            }
        }
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        Rabbit rabit=new Rabbit(2000,"兔子",50);
        Tortoise tortoise=new Tortoise(1000,"乌龟",50);
        rabit.start();
        tortoise.start();
        System.out.println("201603010607黄美");
    }
}
