package xml.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import thrift.xml.client.impl.TestServiceSync;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:xml/client/koalas-client.xml"})
public class ClientRunSync {

    @Autowired
    TestServiceSync testServiceSync;

    @Test
    public void testRunSync(){
        long a = 0;
        for (int i = 0; i < 200000; i++) {
            try {
                if(i==100000){
                   a= System.currentTimeMillis ();
                }
                testServiceSync.getRemoteRpc ();
            }catch (Exception e){
                e.printStackTrace ();
            }
        }
        System.out.println ("单机测试单线程环境下，数据热启动之后 10万-20万区间调用次数QPS统计");
        System.out.println ("10w次调用耗时 ：" + (System.currentTimeMillis ()-a));
        System.out.println ("QPS ：" + 100000/((System.currentTimeMillis ()-a)/1000));

    }

    @Test
    public void testThreadRunSync() throws InterruptedException {
        long a = System.currentTimeMillis ();
        Thread t1 =  new Thread ( new InnerRun() );
        Thread t2 =  new Thread ( new InnerRun() );
        Thread t3 =  new Thread ( new InnerRun() );
        Thread t4 =  new Thread ( new InnerRun() );
        Thread t5 =  new Thread ( new InnerRun() );
        Thread t6 =  new Thread ( new InnerRun() );
        Thread t7 =  new Thread ( new InnerRun() );
        Thread t8 =  new Thread ( new InnerRun() );
        Thread t9 =  new Thread ( new InnerRun() );
        Thread t10 = new Thread ( new InnerRun() );

        t1.start ();
        t2.start ();
        t3.start ();
        t4.start ();
        t5.start ();
        t6.start ();
        t7.start ();
        t8.start ();
        t9.start ();
        t10.start ();


        t1.join ();
        t2.join ();
        t3.join ();
        t4.join ();
        t5.join ();
        t6.join ();
        t7.join ();
        t8.join ();
        t9.join ();
        t10.join ();
        System.out.println (System.currentTimeMillis ()-a);
    }


    private class InnerRun implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                try {
                    testServiceSync.getRemoteRpc ();
                }catch (Exception e){
                    e.printStackTrace ();
                }
            }
        }
    }





    @Test
    public void testRunGenericSync(){
        long a = System.currentTimeMillis ();
        for (int i = 0; i < 100000; i++) {
            try {
                testServiceSync.getGenericRpc ();
            }catch (Exception e){
                e.printStackTrace ();
            }

        }
        System.out.println (System.currentTimeMillis ()-a);
    }
}
