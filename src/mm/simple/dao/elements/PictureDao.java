package mm.simple.dao.elements;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import mm.simple.dao.BaseDao;
import mm.simple.dao.IDao;
import mm.simple.model.Movie;
import mm.simple.model.Picture;
import mm.utils.HibernateSingleton;
import mm.utils.PictureSaver;

public class PictureDao extends BaseDao implements IDao<Picture>{

	@Override
	public void saveList(List<Picture> pictures, Movie movie) {
		 HibernateSingleton.getInstance();
		 Session session;
		 try{
			 session = HibernateSingleton.getSessionFactory().getCurrentSession();
		 }catch(Exception e){
			 session = HibernateSingleton.getSessionFactory().openSession();
		 }
		 int i =0;
        // 4. Starting Transaction
        Transaction transaction = session.beginTransaction();
        if(pictures!=null){
	        for(Picture picture:pictures){
	        	picture = PictureSaver.save(picture, i);
	        	movie.addPicture(picture);
	        	session.save(picture);
	        	i++;
	        }	
        }
        session.update(movie);
        transaction.commit();
        
        session.close();
		
	}

	@Override
	public List getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Picture movieElement, Movie movie) {
		// TODO Auto-generated method stub
		
	}

}
