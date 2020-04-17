package test.JSON;

import java.net.URL;
import java.util.List;

/**
 * @author Gam
 * @version 1.0
 * @date 2020/4/16 21:44
 */
public class json {

    public static void main(String args[]){

//        URL path = json.class.getClassLoader().getResource("test/JSON/ServerList.json");
//        URL temp = json.class.getResource("ServerList.json");
//        String path = temp.toString().replace("file:/","");
//
//        testJSON testjson = new testJSON();
//        List<ServerSite> sites = testjson.readServers(path);
        myThread mt = new myThread();
        myThread mt2 = new myThread();
        mt.start();
        mt2.start();
        for (int i = 0;i<10;i++){
            System.out.println("主线程：");
            RoundRobin roundRobin = new RoundRobin();
            roundRobin.testRoundRobin();
        }
//
//
//        System.out.println("["+path+"]\n");
//        System.out.println(sites.get(0).getHost());



    }

}

class myThread extends Thread{
    @Override
    public void run(){
        for (int i = 0;i<10;i++){
            System.out.println("副线程：");
            RoundRobin roundRobin = new RoundRobin();
            roundRobin.testRoundRobin();
        }
    }
        }