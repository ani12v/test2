/*
*  ��дһ��JavaӦ�ó��������߳�����3���̣߳�wangWorker��zhangWorker��boss��
* �ֱ�����㽶����ƻ���Ͳ��ù�����Ϣ����������ÿ��һ��ˮ��������Ϣ10�롣
* */

class Shop implements Runnable {
    Thread zhangWorker,wangWorker,boss;
    Shop() {
    	 boss=new Thread(this);   // ����boss��Shop����ΪbossĿ�����
        zhangWorker=new Thread(this);  // ����zhangWorker��Shop����ΪbossĿ�����
        wangWorker=new Thread(this);  // ����wangWorker��Shop����ΪbossĿ�����
         zhangWorker.setName("�Ź�");// ����zhangWorker����Ϊ�Ź�
         wangWorker.setName("����");// ����wangWorker����Ϊ����
         boss.setName("���ϰ�");// ����boss����Ϊ���ϰ�
    }
    public void run() {
       int i=0;
       if(Thread.currentThread()==zhangWorker) {// ����ǰ�߳���zhangWorker
             while(true) {
                  try{  i++;
                      System.out.println(zhangWorker.getName()+"����"+i+"��ƻ��");//���˭���˼���ƻ��

                      if(i==5)
                           return;//�ṻ5��ƻ���깤��

                      Thread.sleep(10000);       // zhangWorker����10�루10000���룩
                  }
                  catch(InterruptedException e) {
                        System.out.println(boss.getName()+"��"+zhangWorker.getName()+"��������!");
                  }
              }
       }
      else if(Thread.currentThread()==wangWorker) {// ����ǰ�߳���wangWorker
           while(true) {
               try{  i++;
                   System.out.println(wangWorker.getName()+"����"+i+"��ƻ��");//���˭���˼���ƻ��

                   if(i==5)
                       return;//�ṻ5��ƻ���깤��

                   Thread.sleep(10000);       // zhangWorker����10�루10000���룩
               }
               catch(InterruptedException e) {
                   System.out.println(boss.getName()+"��"+wangWorker.getName()+"��������!");
               }
           }
      }
      else if(Thread.currentThread()==boss) {
              while(true) {
            	  zhangWorker.interrupt();     // ����zhangWorker  //�ж�Thread.sleep
            	  wangWorker.interrupt();      // ����wangWorker
                   if(!(zhangWorker.isAlive()||wangWorker.isAlive())) {// ����λ���˶�������
                        System.out.println(boss.getName()+"˵���°�ɣ�");   // �ϰ�˵�°�
                        return;
                   }
              }
      }
    }
}
public class t4 {
    public static void main(String args[]) {
        Shop shop=new Shop();
        shop.zhangWorker.start();         // ��ʼ�Ź��߳�
        shop.wangWorker.start();        // ��ʼ�����߳�
        shop.boss.start();       // ��ʼ�ϰ��߳�
        System.out.println("201603010607����");
    }
}


