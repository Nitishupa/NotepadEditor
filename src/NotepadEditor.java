package notepadeditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class NotepadEditor implements ActionListener {
	JMenuItem neww;
	JMenuItem openn;
	JMenuItem savee;
	JMenuItem cut;
	JMenuItem copy;
	JMenuItem paste;
	JMenuItem font;
	JMenuItem  font_color;
	JMenuItem background_color;
	JFrame jf,Font_Frame;
	JTextArea area;
	File f;
	JComboBox Font_Family,Font_Size,Font_Style;
	JButton ok;
	NotepadEditor()
	{ 
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	
		}
	catch(Exception e)
	{
		
	}
		jf=new JFrame("*untitled- Notepad*") ;
		jf.setSize(400,400);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		
		JMenuBar jmenubar=new JMenuBar();
		JMenu file=new JMenu("File");
		jmenubar.add(file);
		
		JMenu edit=new JMenu("Edit");
		jmenubar.add(edit);
		jf.setJMenuBar(jmenubar);
		
		cut=new JMenuItem("Cut");
		cut.addActionListener(this);
		edit.add(cut);
		
		copy=new JMenuItem("Copy");
		copy.addActionListener(this);
		edit.add(copy);
		
		paste=new JMenuItem("Paste");
		paste.addActionListener(this);
		edit.add(paste);
		JMenu format=new JMenu("Format");
		jmenubar.add(format);
		
		font=new JMenuItem("Font");
		font.addActionListener(this);
		format.add(font);
		
		font_color=new JMenuItem("Font_Color");
		font_color.addActionListener(this);
		format.add(font_color);
		
		background_color=new JMenuItem("Background_color");
		background_color.addActionListener(this);
		format.add(background_color);
		
		
		neww=new JMenuItem("New");
		neww.addActionListener(this);
		file.add(neww);
		
		openn=new JMenuItem("Open");
		openn.addActionListener(this);
		file.add(openn);
		
		savee=new JMenuItem("Save");
		savee.addActionListener(this);
		file.add(savee);
		
		area=new JTextArea (); /////// text area 
		jf.add(area);
		
		JScrollPane scrollpane=new JScrollPane(area);
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jf.add(scrollpane);
		jf.setVisible(true);
		
	}
	       public static void main(String args[]) 
	       
	            {
	    	   
	    	  
	    	   	new NotepadEditor();                            //call constructor
	
	       	    }
	       @Override
	       public void actionPerformed(ActionEvent e)
	       {
	    	   			if (e.getSource()==neww)
	    	   				{
	    	   			 newFile();
	    	   				}
	    	   			 if (e.getSource()==openn)
	    	   			  {
	    	   				openFile();
	    	   			   }
	    	   			 if (e.getSource()==savee)
	    	   			 {
	    	   				 saveFile();
	                        }
	    	   			 if (e.getSource()==cut)
	    	   			 {
	    	   				 area.cut();
	                            }
	    	   			 if (e.getSource()==copy) 
	    	   			 {
	    	   				 area.copy();
	    	   				 
	    	   			 }
	    	   			 if(e.getSource()==paste)
	    	   			 {
	    	   				 area.paste();
	    	   			 }
	    	   			 if(e.getSource()==font)
	    	   			 {
	    	   			      openFontFrame();
	    	   			 }
	    	   			 if(e.getSource()==ok)
	    	   			 {
	    	   				setFontOnText();
	    	   				
	    	   			 }
	    	   			 if(e.getSource()==font_color)
	    	   			 {
	    	   				Color c=JColorChooser.showDialog(jf,"Choose Color", Color.BLACK) ;
	    	   				area.setForeground(c);
	    	   			 }
	       
	    	   			 if(e.getSource()==background_color)
	    	   			 {
	    	   				 Color c=JColorChooser.showDialog(jf, "choose Background color", Color.white);
	    	   				 area.setBackground(c);
	    	   			 }
	       }
	       
	       void openFile()
	       {
	    	   {
			           JFileChooser filechooser=new JFileChooser();                       // open ho jayegabut read ni hoga so
			           int result= filechooser.showOpenDialog(jf);
			           if (result==0) 
			           {
                         area.setText("");
			        	   f=filechooser.getSelectedFile();
			        	   jf.setTitle(f.getName());
			        	   try(FileInputStream fis= new FileInputStream(f))
					      {
						      int i;
						         while((i=fis.read())!=-1)
						              {
					                       	area.append(String.valueOf((char)i));
						                  }
					       }
			
			catch(Exception ee)
			{
				ee.printStackTrace();
			}
			
		
			           }
	    	   }
	       }
	       void setFontOnText()
	       {
	    	   String fontsize=(String)Font_Size.getSelectedItem();
  				String fontfamily=(String)Font_Family.getSelectedItem();
  				String fontstyle=(String)Font_Style.getSelectedItem();
  				int style=0;
  				if(fontstyle.equals("plain"))
  				{
  					style=0;
  					
  				}
  				else if(fontstyle.equals("bold"))
  				{
  					style=1;
  				}
  				else if(fontstyle.equals("italic"))
  				{
  					style=2;
  				}
  				Font fontt=new Font(fontfamily,style,Integer.parseInt(fontsize));
  				Font_Frame.setVisible(false);
  				area.setFont(fontt);
  				
	       }
	       void openFontFrame()
	       {
	    	  Font_Frame =new JFrame("Choose Font");
	    	  Font_Frame.setSize(500, 500);
	    	  Font_Frame.setLocationRelativeTo(jf);
	    	  Font_Frame.setVisible(true);
	    	  Font_Frame.setLayout(null);
	    	  String fonts[] =  GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	    	  
	    	  Font_Family=new JComboBox(fonts);
	    	  Font_Family.setBounds(50, 100, 100, 30);
	    	  Font_Frame.add(Font_Family);
	    	  
	    	  String []sizes= {"10","12","14","16","18","20","24","40"};
	    	  
	    	  Font_Size=new JComboBox(sizes);
	    	  Font_Size.setBounds(170, 100, 100, 30);
	    	  Font_Frame.add(Font_Size);
	    	  
	    	  String [] style= {"plain","Bold","italic"};
	    	  
	    	  Font_Style=new JComboBox(style);
	    	  Font_Style.setBounds(300, 100, 100, 30);
	    	  Font_Frame.add(Font_Style);
	    	  ok=new JButton("Ok");
	    	  ok.setBounds(180, 200, 100, 50);
	    	  Font_Frame.add(ok);
	    	  ok.addActionListener(this);
	    	 Font_Frame.setVisible(true);
	    	  
	    	  
	    	  
	    	  
	       }
	
	    	   void saveFile()
	    	   {
	    		   JFileChooser filechooser=new JFileChooser();                     
		             int result= filechooser.showSaveDialog(jf);
		              if (result==0)  
		              {
		            	  String text=area.getText();
		            	 f=filechooser.getSelectedFile();
		            	 jf.setTitle(f.getName());
		            	  try (FileOutputStream fos=new FileOutputStream(f))
		            	  {
		            		  byte []b=text.getBytes();
		            		  fos.write(b);
		            		  
		                  }
		            	  catch(Exception ee)
		            	  {
		            		  ee.printStackTrace();
		            	  }
		            			  
		              }
		     
		              
	    	   }
	    	   void newFile()
	    	   	{
	    		   String text=area.getText();
	    		   if (!text.equals(""))
	    		   {
	    			   int i=JOptionPane.showConfirmDialog(jf, "DO you want to save this file");
	    			   if (i==0) 
	    			   {
	    				   saveFile();
	    				   area.setText("");
	    				   jf.setTitle("\"*untitled- Notepad*\"");
	    				   
	    			   }
	    			   else if(i==1)
	    			   {
	    				   area.setText("");
	    			   }
	    				   
	    			 }
	    		   }
	    		   
	    				   
	    	   }
	    	   
		              

 
	


	



	



