package org.jsp.MercchantProduct.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.MercchantProduct.dto.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao 
{
	@Autowired
	private EntityManager manager;
	public Merchant saveMerchant(Merchant merehant)
	{
		EntityTransaction transaction=manager.getTransaction();
		manager.persist(merehant);
		transaction.begin();
		transaction.commit();
		return merehant;
	}
	public Merchant updateMerchant(Merchant merehant)
	{
		EntityTransaction transaction=manager.getTransaction();
		manager.merge(merehant);
		transaction.begin();
		transaction.commit();
		return merehant;
	}
	public Merchant verifyMerchant(long phone, String password)
	{
		Query q= manager.createQuery("select m from Merchant m where m.phone=?1 and m.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try 
		{
			return (Merchant) q.getSingleResult();
		}
		catch(NestedRuntimeException e)
		{
			return null;
		}
		
	}
	public Merchant verifyMerchant(String email, String password)
	{
		Query q= manager.createQuery("select m from Merchant m where m.email=?1 and m.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try 
		{
			return (Merchant) q.getSingleResult();
		}
		catch(NestedRuntimeException e)
		{
			return null;
		}
		
	}

	


}
