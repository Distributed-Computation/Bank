package test.JSON;

import java.net.URL;
import java.util.*;

/**
 * @author Gam
 * @version 1.0
 * @date 2020/4/16 22:29
 */
public class RoundRobin {
    public static Integer index = 0;
    public static int defaultWeight = 1;
    Map<String,Integer> serverMap = new HashMap<String,Integer>();
    URL temp = json.class.getResource("ServerList.json");
    String path = temp.toString().replace("file:/","");

    testJSON testjson = new testJSON();
    List<ServerSite> sites = testjson.readServers(path);

    public void testRoundRobin(){

        for (int i=0;i<sites.size();i++) {
            serverMap.put(sites.get(i).getHost(), defaultWeight);
        }
            //取得Ip地址list
            Set<String> keySet = serverMap.keySet();
            ArrayList<String> keyList = new ArrayList<String>();
            //权重轮询
//            List<String> serverList = new ArrayList<String>();
//            Iterator<String> it = keySet.iterator();
//            while(it.hasNext()){
//                String server = it.next();
//                Integer weight = serverMap.get(server);
//                for (int i = 0; i < weight; i++) {
//                    serverList.add(server);
//                }
//            }
            keyList.addAll(keySet);
            String server = null;
            //当前轮询位置index，为了保证服务器选择的顺序性，需要在操作是对齐加上synchronized锁，使得在同一时刻只有一个线程能够修改index的值，
            //否则index变量被并发修改是则无法保证服务器选择的顺序性
            synchronized(index){
                if(index >= keySet.size()){
                    index = 0;
                }
                server = keyList.get(index);
                System.out.println("index: "+index+",服务器："+server);
                index++;
            }
        }
}
