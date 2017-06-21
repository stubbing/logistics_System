package dao.impl;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import bean.ProvinceCenter;
import dao.IProvinceCenterDao;
public class ProvinceCenterDaoImpl extends HibernateDaoSupport implements IProvinceCenterDao {    //���������˺�����������ݿ⣬�鿴�û��Ƿ����
	 public ProvinceCenter getProvinceCenterByIDAndPwd(String login, String password) throws Exception {
	        // TODO Auto-generated method stub
	        String hql = "from ProvinceCenter where centerId=? and pwd=?";
	        Session session = null;
	        ProvinceCenter user = null;
	        try {
	            session = getSession();
	            user = (ProvinceCenter) session.createQuery(hql).setParameter(0, Integer.parseInt(login)).setParameter(1, password)
	                    .uniqueResult();
	        } finally {
	            releaseSession(session);
	        }
	        return user;
	    }

	@Override
	public void save(ProvinceCenter user) throws Exception {
		   getHibernateTemplate().saveOrUpdate(user);
	}

}