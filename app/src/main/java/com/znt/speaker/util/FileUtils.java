
package com.znt.speaker.util; 

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/** 
 * @ClassName: MyFileUtils 
 * @Description: TODO
 * @author yan.yu 
 * @date 2014-2-11 涓嬪崍4:41:52  
 */
public class FileUtils
{

	/**
	* @Description: 鍒ゆ柇鏂囦欢鏄惁鏈夋晥
	* @param @param filrUrl
	* @param @return   
	* @return boolean 
	* @throws
	 */
	public static boolean isFileValid(String filrUrl)
	{
		if(TextUtils.isEmpty(filrUrl))
			return false;
		File tempFile = new File(filrUrl);
		if(!tempFile.exists())
			return false;
		if(tempFile.length() == 0)
			return false;
		
		return true;
	}
	

	/**
	* @Description: 鑾峰彇鐩綍涓嬬殑鏂囦欢(鏈湴鏂囦欢鎵弿鏂瑰紡)
	* @param @param fileList  鑾峰彇鐨勬枃浠跺垪琛�
	* @param @param type 0, 鍥剧墖锛�1锛岄煶棰戯紱2锛岃棰�
	* @param @param path   鐩綍璺緞
	* @return void 
	* @throws
	 */
	public static void getLocalFiles(final List<File> fileList, final int type, String path)
	{
		if(path == null || path.length() == 0)
			return ;
		File dirFile = new File(path);
		File[] files = dirFile.listFiles(new FileFilter()
		{
			@Override
			public boolean accept(File pathname)
			{
				// TODO Auto-generated method stub
				if(pathname.isDirectory() && !pathname.isHidden() && pathname.canRead())
				{
					getLocalFiles(fileList, type, pathname.getAbsolutePath());
					return false;
				}
				boolean result = false;
				if(type == 0)//鍥剧墖
					result = isPicture(pathname.getAbsolutePath());
				else if(type == 1)//闊抽
					result = isMusic(pathname.getAbsolutePath());
				else if(type == 2)//瑙嗛
					result = isVideo(pathname.getAbsolutePath());	
				if(result)
				{
					fileList.add(pathname);
				}
				return false;
			}
		});
	}
	
	/**
	* @Description: MP4鏂囦欢
	* @param @param path
	* @param @return   
	* @return boolean 
	* @throws
	 */
	public static boolean isVideo(String path)
	{
		if(path == null || path.length() == 0)
			return false;
		if(path.toLowerCase().endsWith(".mp4") || 
				path.toLowerCase().endsWith(".flv") ||
				path.toLowerCase().endsWith(".3gp") ||
				path.toLowerCase().endsWith(".wmv") ||
				path.toLowerCase().endsWith(".avi") ||
				path.toLowerCase().endsWith(".mkv") ||
				path.toLowerCase().endsWith(".rmvb") ||
				path.toLowerCase().endsWith(".mpg") ||
				path.toLowerCase().endsWith(".mpeg") ||
				path.toLowerCase().endsWith(".asf") ||
				path.toLowerCase().endsWith(".iso") ||
				path.toLowerCase().endsWith(".dat") ||
				path.toLowerCase().endsWith(".263") ||
				path.toLowerCase().endsWith(".h264") ||
				path.toLowerCase().endsWith(".mov") ||
				path.toLowerCase().endsWith(".rm") ||
				path.toLowerCase().endsWith(".ts") ||
				path.toLowerCase().endsWith(".vod") ||
				path.toLowerCase().endsWith(".mts"))
		{
			return true;
		}
		return false;
	}
	
	/**
	* @Description: 闊抽鏂囦欢
	* @param @param path
	* @param @return   
	* @return boolean 
	* @throws
	 */
	public static boolean isMusic(String path)
	{
		if(path == null || path.length() == 0)
			return false;
		if(path.toLowerCase().endsWith(".mp3") || 
			path.toLowerCase().endsWith(".flac")||
			path.toLowerCase().endsWith(".wav") ||
			path.toLowerCase().endsWith(".ape") ||
			path.toLowerCase().endsWith(".aac") ||
			path.toLowerCase().endsWith(".wma") ||
			path.toLowerCase().endsWith(".ogg") ||
			path.toLowerCase().endsWith(".ac3") ||
			path.toLowerCase().endsWith(".ddp") ||
			path.toLowerCase().endsWith(".pcm")) 
		{
			return true;
		}
		return false;
	}
	
	/**
	* @Description: 鍥剧墖鏂囦欢
	* @param @param path
	* @param @return   
	* @return boolean 
	* @throws
	 */
	public static boolean isPicture(String path)
	{
		if(path == null || path.length() == 0)
			return false;
		if(path.toLowerCase().endsWith(".jpg") || 
				path.toLowerCase().endsWith(".png") ||
				path.toLowerCase().endsWith(".bmp") ||
				path.toLowerCase().endsWith(".gif"))
		{
			return true;
		}
		return false;
	}
	
	
	/**
	* @Description: 鍒犻櫎鏈湴鍗曚釜鏂囦欢
	* @param @param file
	* @param @return   
	* @return int 0,鍒犻櫎鎴愬姛锛�1锛屾枃浠朵笉瀛樺湪锛�2锛屽垹闄ゅけ璐�
	* @throws
	 */
	public static int deleteFile(File file)
	{
		if(file == null || !file.exists())
			return 1;//鏂囦欢涓嶅瓨鍦�
		if(!file.canWrite())
			return 2;//娌℃湁鍐欐潈闄�
		file.delete();
		return 0;
	}
	/**
	* @Description: 鍒犻櫎鏈湴澶氫釜鏂囦欢
	* @param @param files
	* @param @return   
	* @return int 0,鍒犻櫎鎴愬姛锛�1锛屾枃浠朵笉瀛樺湪锛�2锛屽垹闄ゅけ璐�
	* @throws
	 */
	public static int deleteFile(List<File> files)
	{
		int result = 0;
		for(int i=0;i<files.size();i++)
		{
			result = deleteFile(files.get(0));
			if(result != 0)
			{
				break;
			}
		}
		return result;
	}
	
	/**
	* @Description: 鍒犻櫎鏈湴鐩綍
	* @param @param file
	* @param @return   
	* @return int 0,鍒犻櫎鎴愬姛锛�1锛屾枃浠朵笉瀛樺湪锛�2锛屽垹闄ゅけ璐�
	* @throws
	 */
	public static int deleteFolder(File file)
	{
		if(file == null || !file.exists())
			return 1;// 鐩綍涓嶅瓨鍦�
		if(!file.canWrite())
			return 2;//娌℃湁鍐欐潈闄�
		if(file.isFile())
			file.delete();
		else if(file.isDirectory())
		{
			File[] childFile = file.listFiles();
            if(childFile == null || childFile.length == 0)
            {
                file.delete();
                return 0;
            }
            for(File f : childFile)
            {
            	deleteFolder(f);
            }
            file.delete();
		}
		return 0;
	}
	
	/**
	* @Description: 浠庢祦涓幏鍙栧瓧鑺傛暟缁�
	* @param @param is
	* @param @return
	* @param @throws IOException   
	* @return byte[] 
	* @throws
	 */
	public static byte[] getBytes(InputStream is) throws IOException 
	{  
		if(is == null)
			return null;
       ByteArrayOutputStream outstream = new ByteArrayOutputStream();  
       byte[] buffer = new byte[1024]; // 鐢ㄦ暟鎹  
       int len = -1;  
       while ((len = is.read(buffer)) != -1) 
       {  
           outstream.write(buffer, 0, len);  
       }  
       if(outstream != null)
    	   outstream.close();  
       // 鍏抽棴娴佷竴瀹氳璁板緱銆�  
       return outstream.toByteArray();  
   } 
	
	public static String getStringFromFile(File file)
	{
		if(file == null || !file.exists())
			return "";
		String str = "";
		
		try
		{
			FileInputStream fis = new FileInputStream(file);
			
			byte[] buffer = new byte[1024]; // 鐢ㄦ暟鎹  
	        int len = -1;  
	        while ((len = fis.read(buffer)) != -1) 
	        {  
	    	     String s = new String(buffer);
	    	     str += s;
	        }  
	        if(fis != null)
	    	   fis.close();  
			
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
	}
	
	/**
	* @Description: 鎵撳紑瀹夎鍖呮枃浠�
	* @param @param activity
	* @param @param file   
	* @return void 
	* @throws
	 */
	public static void openApkFile(Activity activity, File file) 
	{
        // TODO Auto-generated method stub
		if(file == null || !file.exists())
			return;
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
        activity.startActivity(intent);
	}
	

   /** 
     * 澶嶅埗鏁翠釜鏂囦欢澶瑰唴瀹� 
     * @param oldPath String 鍘熸枃浠惰矾寰� 濡傦細c:/fqf 
     * @param newPath String 澶嶅埗鍚庤矾寰� 濡傦細f:/fqf/ff 
     * @return boolean 
     */ 
   public static boolean copyFolder(String oldPath, String newPath) 
   { 
	   boolean isok = true;
       try 
       { 
           (new File(newPath)).mkdirs(); //濡傛灉鏂囦欢澶逛笉瀛樺湪 鍒欏缓绔嬫柊鏂囦欢澶� 
           File oldFile = new File(oldPath); 
           String[] file= oldFile.list(); 
           File temp=null; 
           for (int i = 0; i < file.length; i++) 
           { 
               if(oldPath.endsWith(File.separator))
               { 
                   temp = new File(oldPath + file[i]); 
               } 
               else
               { 
                   temp = new File(oldPath+File.separator + file[i]); 
               } 

               if(temp.isFile())
               { 
                   FileInputStream input = new FileInputStream(temp); 
                   FileOutputStream fos = new FileOutputStream(newPath + "/" + 
                           (temp.getName()).toString()); 
                   BufferedOutputStream bos = new BufferedOutputStream(fos);
                   byte[] b = new byte[1024 * 4 ];
                   int len; 
                   while ( (len = input.read(b)) != -1) 
                   { 
                	   bos.write(b, 0, len); 
                   } 
                   bos.flush(); 
                   bos.close(); 
                   input.close(); 
               } 
               if(temp.isDirectory())
               {
            	   //濡傛灉鏄瓙鏂囦欢澶� 
                   copyFolder(oldPath + "/"+ file[i],newPath + "/" + file[i]); 
               } 
           } 
       } 
       catch (Exception e) 
       { 
    	    isok = false;
       } 
       return isok;
   }
   
   
   /**
   * @Description: 浠庢枃浠朵腑鑾峰彇娴�
   * @param @param filePath
   * @param @return   
   * @return InputStream 
   * @throws
    */
   public static FileInputStream readDataFromFile(String filePath)
   {
	   File file = new File(filePath);
	   if(!file.exists())
		   return null;
	   try
	   {
		   FileInputStream fis = new FileInputStream(file);
		   //byte[] buffer = new byte[(int) file.length()];
		   //fis.read(buffer);
		   //content = buffer.toString();
		   return fis;
	   } 
	   catch (FileNotFoundException e)
	   {
		// TODO Auto-generated catch block
		   e.printStackTrace();
		   return null;
	   } 
	   //return content;
   }
   
   /**
   * @Description: 鑾峰彇鏈畨瑁卆pk鐨勫浘鏍�
   * @param @param context
   * @param @param apkPath
   * @param @return   
   * @return Drawable 
   * @throws
    */
   public static Drawable getApkIcon(Context context, String apkPath) 
   {
       PackageManager pm = context.getPackageManager();
       PackageInfo info = pm.getPackageArchiveInfo(apkPath,
               PackageManager.GET_ACTIVITIES);
       if (info != null) 
       {
           ApplicationInfo appInfo = info.applicationInfo;
           appInfo.sourceDir = apkPath;
           appInfo.publicSourceDir = apkPath;
           try 
           {
               return appInfo.loadIcon(pm);
           } 
           catch (OutOfMemoryError e) 
           {
               Log.e("ApkIconLoader", e.toString());
           }
       }
       return null;
   }
   
   public static Uri insertImage(ContentResolver cr, String name, long dateTaken, String directory, String filename)
   { 
   	  if(!directory.endsWith("/"))
   		  directory = directory + "/";
       String filePath = directory + filename; 
    
       ContentValues values = new ContentValues(7); 
       values.put(Media.TITLE, name);
       values.put(Media.DISPLAY_NAME, filename);
       values.put(Media.DATE_TAKEN, dateTaken);
       values.put(Media.MIME_TYPE, "image/jpeg");
       values.put(Media.DATA, filePath);
       return cr.insert(Media.EXTERNAL_CONTENT_URI, values);
   }
   
}
 
