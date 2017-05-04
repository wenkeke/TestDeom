package test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import entity.Dept;

public class TsetSaveOrUpdate {
	public static void main(String[] args) {
		Session session = HibernateUtil.currentSession();

		Dept dept = (Dept) session.load(Dept.class, 92);

		// Dept dept = new Dept();
		// .dept.setDeptno(131);
		dept.setDname("new 印刷厂");
		dept.setLoc("南京");
		session.saveOrUpdate(dept);

		// Dept dept = new Dept("印刷", "山东");
		// session.saveOrUpdate(dept);
		//
		Transaction tr = session.beginTransaction();
		try {
			tr.commit();
			System.out.println("增加成功！");
		} catch (HibernateException e) {
			System.out.println("增加失败！");
			tr.rollback();
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
	}
}
