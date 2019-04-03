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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class main extends JFrame 
{
	//高频词输出按钮
	private static JButton highFrequencyButton; 
	//统计指定单词个数按钮
	private static JButton wordCountButton;
	//词频写入文件按钮
	private static JButton printFile; 
	//行数单词数统计按钮
	private static JButton lineWordCount; 
	//版面
	private static JLabel input;
	//框架
	private static JFrame statistics; 
	//文件名
	private static JTextField file2; 
	private static JLabel file ;
	
	public static FileReader fr;
	static BufferedReader br;
	//行数
	static int rowNumber=0; 
	//单词数
	static int wordNumber=0;
	//统计行数单词数所用时间
	static long time;
	/**初始化登陆界面*/
	public main ()
	{ 
		//设置字体
   	    Font font =new Font("楷体", Font.PLAIN, 30); 
   	    statistics=new JFrame("英文文本统计分析软件");
		statistics.setSize(500, 600);
		input=new JLabel();
		
		file=new JLabel("输入文件名:");
		file.setBounds(20, 40, 100, 50);
		
		highFrequencyButton=new JButton("查看前N个高频词");    
		highFrequencyButton.setBounds(20, 150, 400, 50);
		highFrequencyButton.setFont(font);
		
		wordCountButton=new JButton("统计指定单词词频");
		wordCountButton.setBounds(20, 250, 400, 50);
		wordCountButton.setFont(font);

		printFile=new JButton("写入文件");
		printFile.setBounds(20, 450, 400, 50);
		printFile.setFont(font);
		
		lineWordCount=new JButton("统计行数单词数");
		lineWordCount.setBounds(20, 350, 400, 50);
		lineWordCount.setFont(font);

		//加入文本框
		file2 = new JTextField();
		file2.setBounds(150, 40, 250, 40);
		
		input.add(file);
		input.add(file2);
	 
		input.add(highFrequencyButton);
		input.add(wordCountButton);
		input.add(printFile);
		input.add(lineWordCount);
		
		statistics.add(input);
		statistics.setVisible(true);
		statistics.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		statistics.setLocation(300,400);

	}
	/**从指定文件读入单词并统计词频*/
    static void FileName(final Map<String, Integer> map) throws IOException
    {
		String file=file2.getText();	
		if (file.isEmpty())
		{
			JOptionPane.showConfirmDialog(null, "请输入文件！","提示",JOptionPane.DEFAULT_OPTION);
		}
		else 
		{
			try 
			{
				fr= new FileReader(file);
			} 
			catch (FileNotFoundException e2) 
			{
				e2.printStackTrace();
			}	
	    	BufferedReader b = new BufferedReader(fr);
	            String value= b.readLine();
	            long start = System.currentTimeMillis();
	            rowNumber=0;
	            while (value!= null) 
	            {
	            	//处理标点符号
	            	String[] words = value.split("[【】、.。,\"“”!--;:?\'\\] ]"); 
	            	for (int i = 0; i < words.length; i++) 
	            	{
	            		//将大写字母转换为小写字母
	                      String key = words[i].toLowerCase();
	                		if (key.length() > 0) 
	                		{
	                      		if (!map.containsKey(key)) 
	                      		{
	                      			wordNumber++;
	                          		map.put(key, 1);
	                          		} 
	                      		else 
	                      		{ 
	                      			int k = map.get(key)+1;// 如果不是第一次出现，就把k值++
	                                map.put(key, k);
	                          		}
	                      		}
	                  		} 
	                value = b.readLine();
	                rowNumber++;
	            }
	            long end=System.currentTimeMillis();
	            time=end-start;
		}   
	}
	public static void main(String[] args)
	{
		Map<String, Integer> map = new TreeMap<String, Integer>();
	    main main = new main();
	    /**打开查看前N个高频词界面*/
		highFrequencyButton.addActionListener(new ActionListener()
		{
        public void actionPerformed(ActionEvent event)
        {
           if (event.getSource()==highFrequencyButton)
           {  
			   try 
			   {
				FileName(map);
			    } 
			   catch (IOException e) 
			   {
				// TODO Auto-generated catch block
				e.printStackTrace();
			   }	 	    
        	   new hwords(map);
           }
        }
     });
		 /**打开查询指定单词词频界面*/
		wordCountButton.addActionListener(new ActionListener()
		{
        public void actionPerformed(ActionEvent event)
        {
           if (event.getSource()==wordCountButton)
           {
        	   try 
        	   {
				FileName(map);
			   } 
        	  catch (IOException e) 
        	   {
				// TODO Auto-generated catch block
				e.printStackTrace();
			   }
        	 
        	   new wordsta(map);  
           }
        }
     });
		 
		 /**执行词频写入文件功能*/
		printFile.addActionListener(new ActionListener()
		{
        public void actionPerformed(ActionEvent event)
        {
           if (event.getSource()==printFile)
           {
        	   try 
        	   {
				FileName(map);
			    } 
        	   catch (IOException e) 
        	   {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }      
        	outputresult rs = new outputresult(); 
			try 
			{
				rs.PrintToF(map);
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	JOptionPane.showConfirmDialog(null, "写入文件成功，在result.txt中查看！","提示",JOptionPane.DEFAULT_OPTION);	   
           }
        }
     });
		 /**查看文本行数和单词数*/
		lineWordCount.addActionListener(new ActionListener()
		{
        public void actionPerformed(ActionEvent event)
        {
           if (event.getSource()==lineWordCount)
           {
        	   try 
        	   {
				FileName(map);
			   } 
        	   catch (IOException e) 
        	   {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
        	   JOptionPane.showConfirmDialog(null,"总行数："+rowNumber+"总单词数："+wordNumber+"\n"+"所用时间："+time+"ms","结果",JOptionPane.DEFAULT_OPTION);
           }
        }
     });
	  
	}
}