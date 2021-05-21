package com.nakiri.struct;

import com.nakiri.entity.MyListNode;
import lombok.NoArgsConstructor;

import java.io.*;

/**
 * 用于日志功能的一个类
 * @author NAKIRI
 */

public class NodeStruct {
    MyListNode head;

    public NodeStruct(){
        head = new MyListNode();
        head.setTime(-1);
        head.setNext(null);
    }

    private boolean isStructEmpty(MyListNode node){
        return node == null;
    }

    public boolean addStructNode(MyListNode newNode){
        newNode.setNext(null);
        if (head.getNext() == null){
            head.setNext(newNode);
        } else {
            MyListNode useNode = new MyListNode();
            useNode = head.getNext();
            while(useNode.getNext() != null){
                useNode = useNode.getNext();
            }
            useNode.setNext(newNode);
        }
       return true;
    }

    public MyListNode deleteStructNode(){
        if (isStructEmpty(head.getNext())){
            return null;
        }

        MyListNode use = head.getNext();
        head.setNext(head.getNext().getNext());
        return use;
    }

    /**
     * 文件操作
     */
    public void creatTxt() throws IOException {
        File file = new File("src\\ListDairy.txt");
        Writer fw = null;
        //创建输入流
        if(!file.exists()){
            try {
                file.createNewFile();
                fw = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(file),"GB2312"
                        )
                );

                //写入操作
                while(head.getNext() == null) {
                    MyListNode use = deleteStructNode();
                    if(use.getTime() == -1){
                        fw.write("---------------------------------------------\n");
                    }else {
                        fw.write(use.toString1());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(fw != null){
                    try {
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else {
            try {
                fw = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(file),"GB2312"
                        )
                );

                //写入操作
                while(head.getNext() != null) {
                    MyListNode use = deleteStructNode();
                    if(use.getTime() == -1){
                        fw.write("-------------------------------------\n");
                    }else if(use.getTime() == -2){
                        fw.write("=====================================\n");
                    } else {
                        fw.write(use.toString1());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(fw != null){
                    try {
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
