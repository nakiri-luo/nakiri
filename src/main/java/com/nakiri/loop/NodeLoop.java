package com.nakiri.loop;

import com.nakiri.entity.MyListNode;
import lombok.ToString;

/**
 * 只能通过传入优先级进行构造此类
 * @author NAKIRI
 */

public class NodeLoop {
    public MyListNode loopHead;
    public NodeLoop nextNode;

    public NodeLoop(int lv){
        loopHead = new MyListNode();
        loopHead.setNext(null);
        loopHead.setLv(lv);
        loopHead.setTime(-1);
        nextNode = null;
    }

    public boolean isLoopEmpty(){
        return loopHead == null;
    }

    public int lengthLoop(){
        int length;
        MyListNode use = loopHead;
        for (length = 1;use.getNext() != null;length++){
            use = use.getNext();
        }
        return length;
    }

    public boolean addLoopNode(String text,double time){
        int num = lengthLoop();
        MyListNode newNode = new MyListNode();

        newNode.setNum(num);
        newNode.setText(text);
        newNode.setTime(time);
        newNode.setLv(loopHead.getLv());
        newNode.setNext(loopHead.getNext());

        loopHead.setNext(newNode);

        return true;
    }

    public MyListNode deleteLoopNode(int num){
        MyListNode use = loopHead;
        MyListNode deleted = null;
        if(isLoopEmpty()){
            return null;
        }
        while (use.getNext().getNum() != num){
            use = use.getNext();
            if (use.getNext() == null){
                break;
            }
        }
        if (use.getNext() == null){
            return null;
        }
        deleted = use.getNext();
        use.setNext(use.getNext().getNext());
        return deleted;
    }

    /**
     * 得到打印在文本框上的文本
     */
    public String getText(){
        StringBuilder text = new StringBuilder();
        MyListNode use = loopHead.getNext();
        while (use != null) {
            String add =  use.getNum() + ".\t" + use.getText() + "\t\t" + use.getTime() + "小时\t任务优先级" + use.getLv() + "\n";
            text.append(add);
            use  = use.getNext();
        }

        return text.toString();
    }

    public boolean cleanLoop(MyListNode head){
        head = loopHead;
        loopHead = null;
        return true;
    }

    public double getAllTime(){
        double time = 0;
        MyListNode use = loopHead.getNext();
        while (use != null){
            time = time + use.getTime();
            use = use.getNext();
        }

        return time;
    }
}
