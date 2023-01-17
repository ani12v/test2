//
/*
* 编写一个Java应用程序，模拟5个人买票。
找零规则:（1）20元买2张票，只找1张10元，不许找2张5元
         （2）20元买1张票，只找1张10元和1张5元，不许找3张5元
//* */
class TicketSeller{// 负责卖票的类
  int fiveNumber=1;
  int tenNumber=0;
  int twentyNumber=0;
  public TicketSeller(Cinema cinema, String 售票员) {}

    public synchronized  void sellTicket(int receiveMoney,int buyNumber) { // 同步方法
       if(receiveMoney==5&&buyNumber==1) {   //若收到1张5元
           fiveNumber+=1;  //5元数加1
     System.out.println(Thread.currentThread().getName()+"给我5元钱，这是您的1张入场卷" ); //输出买票人名字
       }
       else if(receiveMoney==10&&buyNumber==2) {//若收到1张10元且是买2张票
           tenNumber+=1; //10元数加1
      System.out.println(Thread.currentThread().getName()+"给我10元钱，这是您的2张入场卷" ); //输出买票人名字
       }
       else if(receiveMoney==10&&buyNumber==1) {  //若收到1张10元但是只买1张票
          while(fiveNumber<1)   // 如果5元数小于1
          {    try {   System.out.println(Thread.currentThread().getName()+"请靠边等");//输出买票人名字
              wait();   // 线程进入等待状态
               System.out.println(Thread.currentThread().getName()+"结束等待");//输出买票人名字
               }
               catch(InterruptedException e) { }
           }
           fiveNumber-=1; //5元数减1
           tenNumber+=1; //10元数加1
           System.out.println(Thread.currentThread().getName()+"给我10元钱，找您5元，这是您的1张入场卷");//输出买票人名字
       }
       else if(receiveMoney==20&&buyNumber==1) { //若收到1张20元但是只买1张票
          while( fiveNumber<1||tenNumber<1)   // 如果10元或者5元数小于1
          {    try {   System.out.println(Thread.currentThread().getName()+"请靠边等");
               wait();  // 线程进入等待状态
               System.out.println(Thread.currentThread().getName()+"结束等待");
               }
               catch(InterruptedException e) { }
        }
           fiveNumber-=1;//5元数减1
           tenNumber-=1;//10元数减1
           twentyNumber-=1;//20元数加1
        System.out.println(Thread.currentThread().getName()+"给20元钱，找您一张5元和一张10元，这是您的1张入场卷");
     }
     else if(receiveMoney==20&&buyNumber==2) {//若收到1张20元但只买2张票
          while( tenNumber<1 )  // 如果10元数小于1
          {  try {   System.out.println(Thread.currentThread().getName()+"请靠边等");
              wait();  // 线程进入等待状态
          System.out.println(Thread.currentThread().getName()+"结束等待");
                  }
                catch(InterruptedException e) { }
          }
           tenNumber-=1;//10元数减1
           twentyNumber-=1;//20元数加1
          System.out.println(Thread.currentThread().getName()+"给20元钱，找您一张10元，这是您的2张入场卷");
      }
        notifyAll();  //[代码] 通知等待的线程结束等待
  }
}
class Cinema implements Runnable{// 实现Runnable接口的类
   Thread zhao,qian,sun,li,zhou;           // 电影院中买票的线程
   TicketSeller seller;                    // 电影院的售票员
   Cinema() {
      zhao=new Thread(this);     // 初始化zhao
      qian=new Thread(this);     // 初始化qian
      sun=new Thread(this);      // 初始化sun
      li=new Thread(this);       // 初始化li
      zhou=new Thread(this);     // 初始化zhou
      zhao.setName("赵");
      qian.setName("钱");
      sun.setName("孙");
      li.setName("李");
      zhou.setName("周");
      seller= new TicketSeller(this,"售票员"); // 初始化售票员
   }
   public void run() {
       if(Thread.currentThread()==zhao)         // 若为zhao
          seller.sellTicket(20,2);              //给售票员一张20元，买2张票
       else if(Thread.currentThread()==qian)   // 若为qian
          seller.sellTicket(20,1);             //给售票员一张20元，买1张票
       else if(Thread.currentThread()==sun)   // 若为qian
          seller.sellTicket(10,1);            //给售票员一张10元，买1张票
       else if(Thread.currentThread()==li)     // 若为qian
          seller.sellTicket(10,2);            //给售票员一张10元，买2张票
       else if(Thread.currentThread()==zhou)  // 若为qian
          seller.sellTicket(5,1);            //给售票员一张5元，买1张票
   }
}
public class t5 {
   public static void main(String args[]) {
        Cinema cinema=new Cinema();  // 初始化电影院
        cinema.zhao.start();        // zhao开始买票
        try{  Thread.sleep(1000);
        }
        catch(InterruptedException e) { }
        cinema.qian.start();       // qian开始买票
        try{ Thread.sleep(1000);
        }
        catch(InterruptedException e) { }
        cinema.sun.start();        // sun开始买票
        try{  Thread.sleep(1000);
        }
        catch(InterruptedException e) { }
        cinema.li.start();        // li开始买票
        try{  Thread.sleep(1000);
        }
        catch(InterruptedException e) { }
        cinema.zhou.start();    // zhou开始买票

       System.out.println("201603010607黄美");
   }
}

