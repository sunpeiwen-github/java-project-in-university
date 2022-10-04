/*一个超市现有商品100件，一个线程负责卖商品（一次卖1件)，一个线程负责进货（1次进100件）。
如果商品数量为0时，销售线程通知进货线程进货，进货后通知销售线程销售。
 *
 */


import java.awt.*;
import java.io.ObjectInputStream;

public class Store {
    public static void main(String[] args) {
        Good good = new Good(10);
        Thread in = new In(good);
        Thread out = new Out(good);
        in.start();
        out.start();

    }
}

class Good {
    int count;

    Good(int count) {
        this.count = count;
    }
}

class In extends Thread {
    Good good;

    In(Good good) {
        this.good = good;
    }

    public void run() {
        while (true) {
            synchronized (good) {

               /* if (good.count == 0) {
                   good.count = 10;
                    System.out.println("进货10");
                    this.notify();
                } else {
                    System.out.println("等待");
                    try {
                      //  this.notify();
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/

                /*if (good.count > 0) {
                    try {
                        System.out.println("等待");
                        wait();
                        System.out.println("等待2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
                /*if(good.count == 0) {
                    good.count = 10;
                    System.out.println("进货10");
                    good.notifyAll();
                }else {
                    try {
                        good.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
            }
        }
    }
}

class Out extends Thread {
    Good good;

    Out(Good good) {
        this.good = good;
    }

    public void run() {
        while (true) {
            //synchronized (this) {
            if (good.count != 0) {
                good.count--;
                System.out.println("剩余商品" + good.count);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    synchronized (good) {
                        System.out.println("count=0");
                        good.notifyAll();
                        good.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //}
        }
    }
}