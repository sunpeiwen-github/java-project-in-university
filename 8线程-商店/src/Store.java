import java.awt.*;
import java.io.ObjectInputStream;

public class Store {
    public static void main(String []args)
    {
        Good good=new Good(100);
        In in=new In(good);
        Out out=new Out(good);
    }
}
class Good
{
    int count;
    Good (int count)
    {
        this.count=count;
    }
    public  synchronized void in()
    {
        if(count!=0)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count=100;
        System.out.println("进货100");
        notify();
    }
    public synchronized  void out()
    {
        if(count==0)
        {
            try {
                notify();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"商品"+count);
        count--;
    }

}
class In extends Thread
{
    Good good;
    In(Good good)
    {
        this.good=good;
        start();
    }
    public void run()
    {
        while(true)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            good.in();
        }
    }
}
class Out extends Thread
{
    Good good;
    Out(Good good)
    {
        this.good=good;
        start();
    }
    public void run()
    {
        while(true)
        {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        good.out();
    }
    }

}