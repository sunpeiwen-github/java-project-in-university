public class ThreadClass {
    public static void main(String []args)
    {
        ThreadOdd odd=new ThreadOdd();
        odd.start();
        ThreadPrime prime=new ThreadPrime();
        Thread t=new Thread(prime);
        t.start();
    }
}
class ThreadOdd extends Thread {
    public void run()
    {
        for(int i=1;i<100;i+=2)
        {
            System.out.println("奇数"+i);
        }
    }
}
class ThreadPrime implements Runnable{
    public void run()
    {

        for(int i=2;i<100;i++)
        {
            int flag=0;
            for(int j=2;j<i;j++)
            {
                if(i%j==0)
                {
                   flag=1;
                   break;
                }
            }
            if(flag==0)
            {
                System.out.println("素数"+i);

            }
        }
    }
}
