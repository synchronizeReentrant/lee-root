package com.lee.demo.zk.util;

public abstract class AbstractLifecycle {

    /**
     *Note:
     *
     *
     */

  protected  volatile boolean isStart = false;

  public AbstractLifecycle(){}

  public void start(){
     if(!this.isStart){
         this.doStart();
         this.isStart = true;
     }

  }
  public boolean isStarted(){
        return this.isStart;
  }
  protected abstract void doStart();
}
