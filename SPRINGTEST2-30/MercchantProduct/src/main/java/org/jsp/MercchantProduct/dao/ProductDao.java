package org.jsp.MercchantProduct.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.MercchantProduct.dto.Merchant;
import org.jsp.MercchantProduct.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private EntityManager manager;

	public Product addProduct(Product product,int merchant_id)
	{
		EntityTransaction transaction=manager.getTransaction();
		Merchant m=new Merchant();
		m=manager.find(Merchant.class, merchant_id);
		if (m != null) 
		{
			m.getProducts().add(product);
			product.setMerchant(m);
			manager.persist(product);
			transaction.begin();
			transaction.commit();
			return product;
		}
		return null;
	}

	public Product updateProduct(Product product) {
		EntityTransaction transaction = manager.getTransaction();
		manager.merge(product);
		transaction.begin();
		transaction.commit();
		return product;
	}

	public List<Product> findProduct(String brand) {
		Query q = manager.createQuery("select p from Product p where p.brand=?1");
		q.setParameter(1, brand);
		return q.getResultList();
	}

	public List<Product> findProducts(String category) {
		Query q = manager.createQuery("select p from Product p where p.category=?1");
		q.setParameter(1, category);
		return q.getResultList();
	}

	public List<Product> findProductss(int id) {
		Query q = manager.createQuery("select p from Product p where p.merchant.id=?1");
		q.setParameter(1, id);
		return q.getResultList();
	}

	public List<Product> findProductsss(long phone, String password) {
		Query q = manager.createQuery("select p from Product p where p.merchant.phone=?1 and p.merchant.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		return q.getResultList();
	}

}
