package com.rockstar.o2o.util;
import java.awt.Font;
 import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;

import com.rockstar.o2o.util.file.FilethumbUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Testimage {
	 public static void main(String[] args) throws Exception{
		 Testimage pw=new Testimage();
		 pw.addImageLogo("d:\\1ff3971f-22d5-4ba3-b795-efb6af6911e0.jpg","d:\\newchonghair.jpg","d:\\aim1.jpg",170,170);
		  pw.addTextLogo("d:\\1ff3971f-22d5-4ba3-b795-efb6af6911e0.jpg","http://www.rockstars.com.cn","d:\\aim2.jpg",
		 new Font("Arial Black",Font.PLAIN,48),20,40);
		 //FilethumbUtil.saveImageAsJpgByLocal("d:\\chonghair.jpg", "d:\\newchonghair.jpg", 100, 100);
		 }
		 
		 /**
		 23 *@param photopath : 原图存放的路径
		24 *@param logopath : logo图像存放的路径
		25 *@param savepath : 目标输出保存的路径
		26 *@param x : logo图像在合并到原图中的显示位置x座标
		27 *@param y : logo图像在合并到原图中的显示位置y座标
		28 */
		  public void addImageLogo(String photopath,String logopath,String savepath,int x,int y)
		  throws IOException,FileNotFoundException{
		 
		  Image image=ImageIO.read(new File(photopath));
		  int pwidth=image.getWidth(null);
		  int pheight=image.getHeight(null);
		 
		  BufferedImage buffimage=new BufferedImage(pwidth,pheight,BufferedImage.TYPE_INT_BGR);
		  Graphics g=buffimage.createGraphics();
		  g.drawImage(image,0,0,pwidth,pheight,null);
		 
		  Image logo=ImageIO.read(new File(logopath));
		  int lwidth=logo.getWidth(null);
		  int lheight=logo.getHeight(null);
		  g.drawImage(logo,x,y,lwidth,lheight,null);
		 
		  g.dispose();
		  FileOutputStream os=new FileOutputStream(savepath);
		  JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(os);
		  encoder.encode(buffimage);
		  os.close();
		  }
		 
		  /**
		 53 *@param photopath : 原图存放的路径
		54 *@param logopath : 文本logo内容
		55 *@param savepath : 目标输出保存的路径
		56 *@param font : 文本logo的字体设置
		57 *@param x : 文本logo在合并到原图中的显示位置x座标
		58 *@param y : 文本logo在合并到原图中的显示位置y座标
		59 */
		  public void addTextLogo(String photopath,String logotext,String savepath,
		  java.awt.Font font,int x,int y) throws Exception{
		 
		  Image image=ImageIO.read(new File(photopath));
		  int pwidth=image.getWidth(null);
		  int pheight=image.getHeight(null);
		 
		  BufferedImage buffimage=new BufferedImage(pwidth,pheight,BufferedImage.TYPE_INT_BGR);
		  Graphics g=buffimage.createGraphics();
		  g.drawImage(image,0,0,pwidth,pheight,null);
		 
		  g.setFont(font);
		  g.drawString(logotext,x,y);
		
		  g.dispose();
		  FileOutputStream os=new FileOutputStream(savepath);
		  JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(os);
		  encoder.encode(buffimage);
		  os.close();
		  }
}
