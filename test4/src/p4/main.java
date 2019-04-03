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
	//��Ƶ�������ť
	private static JButton highFrequencyButton; 
	//ͳ��ָ�����ʸ�����ť
	private static JButton wordCountButton;
	//��Ƶд���ļ���ť
	private static JButton printFile; 
	//����������ͳ�ư�ť
	private static JButton lineWordCount; 
	//����
	private static JLabel input;
	//���
	private static JFrame statistics; 
	//�ļ���
	private static JTextField file2; 
	private static JLabel file ;
	
	public static FileReader fr;
	static BufferedReader br;
	//����
	static int rowNumber=0; 
	//������
	static int wordNumber=0;
	//ͳ����������������ʱ��
	static long time;
	/**��ʼ����½����*/
	public main ()
	{ 
		//��������
   	    Font font =new Font("����", Font.PLAIN, 30); 
   	    statistics=new JFrame("Ӣ���ı�ͳ�Ʒ������");
		statistics.setSize(500, 600);
		input=new JLabel();
		
		file=new JLabel("�����ļ���:");
		file.setBounds(20, 40, 100, 50);
		
		highFrequencyButton=new JButton("�鿴ǰN����Ƶ��");    
		highFrequencyButton.setBounds(20, 150, 400, 50);
		highFrequencyButton.setFont(font);
		
		wordCountButton=new JButton("ͳ��ָ�����ʴ�Ƶ");
		wordCountButton.setBounds(20, 250, 400, 50);
		wordCountButton.setFont(font);

		printFile=new JButton("д���ļ�");
		printFile.setBounds(20, 450, 400, 50);
		printFile.setFont(font);
		
		lineWordCount=new JButton("ͳ������������");
		lineWordCount.setBounds(20, 350, 400, 50);
		lineWordCount.setFont(font);

		//�����ı���
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
	/**��ָ���ļ����뵥�ʲ�ͳ�ƴ�Ƶ*/
    static void FileName(final Map<String, Integer> map) throws IOException
    {
		String file=file2.getText();	
		if (file.isEmpty())
		{
			JOptionPane.showConfirmDialog(null, "�������ļ���","��ʾ",JOptionPane.DEFAULT_OPTION);
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
	            	//���������
	            	String[] words = value.split("[������.��,\"����!--;:?\'\\] ]"); 
	            	for (int i = 0; i < words.length; i++) 
	            	{
	            		//����д��ĸת��ΪСд��ĸ
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
	                      			int k = map.get(key)+1;// ������ǵ�һ�γ��֣��Ͱ�kֵ++
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
	    /**�򿪲鿴ǰN����Ƶ�ʽ���*/
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
		 /**�򿪲�ѯָ�����ʴ�Ƶ����*/
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
		 
		 /**ִ�д�Ƶд���ļ�����*/
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
        	JOptionPane.showConfirmDialog(null, "д���ļ��ɹ�����result.txt�в鿴��","��ʾ",JOptionPane.DEFAULT_OPTION);	   
           }
        }
     });
		 /**�鿴�ı������͵�����*/
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
        	   JOptionPane.showConfirmDialog(null,"��������"+rowNumber+"�ܵ�������"+wordNumber+"\n"+"����ʱ�䣺"+time+"ms","���",JOptionPane.DEFAULT_OPTION);
           }
        }
     });
	  
	}
}