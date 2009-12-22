package INIA.MSCC.DAO;

import java.util.List;
//import org.hibernate.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.mapping.List;



import INIA.MSCC.NEGOCIO.SEG.ENTIDADES.Usuario;

public class UsuarioDAO {

	public static Usuario login(String nombre, String password) {
		Usuario ret = null;
	
		Transaction tx = null;
		Session session = InitSessionFactory.getInstance().getCurrentSession();

//		Session s = HibernateUtil.getSession();
//        s.beginTransaction();
		try {
			tx = session.beginTransaction();

	        ret = (Usuario) session.createQuery("from Usuario as u where u.nombre= '" + nombre + "' " + " and u.password='" + password + "' ").uniqueResult();
	        //System.out.println(ret.getNombre());
	        
			//ret = (Usuario) session.createQuery("select u from tl_seg_usua_usuario as u " + " where u.usua_str_nombre= '" + nombre + "' " + " and u.usua_str_password='" + password + "' ").uniqueResult();
			//Object obj = session.createQuery("select u from tl_seg_usua_usuario as u " + " where u.usua_str_nombre= '" + nombre + "' " + " and u.usua_str_password='" + password + "' ").uniqueResult();
			tx.commit();
			
//	        List<Usuario> listaUsuario = s.createQuery("from Flight").list();
//	        for (Usuario usuario : listaUsuario)
//	        {
//	            System.out.println(usuario.getNombre());
//	            ret = usuario;
//	        }
//	        s.getTransaction().commit();
			
		}
		catch (HibernateException e) 
		{
			e.printStackTrace();
			if (tx != null && tx.isActive())
//			if(s.getTransaction()!=null && s.getTransaction().isActive())
			{	
				tx.rollback();
				//s.getTransaction().rollback();
			}
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	public static List<Usuario> getUsers() {
		List<Usuario> ret = null;

		Transaction tx = null;
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		try {
			tx = session.beginTransaction();
			ret = (List<Usuario>)session.createQuery("from Usuario").list();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null && tx.isActive())
				tx.rollback();
		}
		return ret;
	}

	public static void saveUser(Session session, Usuario u) {
		session.saveOrUpdate(u);
	}

	public static boolean isUser(String name, Session session) {
		boolean existe = false;
		List res = session.createQuery(
			"select u from Usuario as u " +
			" where u.nombre='" + name + "' "
		).list();
		existe = res.size() > 0;
		return existe;
	}
	
}