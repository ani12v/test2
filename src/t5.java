//
/*
* ��дһ��JavaӦ�ó���ģ��5������Ʊ��
�������:��1��20Ԫ��2��Ʊ��ֻ��1��10Ԫ��������2��5Ԫ
         ��2��20Ԫ��1��Ʊ��ֻ��1��10Ԫ��1��5Ԫ��������3��5Ԫ
//* */
class TicketSeller{// ������Ʊ����
  int fiveNumber=1;
  int tenNumber=0;
  int twentyNumber=0;
  public TicketSeller(Cinema cinema, String ��ƱԱ) {}

    public synchronized  void sellTicket(int receiveMoney,int buyNumber) { // ͬ������
       if(receiveMoney==5&&buyNumber==1) {   //���յ�1��5Ԫ
           fiveNumber+=1;  //5Ԫ����1
     System.out.println(Thread.currentThread().getName()+"����5ԪǮ����������1���볡��" ); //�����Ʊ������
       }
       else if(receiveMoney==10&&buyNumber==2) {//���յ�1��10Ԫ������2��Ʊ
           tenNumber+=1; //10Ԫ����1
      System.out.println(Thread.currentThread().getName()+"����10ԪǮ����������2���볡��" ); //�����Ʊ������
       }
       else if(receiveMoney==10&&buyNumber==1) {  //���յ�1��10Ԫ����ֻ��1��Ʊ
          while(fiveNumber<1)   // ���5Ԫ��С��1
          {    try {   System.out.println(Thread.currentThread().getName()+"�뿿�ߵ�");//�����Ʊ������
              wait();   // �߳̽���ȴ�״̬
               System.out.println(Thread.currentThread().getName()+"�����ȴ�");//�����Ʊ������
               }
               catch(InterruptedException e) { }
           }
           fiveNumber-=1; //5Ԫ����1
           tenNumber+=1; //10Ԫ����1
           System.out.println(Thread.currentThread().getName()+"����10ԪǮ������5Ԫ����������1���볡��");//�����Ʊ������
       }
       else if(receiveMoney==20&&buyNumber==1) { //���յ�1��20Ԫ����ֻ��1��Ʊ
          while( fiveNumber<1||tenNumber<1)   // ���10Ԫ����5Ԫ��С��1
          {    try {   System.out.println(Thread.currentThread().getName()+"�뿿�ߵ�");
               wait();  // �߳̽���ȴ�״̬
               System.out.println(Thread.currentThread().getName()+"�����ȴ�");
               }
               catch(InterruptedException e) { }
        }
           fiveNumber-=1;//5Ԫ����1
           tenNumber-=1;//10Ԫ����1
           twentyNumber-=1;//20Ԫ����1
        System.out.println(Thread.currentThread().getName()+"��20ԪǮ������һ��5Ԫ��һ��10Ԫ����������1���볡��");
     }
     else if(receiveMoney==20&&buyNumber==2) {//���յ�1��20Ԫ��ֻ��2��Ʊ
          while( tenNumber<1 )  // ���10Ԫ��С��1
          {  try {   System.out.println(Thread.currentThread().getName()+"�뿿�ߵ�");
              wait();  // �߳̽���ȴ�״̬
          System.out.println(Thread.currentThread().getName()+"�����ȴ�");
                  }
                catch(InterruptedException e) { }
          }
           tenNumber-=1;//10Ԫ����1
           twentyNumber-=1;//20Ԫ����1
          System.out.println(Thread.currentThread().getName()+"��20ԪǮ������һ��10Ԫ����������2���볡��");
      }
        notifyAll();  //[����] ֪ͨ�ȴ����߳̽����ȴ�
  }
}
class Cinema implements Runnable{// ʵ��Runnable�ӿڵ���
   Thread zhao,qian,sun,li,zhou;           // ��ӰԺ����Ʊ���߳�
   TicketSeller seller;                    // ��ӰԺ����ƱԱ
   Cinema() {
      zhao=new Thread(this);     // ��ʼ��zhao
      qian=new Thread(this);     // ��ʼ��qian
      sun=new Thread(this);      // ��ʼ��sun
      li=new Thread(this);       // ��ʼ��li
      zhou=new Thread(this);     // ��ʼ��zhou
      zhao.setName("��");
      qian.setName("Ǯ");
      sun.setName("��");
      li.setName("��");
      zhou.setName("��");
      seller= new TicketSeller(this,"��ƱԱ"); // ��ʼ����ƱԱ
   }
   public void run() {
       if(Thread.currentThread()==zhao)         // ��Ϊzhao
          seller.sellTicket(20,2);              //����ƱԱһ��20Ԫ����2��Ʊ
       else if(Thread.currentThread()==qian)   // ��Ϊqian
          seller.sellTicket(20,1);             //����ƱԱһ��20Ԫ����1��Ʊ
       else if(Thread.currentThread()==sun)   // ��Ϊqian
          seller.sellTicket(10,1);            //����ƱԱһ��10Ԫ����1��Ʊ
       else if(Thread.currentThread()==li)     // ��Ϊqian
          seller.sellTicket(10,2);            //����ƱԱһ��10Ԫ����2��Ʊ
       else if(Thread.currentThread()==zhou)  // ��Ϊqian
          seller.sellTicket(5,1);            //����ƱԱһ��5Ԫ����1��Ʊ
   }
}
public class t5 {
   public static void main(String args[]) {
        Cinema cinema=new Cinema();  // ��ʼ����ӰԺ
        cinema.zhao.start();        // zhao��ʼ��Ʊ
        try{  Thread.sleep(1000);
        }
        catch(InterruptedException e) { }
        cinema.qian.start();       // qian��ʼ��Ʊ
        try{ Thread.sleep(1000);
        }
        catch(InterruptedException e) { }
        cinema.sun.start();        // sun��ʼ��Ʊ
        try{  Thread.sleep(1000);
        }
        catch(InterruptedException e) { }
        cinema.li.start();        // li��ʼ��Ʊ
        try{  Thread.sleep(1000);
        }
        catch(InterruptedException e) { }
        cinema.zhou.start();    // zhou��ʼ��Ʊ

       System.out.println("201603010607����");
   }
}

