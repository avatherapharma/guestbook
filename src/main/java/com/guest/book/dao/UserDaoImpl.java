package com.guest.book.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.guest.book.entity.USER;
import com.guest.book.entity.USERENTRY;
import com.guest.book.model.UserEntry;
import com.guest.book.model.UserModel;

public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<USER> validateUser(UserModel model) {

		Session session = null;
		List<USER> result = new ArrayList<USER>();
		try {

			session = sessionFactory.openSession();
			@SuppressWarnings("deprecation")

			Criteria crit = session.createCriteria(USER.class);
			crit.add(Restrictions.ilike("USERNAME", model.getUserName()));
			crit.add(Restrictions.ilike("PASSWORD", model.getPassword()));
			crit.add(Restrictions.ilike("ROLE", model.getRole()));
		//	crit.add(Restrictions.ilike("STATUS","active"));

			result = crit.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public boolean addGuestEntry(UserEntry model) {

		boolean result = false;
		Session session = null;
		try {

			session = sessionFactory.openSession();

			session.beginTransaction();
			USERENTRY entry = new USERENTRY();

			entry.setENTRY(model.getEntry());
			entry.setID(model.getId());
			entry.setUSER(model.getUser());
			entry.setSTATUS("pending");
			session.save(entry);
			session.getTransaction().commit();
			result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public List<USERENTRY> alluserentrys() {
		Session session = null;
		List<USERENTRY> result = new ArrayList<USERENTRY>();
		try {

			session = sessionFactory.openSession();
			@SuppressWarnings("deprecation")

			Criteria crit = session.createCriteria(USERENTRY.class);
			crit.add(Restrictions.ilike("STATUS", "pending"));

			result = crit.list();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			session.close();
		}

		return result;
	}

	@Override
	public boolean approveEntry(UserEntry model) {
		Session session = null;
		boolean updatestatus = false;
		try {

			session = sessionFactory.openSession();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<USERENTRY> criteriaUpdate = cb.createCriteriaUpdate(USERENTRY.class);
			Root<USERENTRY> root = criteriaUpdate.from(USERENTRY.class);
			criteriaUpdate.set("STATUS", "approved");

			criteriaUpdate.where(cb.equal(root.get("ID"), model.getId()));

			Transaction transaction = session.beginTransaction();
			session.createQuery(criteriaUpdate).executeUpdate();
			transaction.commit();

			updatestatus = true;
		} catch (Exception e) {

		} finally {
			session.close();
		}
		return updatestatus;
	}

	@Override
	public boolean removeentry(UserEntry model) {
		Session session = null;
		boolean updatestatus = false;
		try {

			session = sessionFactory.openSession();

			USERENTRY entry = new USERENTRY();
			entry.setID(model.getId());

			Transaction transaction = session.beginTransaction();
			session.delete(entry);
			transaction.commit();

			updatestatus = true;
		} catch (Exception e) {

		} finally {
			session.close();
		}
		return updatestatus;
	}

	@Override
	public boolean addNewUserUser(UserModel model) {

		boolean result = false;
		Session session = null;
		try {

			session = sessionFactory.openSession();

			session.beginTransaction();
			USER entry = new USER();

			entry.setADDRESS(model.getAddress());
			entry.setMOBILE(model.getMobileNo());
			entry.setNAME(model.getName());
			entry.setPASSWORD(model.getPassword());
			entry.setROLE(model.getRole());
			entry.setUSERNAME(model.getUserName());
			entry.setSTATUS("pending");
			session.save(entry);
			session.getTransaction().commit();
			result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		} finally {
			session.close();
		}
return result;
	}

}
