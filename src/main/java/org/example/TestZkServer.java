package org.example;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author Dajie
 * @create 2020/9/4 22:35
 */
public class TestZkServer {
    public static final String SERVERS = "47.115.46.193:2181,47.115.46.193:2182,47.115.46.193:2183";
    public static ZkClient zkClient = null;

    static {
        zkClient = new ZkClient(SERVERS, 100000, 100000, new CustomSerializer());
    }

    public static void main(String[] args) throws IOException {

        zkClient.create("/java","java", CreateMode.PERSISTENT);
        zkClient.create("/java/java1","java1", CreateMode.PERSISTENT);
        zkClient.create("/java/java2","java2", CreateMode.PERSISTENT);
        zkClient.create("/java/java3","java3", CreateMode.PERSISTENT);
        zkClient.create("/java/java4","java4", CreateMode.PERSISTENT);

        /**
         * 监听
         * 参数1  监听的节点
         * 参数2 方法回调
         */
        zkClient.subscribeDataChanges("/java",new IZkDataListener(){

            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("节点数据发生变更"+dataPath+"  "+data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("节点被删除"+dataPath);
            }
        });


        //监听子节点数据的添加 和删除
        zkClient.subscribeChildChanges("/java", (parentPath, currentChilds) -> {
            System.out.println("父节点路径:" + parentPath);
            System.out.println("变更之后的子节点的信息");
            for (String currentChild : currentChilds) {
                System.out.println(currentChild);
            }
        });

        //不能让程序退出
        System.in.read();
        System.out.println("操作成功zkClient");
    }
}
