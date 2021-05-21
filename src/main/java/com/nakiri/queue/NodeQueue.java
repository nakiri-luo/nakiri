package com.nakiri.queue;

import com.nakiri.loop.NodeLoop;

/**
 *
 * @author NAKIRI
 */

public class NodeQueue {
    public NodeLoop queueHead;

    public NodeQueue(){
        queueHead = new NodeLoop(-1);
        queueHead.loopHead.setTime(-2);
    }

    public boolean isQueueEmpty(){
        return queueHead.nextNode == null;
    }

    public boolean addQueueNode(NodeLoop newNode){
        NodeLoop use = queueHead;
        while (use.nextNode != null){
            use = use.nextNode;
        }
        use.nextNode = newNode;
        return true;
    }

    public NodeLoop deleteQueueNode(){
        NodeLoop use = queueHead.nextNode;
        queueHead.nextNode = queueHead.nextNode.nextNode;
        return use;
    }

    /**
     *
     * @see "设置10小时以上为过劳状态"
     */
    public boolean warnTimeOut(){
        double allTime = 0;
        NodeLoop use = queueHead.nextNode;
        while(use != null){
            allTime = allTime + use.getAllTime();
            use = use.nextNode;
        }
        return allTime >= 10;
    }

    public String queueText(){
        NodeLoop use = queueHead.nextNode;
        StringBuilder text = new StringBuilder();
        while (use != null){
            text.append(use.getText());
            text.append("\n");
            use = use.nextNode;
        }
        return text.toString();
    }
}
