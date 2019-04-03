package p4;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class wordsta extends JFrame 
{
	//确定按钮
	private static JButton inputButton; 
	//关闭按钮
	private static JButton closeButton;
	//版面
	private static JLabel input; 
	//框架
	private static JFrame statistics; 
	//输入查询词频的单词
	private static JTextField word2;  
	private static JLabel word;
	
	/**初始统计指定单词词频界面*/
	public wordsta(final Map<String, Integer> maps)
	{ 
   	    Font font =new Font("楷体", Font.PLAIN, 20); 
	    statistics=new JFrame("统计指定单词词频");
		statistics.setSize(500, 400);
		input=new JLabel();
		
		word=new JLabel("输入要统计的字符串，用空格分割:");
		word.setBounds(20, 80, 150, 50);
		 
		inputButton=new JButton("确定");      
		inputButton.setBounds(100, 250, 100, 50);
		inputButton.setFont(font);
		
		closeButton=new JButton("取消");
		closeButton.setBounds(300, 250, 100, 50);
		closeButton.setFont(font);
		
		word2=new JTextField();
		word2.setBounds(150, 80, 250, 40);
 
		input.add(word2);		
		input.add(word);
		input.add(inputButton);
		input.add(closeButton);
		
		statistics.add(input);
		statistics.setVisible(true);
		statistics.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		statistics.setLocation(300,400);
		 /**关闭界面*/
		closeButton.addActionListener(new ActionListener()
	    {
        public void actionPerformed(ActionEvent event)
        {
           if (event.getSource()==closeButton)
           {
        	   statistics.dispose(); 
           }
        }
     });
	/**查询并显示指定单词词频*/
	inputButton.addActionListener(new ActionListener()
	{
        public void actionPerformed(ActionEvent event)
        {
           if (event.getSource()==inputButton)
           {
        	   long start = System.currentTimeMillis();
        	   String word=word2.getText();
        	   if (!word.isEmpty())
        	   {		  
        			Map<String,Integer> map = new TreeMap<String, Integer>();  
					String[] input= word.split(" ");
				    int i;
				    String print = "";
				    for (i=0; i<input.length; i++) 
				    {
				       	for (Entry<String, Integer> entry : maps.entrySet()) 
				       	{ 
				       		if (input[i].equals(entry.getKey()))
				        	{
				        		map.put(entry.getKey(), entry.getValue());
				        		print += entry.getKey() + ":" + entry.getValue()+"    "; 
				        		break;
				        	}
				         } 
				     }
				    long time=System.currentTimeMillis() - start;
				   	JOptionPane.showConfirmDialog(null,print+"\n"+"所用时间："+(System.currentTimeMillis() - start)+"ms","结果",JOptionPane.DEFAULT_OPTION);
            				 plot histogram=new plot(map,input.length);
				   	histogram.setVisible(true);
					}
				else
				{
				   	JOptionPane.showConfirmDialog(null, "请输入要查询的信息！","提示",JOptionPane.DEFAULT_OPTION);					
				} 
        	   
           }
        }
     });
	}
}