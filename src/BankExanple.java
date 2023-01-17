
class Bank implements Runnable{
    int money=100;
    Thread zhang,keven;
    Bank(){
        zhang=new Thread(this);//创建zhang,Bank对象为张的目标对象
        zhang.setName("会计");//设置zhang的线程名字为"会计"
        keven=new Thread(this);//创建keven,Bank对象为keven的目标对象
        keven.setName("出纳");//设置zhang的线程名字为"出纳"
    }
    @Override
    public void run(){
        int i=0;
        while(true){
            if (Thread.currentThread()==zhang){//判断线程zhang是否占有cpu资源
                i= i + 1;
                money= money + 1;
                System.out.print("张会计目前金额"+money);
                System.out.printf("\n%s将money的值改为%d\t",zhang.getName(),money);
                System.out.printf("%s的局部变量i=%d\n",zhang.getName(),i);
                if (i >= 6){
                    System.out.printf("%s线程进入死亡状态\n",zhang.getName());
                    return;//是线程zhang进入死亡状态
                }
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){}
            }else if(Thread.currentThread()==keven){//判断线程keven是否占有cpu资源
                i=i-1;
                money=money-1;
                System.out.printf("\n%s将money的值改为%d\t",keven.getName(),money);
                System.out.printf("%s的局部变量i=%d\n",keven.getName(),i);
                if (i<=-6){
                    System.out.printf("%s线程进入死亡状态\n",keven.getName());
                    return;//使线程keven进入死亡状态
                }
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){}
            }
        }
    }
}

public class BankExanple {
    public static void main(String[] args) {
        Bank bank=new Bank();
        bank.zhang.start();
        bank.keven.start();
        System.out.println("201603010607黄美");
    }
}
