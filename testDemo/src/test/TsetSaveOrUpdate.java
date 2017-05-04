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
		dept.setDname("new ӡˢ��");
		dept.setLoc("�Ͼ�");
		session.saveOrUpdate(dept);

		// Dept dept = new Dept("ӡˢ", "ɽ��");
		// session.saveOrUpdate(dept);
		//
		Transaction tr = session.beginTransaction();
		try {
			tr.commit();
			System.out.println("���ӳɹ���");
		} catch (HibernateException e) {
			System.out.println("����ʧ�ܣ�");
			tr.rollback();
			e.printStackTrace();
		}
		HibernateUtil.closeSession();
	}
}
