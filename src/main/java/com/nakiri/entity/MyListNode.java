package com.nakiri.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NAKIRI
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyListNode {
    /**
     * num:任务编号
     * text:任务内容
     * time:任务耗时
     * lv:任务优先级
     *next:本节点的下一个节点
     */
    private int num;
    private String text;
    private double time;
    private int lv;
    private MyListNode next;

    public String  toString1(){
        return getNum() + ".\t" + getText() + "\t\t" + getTime() + "小时\t任务优先级" + getLv() + "\n";
    }
}
