package mm.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import mm.simple.model.helper.Backdrop;

public class PictureSaver {

	public void save(String name,Backdrop bd){
		 URL url;

		 if(bd.isDownload()){
		try {
			url = new URL(bd.getLink());

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
		 
		 FileOutputStream fos = new FileOutputStream("img//"+name+".jpg");
		 fos.write(response);
		 fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 }
	}
}
