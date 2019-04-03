package p4;

import java.awt.Font;
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
import javax.swing.JTextField;

public class hwords  extends JFrame 
{
	//确定按钮
	private static JButton inputButton; 
	//关闭按钮
	private static JButton closeButton;
	private static JLabel input; 
	private static JFrame statistics; 
	//输入需要查看前N个单词
	private static JTextField number2; 
	private static JLabel number;
	/**初始化输出词频前N个单词界面*/
	public hwords (final Map<String, Integer> maps)
	{ 
   	Font font =new Font("楷体", Font.PLAIN, 20); 
	    statistics=new JFrame("输出词频前N个单词");
		statistics.setSize(500, 400);
		input=new JLabel();
		
		number=new JLabel("输入高频词个数:");
		number.setBounds(20, 100, 150, 100);
		
		inputButton=new JButton("确定");      
		inputButton.setBounds(100, 250, 100, 50);
		inputButton.setFont(font);

		closeButton=new JButton("取消");
		closeButton.setBounds(300, 250, 100, 50);
		closeButton.setFont(font);
 
		number2=new JTextField();
		number2.setBounds(150, 120, 250, 40);
		
		input.add(number);
		input.add(number2);
		input.add(inputButton);
		input.add(closeButton);
		
		statistics.add(input);
		statistics.setVisible(true);
		statistics.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		statistics.setLocation(300,400);
		 /**关闭当前界面*/
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
    inputButton.addActionListener(new ActionListener()
		{
            public void actionPerformed(ActionEvent event)
            {
               if (event.getSource()==inputButton)
               {
            	   long start = System.currentTimeMillis();
            	   String n=number2.getText();
            	   int wordNum=Integer.parseInt(n);
            	   String print = "";
            	   if (!n.isEmpty())
            	   {		 
            		   String str = "above, over,in,of,at,on,behind,during,from,into, onto,inside,outside,to,without,"
            		   		+ "throughout,out,she,he,its,we,them,your,i,you,my,myeself,a,an,the";
           		       String s[] = str.split(",");  
           		        
           		       ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(maps.entrySet());
           	           //按词频排序
           		       Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() 
           		       {
           	                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
           	                {
           	                    return o2.getValue() - o1.getValue(); 
           	                }
           	           });
           		       int j=0;
           		       int i=0;
                       while (j<wordNum)
                       {
                			boolean flag = true ;
                			String tmpString = list.get(i).getKey();
                			for (String string : s) 
                			{	
                				if(tmpString.equals(string))
                				{
                					    flag = false;
                				 } 
                			}	
                		
                			if(flag)
                			{    
                			     print += list.get(i).getKey()+ ": " +list.get(i).getValue()+"    ";
                			     j++;
                			     
                			 }
                			i++;
                   		}
                       JOptionPane.showConfirmDialog(null,print+"\n"+"所用时间："+(System.currentTimeMillis() - start)+"ms","结果",JOptionPane.DEFAULT_OPTION);
            	   }
    			}
    			else
    			{
    				   	JOptionPane.showConfirmDialog(null, "请输入要查询的信息！","提示",JOptionPane.DEFAULT_OPTION);					
    			} 
            	    
            }
        });
    }
}