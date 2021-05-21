package com.nakiri;

import com.nakiri.entity.MyListNode;
import com.nakiri.loop.NodeLoop;
import com.nakiri.queue.NodeQueue;
import com.nakiri.struct.NodeStruct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author NAKIRI
 */

public class Window extends JFrame {
    private static NodeLoop l1;
    private static NodeLoop l2;
    private static NodeLoop l3;
    private static NodeLoop l4;
    private static NodeQueue queue;
    private static NodeStruct struct;


    public static void main(String[] args) {
        new W();
    }

    static class W {
        JFrame jFrame = new JFrame("任务清单");
        Container container = jFrame.getContentPane();

        final JLabel  title = new JLabel("任务清单");

        final JTextField lv = new JTextField("优先级编号",7);
        final JTextField text = new JTextField("任务内容",50);
        final JTextField time = new JTextField("预计时长", 5);

        final JButton b = new JButton("确定");

        final  JTextField dNum = new JTextField("删除任务标号",10);

        final JButton deleteB = new JButton("删除");

        JTextArea jta = new JTextArea("今日任务清单",50,30);
        JScrollPane jsp = new JScrollPane(jta);

        JPanel cent = new JPanel();
        final JButton out = new JButton("导出历史已完成任务清单");


        public W(){
            l1 = new NodeLoop(1);
            l2 = new NodeLoop(2);
            l3 = new NodeLoop(3);
            l4 = new NodeLoop(4);

            queue = new NodeQueue();
            queue.addQueueNode(l1);
            queue.addQueueNode(l2);
            queue.addQueueNode(l3);
            queue.addQueueNode(l4);
            struct = new NodeStruct();

            jFrame.setLayout(new BorderLayout());
            //使用边界布局管理器

            jta.setLineWrap(true);
            jta.setBounds(15,50,700,400);

            b.addActionListener(new AddListener());
            deleteB.addActionListener(new DeleteListener());
            out.addActionListener(new TxtListener());

            cent.add(lv);
            cent.add(text);
            cent.add(time);
            cent.add(b);

            cent.add(dNum);
            cent.add(deleteB);

            cent.add(jta);

            container.add(BorderLayout.NORTH,title);
            container.add(BorderLayout.CENTER,cent);
            container.add(BorderLayout.SOUTH,out);


            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setBounds(0, 0, 800, 600);
            jFrame.setVisible(true);
        }

        class AddListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                //得到优先级
                String sLv = W.this.lv.getText();
                int lv = Integer.parseInt(sLv);
                //得到内容
                String text = W.this.text.getText();
                //得到预估时间
                String sTime = W.this.time.getText();
                double time = Double.parseDouble(sTime);

                switch (lv){
                    case 1: l1.addLoopNode(text,time);break;
                    case 2: l2.addLoopNode(text,time);break;
                    case 3: l3.addLoopNode(text,time);break;
                    case 4: l4.addLoopNode(text,time);break;
                    default: break;
                }
                if (queue.warnTimeOut()){
                    JOptionPane.showMessageDialog(null, "任务时常超过10小时！", "过劳警告", JOptionPane.INFORMATION_MESSAGE);
                }
                jta.setText(queue.queueText());
            }
        }

        class DeleteListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                //得到编号
                String sNum = W.this.dNum.getText();
                int num = Integer.parseInt(sNum);

                MyListNode use = queue.queueHead.nextNode.deleteLoopNode(num);
                if (use != null){
                    struct.addStructNode(use);
                }else {
                    JOptionPane.showMessageDialog(null, "未找到当前优先级该编号的任务！", "错误警告", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(queue.queueHead.nextNode.loopHead.getNext() == null){
                    NodeLoop addLoopHead = queue.deleteQueueNode();
                    struct.addStructNode(addLoopHead.loopHead);
                }
                jta.setText(queue.queueText());
                if (queue.isQueueEmpty()){
                    struct.addStructNode(queue.queueHead.loopHead);
                }
            }
        }
    }


    static class TxtListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            try {
                struct.creatTxt();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
