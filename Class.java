package vddalthdt1;



import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.lang.*;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.*;
import javafx.util.Duration;
//import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
//import javax.swing.text.html.AccessibleHTML.TableElementInfo.TableAccessibleContext;
import javax.swing.table.TableColumn;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.awt.Font;


public class Class {
	static Object[][] data={};
	static File Fs;
	static JTextField jt;
	
	static JTable TB=new JTable();
	static JButton JB;
	static String endn="0:00";
	static JFrame JF;
	static String time1="0:00";
	
	static String time2=endn;
	static JButton JB1;
	
	static File Fn;
	static int n=0;
	static MediaPlayer play;
 static int rows,cols;
 static String nha;
	static JScrollPane cao;
	static Media me;
	static int dg=0;
	static int nghia=0;
	static Thread th1; 
	static int ix=0;
	static int jx=0;
	
	public static void main(String[] args) {
		new JFXPanel();
		
		Class h =new Class();
		
		h.chay();
	

	}
	
	private static void chay() {
	
		clit();
		
		JB=new JButton("Excel");
		JB.setBounds(299, 327, 86, 33);
		JF=new JFrame("nghia");
		JB1=new JButton("Music");
		JB1.setBounds(200, 327, 86, 33);
		JF.setVisible(true);
		JF.setSize(411, 428);
		JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Excel();
		table();
		JF.getContentPane().setLayout(null);
		JF.getContentPane().add(JB1);
		JF.getContentPane().add(JB);
		JF.getContentPane().add(cao);
		jt= new JTextField();
		jt.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jt.setBounds(0, 231, 395, 42);
		jt.setText("...");
		JF.getContentPane().add(jt);
		nutNhac();
		nutExcel();
		

	}
	// lay ja tri cho data
    private static void Excel() {
    
		if(Fs==null) return;
		
		try {
			Workbook wk= Workbook.getWorkbook(Fs);
			Sheet sh=wk.getSheet(0);

			int k = sh.getRows();
			rows=k-1;
			System.out.println(">>>>"+rows+"  "+k+"nghiahaih");
			 cols=sh.getColumns();
			data =new Object[rows+1][cols];
			System.out.printf("%d   %d",rows,cols);
			for(int i=0;i<rows+1;i++)
				for(int j=0;j<cols;j++)
				{
					Cell cell ;
					cell = sh.getCell(j, i);
					
					String m = cell.getContents();
					
					if(m!=null)
					data[i][j] = (m);
				//	System.out.printf("    +%s+  ",data[i][j]);
					
					
					
				}
	
		
		} catch (BiffException | IOException e) {
		    System.out.println("kho lay dc");
		}

	}
    private static void table() {
    	//String[] ten={"cot","loi"};
	   TB.setFont(new Font("Tahoma", Font.PLAIN, 15));
	  //  TB.setForeground(Color.BLACK);
	  //  TB.setBackground(Color.WHITE);
		
	    TB.setModel(new DefaultTableModel(
	    	new Object[][] {
	    		{null, null},
	    	},
	    	new String[] {
	    		"cot", "loi"
	    	}
	    ));
	    TB.getColumnModel().getColumn(0).setPreferredWidth(51);
	    TB.getColumnModel().getColumn(1).setPreferredWidth(281);
	   
	    
	    
		cao = new JScrollPane(TB);
		cao.setBounds(0, 0, 385, 181);
		
       
        
	}
    public static void clit()
    {
    	 
		    	TB.addMouseListener(new MouseAdapter()
		    	{ 
		            @Override
		            public void mouseClicked(MouseEvent e) {
		            	int row = TB.rowAtPoint(e.getPoint());
		    			int col = TB.columnAtPoint(e.getPoint());
		    	    if(row==(rows-1))
		    	    {
		    	    	time1=(String)data[row][col];
		    	    	time2=endn;
		    	    	//System.out.println(">>>>>");
		    	    	
		    	    }
		    	    else
		    	    {
		    			time1=(String)data[row][col];
		    			time2=(String)data[row+1][col];
		    			System.out.printf("%d   ",row);
		    			System.out.printf("%d",rows);
		    	    }
		    	  if(dg==1) play.stop();
		  //  	    ix=row;
  	         //         jx=0;
		    	            
		    	            	
		    	            	if(th1.isAlive())
		    	            	{
		    	            		System.out.println("nghiahaihi");
		    	            		
		 		    	            
		 		    	          
		    	            	try {
			    	            		th1.interrupt();
			    	            		ix=rows+1;
			    	  	                 
			    	  	                  
											th1.join();
										} catch (InterruptedException e1) {
											
										}
		 		    	           
		    	            	}
		    	            	if(th1.isAlive()){}
		    	            	else
		    	            	{
		    	            		System.out.println("ok");
		    	            		 ix=row;
		    	  	                 
		    	  	                  next();
		    	            		
		    	            	}
		    	            
		    	          
                       
		    	
		    	
  			   
		    	
					    	  
		    			/// ..::play nhac::..
		    					    			
		             
		            }}
		            
		            );

      }
    
    
    public static void Nhac()
    {
    	if(Fn==null) return;
    	//Fn=new File("D:\\nghia.mp3");
    	 nha ="file:///"+(""+Fn).replace("\\", "/").replaceAll(" ", "%20");
    	
    	//String F = "file:///" + ("" + link).replace("\\", "/").replaceAll(" ", "%20");
    	
    	System.out.println("nghia1");
		me =new Media(nha);
    	 play =new MediaPlayer(me);
    	System.out.println("nghia");
    	
	    Duration start = new Duration(doi(time1));
		Duration stop = new Duration(doi(time2));
		play.setStartTime((Duration) start);
		play.setStopTime((Duration) stop);
	     dg=1;
		 play.play();
		
		 th1 =new Thread(new Runnable() {
		    	
				@Override
				public void run() {
					nghia=1;
					
					String timee1;
	    	    	String timee2;
					 for(int i=ix;i<rows;i++)
					 {
			    		 if(ix>rows) return;
			    			   if(i==(rows-1))
					    	    {
					    	    	timee1=(String)data[i][0];
					    	    	timee2=endn;
					    	    	
					    	    	System.out.println(">>>>>");
					    	    	
					    	    }
					    	    else
					    	    {
					    	    	
					    			timee1=(String)data[i][0];
					    			timee2=(String)data[i+1][0];
					    			//System.out.printf("%d   ",i);
					    			//System.out.printf("%d",rows);
					    	    }
					    	  
						    			
			    			  System.out.println("next");
			    				jt.setText((String)data[i][1]); 
			    				 try {
			    					 
			    					
			 						Thread.sleep((doi(timee2)-doi(timee1)));
			 					} catch (InterruptedException e) {
			 						
			 					}
			    		 }
					 }
					
				
			});
	    th1.start();
	   
		 
		
      
    	
    }
    public static void Xoai(int h)
    {
    	
    	if(Fn==null) return;
    	//Fn=new File("D:\\nghia.mp3");
    	
    	System.out.println("da bam vao");
    	 nha ="file:///"+(""+Fn).replace("\\", "/").replaceAll(" ", "%20");
    	
    	//String F = "file:///" + ("" + link).replace("\\", "/").replaceAll(" ", "%20");
    	
    	System.out.println("nghia1");
		me =new Media(nha);
    	 play =new MediaPlayer(me);
    	 String time =(String)data[h][0];
		String endd=endn;
    	
	    Duration start = new Duration(doi(time));
		Duration stop = new Duration(doi(endd));
		play.setStartTime((Duration) start);
		play.setStopTime((Duration) stop);
	     dg=1;
		 play.play();
		
    }
    public static void next()
    {
    	
    	Xoai(ix);
    	 th1 =new Thread(new Runnable() {
    	
			@Override
			public void run() {
				nghia=1;
				
				String timee1;
    	    	String timee2;
				 for(int i=ix;i<rows;i++)
				 {
					 if(ix>rows) return;
		    			   if(i==(rows-1))
				    	    {
				    	    	timee1=(String)data[i][0];
				    	    	timee2=endn;
				    	    	
				    	    	System.out.println(">>>>>");
				    	    	
				    	    }
				    	    else
				    	    {
				    	    	
				    			timee1=(String)data[i][0];
				    			timee2=(String)data[i+1][0];
				    			//System.out.printf("%d   ",i);
				    			//System.out.printf("%d",rows);
				    	    }
				    	  
					    			
		    			    System.out.println("next");
		    			    jt.setText((String)data[i][1]); 
		    				 try {
		    					 
		    					
		 						Thread.sleep((doi(timee2)-doi(timee1)));
		 					} catch (InterruptedException e) {
		 						
		 					}
		    		 
				 }
				
			}
		});
    th1.start();
   
    }
   
	public static int doi(String time)
    {
    	String[] par =time.split(":");
    	double phut=Double.parseDouble(par[0]);
    	double giay=Double.parseDouble(par[1]);
    	return (int)(phut*60000+giay*1000);
    }
	public static void nutExcel()
	{
			JB.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							JFileChooser choose =new JFileChooser();
							choose.showOpenDialog(null);
							Fs = choose.getSelectedFile();
							 String ae="";
								
								for (String retval: Fs.toString().split("\\.")) {
									       ae=retval;
									       
									    }
								
								if(ae.equals("XLS")!=true )
								{
									if(ae.equals("xls")!=true)
									Fs=null;
								}
							
							if(Fs!=null)
							{
								Excel();
							    endn=(String)data[rows][0];
							    time2=endn;
							    System.out.println("    c"+endn+"c   ");
							}
							if(Fn!=null&&Fs!=null)
							{
								
								
								
								ix=0;
								
								Nhac();
							}
							
						
						   String[] ten={"cot","loi"};
						
						 
						   
						   
						    TB.setModel(new DefaultTableModel(data, ten));
						   // cao = new JScrollPane(TB);
						    TB.getColumnModel().getColumn(0).setPreferredWidth(51);
						    TB.getColumnModel().getColumn(1).setPreferredWidth(281);
						}
					}); 
	}
    public static void nutNhac()
    {
    	JB1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser cs =new JFileChooser();
				cs.showOpenDialog(null);
				Fn = cs.getSelectedFile();
				
				
	             String an="";
				
				for (String retval: Fn.toString().split("\\.")) {
					       an=retval;
					       
					    }
				
				if(an.equals("MP3")!=true)
				{
					if(an.equals("mp3")!=true)
					Fn=null;
				}
				//System.out.println(""+Fn.toString());
				if(Fn!=null&&Fs!=null)
				{
					
					///jfajeghjkafglsghflak
					ix=0;
					Nhac();
				}
							}
			
		});
    }
    

// the end
 }

