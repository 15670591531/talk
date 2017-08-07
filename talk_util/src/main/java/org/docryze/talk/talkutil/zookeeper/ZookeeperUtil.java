package org.docryze.talk.talkutil.zookeeper;


import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import static org.jboss.netty.handler.codec.rtsp.RtspHeaders.Values.CLIENT_PORT;

/**
 * Zookeeper工具类,用于连接服务,交互数据
 */
public class ZookeeperUtil {
    public static String QueryFromZookeeper(String path) {
        ZooKeeper zk = null;
        String data = null;
        try {
            // 创建一个与服务器的连接
            zk = new ZooKeeper("localhost:" + 2181, 3000, null);
            // 获取指定路径的数据
            if (zk.exists(path, false) == null) {
                return data;
            }
            data = new String(zk.getData(path, false, null), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            if (zk == null) {
                try {
                    zk.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return data;
        }
    }
}
