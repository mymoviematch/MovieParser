package mm.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import mm.simple.model.Picture;
import mm.simple.model.helper.Backdrop;

public class PictureSaver {

	public static Picture save(Picture picture,int no){
		 URL url;

		try {
			url = new URL(picture.getLink());

		 InputStream in = new BufferedInputStream(url.openStream());
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
		 byte[] buf = new byte[1024];
		 int n = 0;
		 while (-1!=(n=in.read(buf)))
		 {
		    out.write(buf, 0, n);
		 }
		 out.close();
		 in.close();
		 byte[] response = out.toByteArray();
		 
		 File path = new File("C:"+File.separator+"Users"+File.separator+"A619678"+File.separator+"workspace"+File.separator+"MovieTime"+File.separator+"WebContent"+File.separator+"WEB-INF"+File.separator+"img"+File.separator+ picture.movie.movie_id+ File.separator +no+".jpg");
		 path.getParentFile().mkdirs();
		 path.createNewFile();
		 
		 FileOutputStream fos = new FileOutputStream(path);
		 fos.write(response);
		 fos.close();
		 picture.setLocal(path.getAbsolutePath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			return picture;
		}
	}
}
