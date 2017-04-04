package net.junx.example.zk.curator;  

import java.util.ArrayList;
import java.util.List;

import org.apache.curator.framework.AuthInfo;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/** 
 * @author	junx
 * @date	2015年11月17日下午1:52:11 
 * @since 	JDK 1.8
 */

public class CuratorDemo {
    private final static Logger logger = LoggerFactory.getLogger(CuratorDemo.class);
    
    //In the curator-test sub-model the TestingServer class is provided. 
    //This class creates a local, in-process ZooKeeper server that can be used for testing.
    private TestingServer server; 
    
    //In the curator-test sub-model the TestingCluster class is provided. 
    //This class creates an internally running ensemble of ZooKeeper servers.
    //private TestingCluster server = new TestingCluster();
    
    private CuratorFramework client ;
    
    //监视一个结点的创建、更新、删除，并将结点的数据缓存在本地。
    private NodeCache nodeCache;
    
    
    //监视一个路径下子结点的
    //1）创建;
    //2）删除;
    //3）数据的更新。
    //产生的事件会传递给注册的PathChildrenCacheListener。
    private PathChildrenCache pathCache;
    
    //Path Cache和Node Cache的“合体”
    //监视路径下的创建、更新、删除事件，并缓存路径下所有孩子结点的数据。
    private TreeCache treeCache;
    
    private String charset  = "UTF-8";
    
    public static void main(String[] args) throws Exception {
        CuratorDemo app = new CuratorDemo();
        try{
            app.init();//初始化zk连接
//            app.crud();//zk的增删改查
//            app.loopPath("/");//遍历zk上的所有节点(有ACL时会出错)
//            app.nodeCache();
//            app.pathCache();
            app.treeCache();
            System.in.read();
        }catch(Exception e){
            logger.error("",e);
        }finally{
            app.close();
        }
    }

    private void treeCache() throws Exception {
        String watchedPath = "/tree"; 
        treeCache = new TreeCache(client,watchedPath);
        treeCache.getListenable().addListener((client,event)->{
            ChildData childData = null;
            TreeCacheEvent.Type type = null;
            String path = "";
            String data = "";
            type = event.getType();
            childData = event.getData();
            if(null == childData){
              logger.info("null.........");
              return;
            }
            
            if(null!=childData){
                path = childData.getPath();
                data = new String(childData.getData(), charset);
            }else{
                
                logger.debug("No data in event：{}", event);
            }
            logger.info("TreeCache Event ：TYPE=[{}],PATH=[{}],DATA=[{}]",type,path,data);
        });
        treeCache.start();
        logger.info("Listening TreeCache Path [{}]",watchedPath);
    }

    private void nodeCache() throws Exception {
        String watchedPath = "/node"; 
        nodeCache = new NodeCache(client,watchedPath,false);
        nodeCache.getListenable().addListener(()->{
            String path = "";
            String data = "";
            byte[] bytes = null;
            ChildData childData = null;
            
            childData = nodeCache.getCurrentData();
            if(null!=childData){
                path = childData.getPath();
                bytes = childData.getData();
                if(null!=bytes){
                    data = new String(bytes,charset);
                }
            }
            logger.info("NodeCache Event! PATH=[{}],DATA=[{}]",path,data);
        });
        nodeCache.start();
        logger.info("Listening NodeCache Path [{}]",watchedPath);
    }

    private void pathCache() throws Exception {
        String watchedPath = "/path"; 
        pathCache = new PathChildrenCache(client,watchedPath,true);
        pathCache.getListenable().addListener((client,event)->{
            ChildData childData = null;
            PathChildrenCacheEvent.Type type = null;
            String path = "";
            String data = "";
            type = event.getType();
            childData = event.getData();
            if(null!=childData){
                path = childData.getPath();
                data = new String(childData.getData(), charset);
            }else{
                
                logger.debug("No data in event：{}", event);
            }
            logger.info("PathCache Event ：TYPE=[{}],PATH=[{}],DATA=[{}]",type,path,data);
          
        });
        pathCache.start();
        logger.info("Listening PathChildrenCache Path [{}]",watchedPath);
    }

    public void crud() throws Exception {
        String zpath = "/user";
        String data = "性格：胆小怕生";
        
        List<ACL> acls = new ArrayList<ACL>();
        Id id1 = new Id("digest",DigestAuthenticationProvider.generateDigest("junx:110"));
        acls.add(new ACL(ZooDefs.Perms.ALL, id1));
        
        /*
         * CreateMode.EPHEMERAL:创建临时节点，当session失效时，该节点将被删除
         * CreateMode.EPHEMERAL_SEQUENTIAL: 创建临时序列节点，当session失效时，该节点将被删除
         * CreateMode.PERSISTENTL:创建持久节点，不会随着session失效而删除
         * CreateMode.PERSISTENT_SEQUENTIAL:创建持久序列节点，不会随着session失效而删除
         */
        //acl方式
        //client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).withACL(acls).forPath(zpath, data.getBytes(charset));
        client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(zpath, data.getBytes(charset));
        logger.info("创建节点路径[{}],节点数据[{}]",zpath,data);
        
        data = "性格：热情似火";
        client.setData().forPath(zpath, data.getBytes(charset));
        logger.info("修改节点路径[{}]的节点数据为[{}]",zpath,data);
        
        data = new String(client.getData().forPath(zpath),charset);
        logger.info("获取节点路径[{}],节点数据[{}]",zpath,data);
    }


    /**
     * 连接zookeeper
     */
    public void init() throws Exception{
        String address          = "";//zookeeper服务器地址
        String namespace        = "";//命名空间
        int sessionTimeoutMs    = 0;//session超时时间，单位毫秒
        int connectionTimeoutMs = 0;//连接超时时间，单位毫秒
        
        server = new TestingServer();
        
        //集群地址
//        address             = "172.16.104.147:2181,172.16.111.106:2181,172.16.111.107:2181";
//        address             = server.getConnectString();
        address = "127.0.0.1:2181";
        namespace           = "test";
        sessionTimeoutMs    = 60_000;
        connectionTimeoutMs = 10_000;
        
        logger.info("address=[{}]",address);
        
        /**
         * 访问权限
         */
//        List<AuthInfo> authInfos = new ArrayList<AuthInfo>();
//        authInfos.add(new AuthInfo("digest", "junx:110".getBytes(charset)));
        
        client = CuratorFrameworkFactory.builder()
                .connectString(address)
                .namespace(namespace)
                .retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000))
                .sessionTimeoutMs(sessionTimeoutMs)
                .connectionTimeoutMs(connectionTimeoutMs)
//                .authorization(authInfos)
                .build();
        client.start();
        logger.info("zkclient is started!");
    }
    
    /**
     * 释放资源
     */
    public void close(){
      CloseableUtils.closeQuietly(pathCache);
      CloseableUtils.closeQuietly(treeCache);
      CloseableUtils.closeQuietly(nodeCache);
      CloseableUtils.closeQuietly(client);
      CloseableUtils.closeQuietly(server);
    }
    
    
    /**
     * 循环遍历节点
     */
    public void loopPath(String zpath){
        try {
            byte[] bytes = client.getData().forPath(zpath);
            String data = "";
            
            if(null!=bytes){
                data = new String(bytes,this.charset);
            }
            
            logger.info("path={}",zpath);
            logger.info("data={}",data);
            
            List<String> children = client.getChildren().forPath(zpath);
            
            if(null==children){
                return;
            }
            
            children.forEach(p->{
                String path = "";
                if ("/".equals(zpath)) {
                    path = zpath + p;
                } else {
                    path = zpath + "/" + p;
                }
                loopPath(path);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}