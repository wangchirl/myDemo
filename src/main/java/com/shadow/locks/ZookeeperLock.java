package com.shadow.locks;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.stream.Collectors;

public class ZookeeperLock {

    // 连接ZK客户端
    private ZkClient zkClient;
    // 创建临时序号节点
    public ZookeeperLock(){
        zkClient = new ZkClient("127.0.0.1:2181",
                5000,20000);
    }
    // 1.获得锁
    public Lock lock(String lockId, long timeout){
        Lock lockNode = createLockNode(lockId);
        lockNode = tryActiveLock(lockNode);
        if(!lockNode.isActive()){
            try {
                synchronized (lockNode){
                    lockNode.wait(timeout);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return lockNode;
    }

    // 2.激活锁
    public Lock tryActiveLock(Lock lockNode){
        // 判断是否获得锁
        List<String> list = zkClient.getChildren("/shadow-lock")
                .stream()
                .sorted()
                .map(p -> "/shadow-lock/" + p)
                .collect(Collectors.toList());
        // 添加上一个节点变更 监听
        String firstPath = list.get(0);
        if(firstPath.equals(lockNode.getPath())){
            lockNode.setActive(true);
        }else {
            String upNodePath = list.get(list.indexOf(lockNode.getPath()) - 1);
            zkClient.subscribeDataChanges(upNodePath, new IZkDataListener() {
                @Override
                public void handleDataChange(String dataPath, Object data) throws Exception {

                }

                @Override
                public void handleDataDeleted(String dataPath) throws Exception {
                    // 事件处理 与 心跳 在同一个线程，如Debug时占有太多时间，将导致本节点被删除，从而影响锁逻辑
                    System.out.println("节点删除：" + dataPath);
                    Lock lock = tryActiveLock(lockNode);
                    synchronized (lockNode){
                        if(lockNode.isActive()){
                            lockNode.notify();
                        }
                    }
                    zkClient.unsubscribeDataChanges(upNodePath,this);
                }
            });

        }

        return lockNode;
    }
    // 3.释放锁
    public void unlock(Lock lock){
        if(lock!=null && lock.isActive()){
            zkClient.delete(lock.getPath());
        }
    }

    
    // 创建临时节点
    public Lock createLockNode(String lockId){
        String path = zkClient.createEphemeralSequential("/shadow-lock/" + lockId, "w");
        Lock lock = new Lock();
        lock.setActive(false);
        lock.setLockId(lockId);
        lock.setPath(path);
        return lock;
    }


}

class Lock{
    private String lockId;//
    private String path; // 节点path
    private boolean active; // 是否激活

    public Lock(String lockId, String path) {
        this.lockId = lockId;
        this.path = path;
    }

    public Lock() {
    }

    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}