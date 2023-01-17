/*
*  编写一个Java应用程序，在主线程中有3个线程，wangWorker、zhangWorker和boss，
* 分别负责搬香蕉、搬苹果和不让工人休息。两个工人每搬一箱水果，就休息10秒。
* */

class Shop implements Runnable {
    Thread zhangWorker,wangWorker,boss;
    Shop() {
    	 boss=new Thread(this);   // 创建boss，Shop对象为boss目标对象
        zhangWorker=new Thread(this);  // 创建zhangWorker，Shop对象为boss目标对象
        wangWorker=new Thread(this);  // 创建wangWorker，Shop对象为boss目标对象
         zhangWorker.setName("张工");// 设置zhangWorker姓名为张工
         wangWorker.setName("王工");// 设置wangWorker姓名为王工
         boss.setName("李老板");// 设置boss姓名为李老板
    }
    public void run() {
       int i=0;
       if(Thread.currentThread()==zhangWorker) {// 若当前线程是zhangWorker
             while(true) {
                  try{  i++;
                      System.out.println(zhangWorker.getName()+"搬了"+i+"箱苹果");//输出谁搬了几箱苹果

                      if(i==5)
                           return;//搬够5箱苹果完工。

                      Thread.sleep(10000);       // zhangWorker休眠10秒（10000毫秒）
                  }
                  catch(InterruptedException e) {
                        System.out.println(boss.getName()+"让"+zhangWorker.getName()+"继续工作!");
                  }
              }
       }
      else if(Thread.currentThread()==wangWorker) {// 若当前线程是wangWorker
           while(true) {
               try{  i++;
                   System.out.println(wangWorker.getName()+"搬了"+i+"箱苹果");//输出谁搬了几箱苹果

                   if(i==5)
                       return;//搬够5箱苹果完工。

                   Thread.sleep(10000);       // zhangWorker休眠10秒（10000毫秒）
               }
               catch(InterruptedException e) {
                   System.out.println(boss.getName()+"让"+wangWorker.getName()+"继续工作!");
               }
           }
      }
      else if(Thread.currentThread()==boss) {
              while(true) {
            	  zhangWorker.interrupt();     // 吵醒zhangWorker  //中断Thread.sleep
            	  wangWorker.interrupt();      // 吵醒wangWorker
                   if(!(zhangWorker.isAlive()||wangWorker.isAlive())) {// 若两位工人都搬完了
                        System.out.println(boss.getName()+"说：下班吧！");   // 老板说下班
                        return;
                   }
              }
      }
    }
}
public class t4 {
    public static void main(String args[]) {
        Shop shop=new Shop();
        shop.zhangWorker.start();         // 开始张工线程
        shop.wangWorker.start();        // 开始王工线程
        shop.boss.start();       // 开始老板线程
        System.out.println("201603010607黄美");
    }
}


