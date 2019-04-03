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
	//ȷ����ť
	private static JButton inputButton; 
	//�رհ�ť
	private static JButton closeButton;
	//����
	private static JLabel input; 
	//���
	private static JFrame statistics; 
	//�����ѯ��Ƶ�ĵ���
	private static JTextField word2;  
	private static JLabel word;
	
	/**��ʼͳ��ָ�����ʴ�Ƶ����*/
	public wordsta(final Map<String, Integer> maps)
	{ 
   	    Font font =new Font("����", Font.PLAIN, 20); 
	    statistics=new JFrame("ͳ��ָ�����ʴ�Ƶ");
		statistics.setSize(500, 400);
		input=new JLabel();
		
		word=new JLabel("����Ҫͳ�Ƶ��ַ������ÿո�ָ�:");
		word.setBounds(20, 80, 150, 50);
		 
		inputButton=new JButton("ȷ��");      
		inputButton.setBounds(100, 250, 100, 50);
		inputButton.setFont(font);
		
		closeButton=new JButton("ȡ��");
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
		 /**�رս���*/
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
	/**��ѯ����ʾָ�����ʴ�Ƶ*/
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
				   	JOptionPane.showConfirmDialog(null,print+"\n"+"����ʱ�䣺"+(System.currentTimeMillis() - start)+"ms","���",JOptionPane.DEFAULT_OPTION);
            				 plot histogram=new plot(map,input.length);
				   	histogram.setVisible(true);
					}
				else
				{
				   	JOptionPane.showConfirmDialog(null, "������Ҫ��ѯ����Ϣ��","��ʾ",JOptionPane.DEFAULT_OPTION);					
				} 
        	   
           }
        }
     });
	}
}