package org.jsp.MercchantProduct.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.MercchantProduct.MerchantProductConfig;
import org.jsp.MercchantProduct.dao.MerchantDao;
import org.jsp.MercchantProduct.dao.ProductDao;
import org.jsp.MercchantProduct.dto.Merchant;
import org.jsp.MercchantProduct.dto.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MerchantProductController
{
	static Scanner s=new Scanner(System.in);
	private static MerchantDao merchantDao;
	private static ProductDao productDao;
	
	static
	{
		ApplicationContext context=new AnnotationConfigApplicationContext(MerchantProductConfig.class);
		merchantDao=context.getBean(MerchantDao.class);
		productDao = context.getBean(ProductDao.class);
	}
	public static void main(String[] args)
	{
		System.out.println("1.Save Merchant ");
		System.out.println("2.Update Merchant ");
		System.out.println("3.Add product ");
		System.out.println("4.Update Product ");
		System.out.println("5.Verify Merchant by phone and password");
		System.out.println("6.Verify Merchant by email and password");
		System.out.println("7.Find product by brand ");
		System.out.println("8.Find product by category");
		System.out.println("9.Find product by merchant id ");
		System.out.println("10..Find product by  merchant phone and pasword");


		switch(s.nextInt())
		{
		case 1:
		{
			saveMerchant();
			break;
		}
		case 2:
		{
			updateMerchant();
			break;
		}
		case 3:
		{
			saveProduct();
			break;
		}
		case 4:
		{
			updateProduct();
			break;
		}
		case 5:
		{
			verifyMerchantbyphoneAndPassword() ;
			break;
		}
		case 6:
		{
			verifyMerchantbyemailAndPassword() ;
			break;
		}
		case 7:
		{
			findProductBybrand();
			break;
		}
		case 8:
		{
			findProductBycategory();
			break;
		}
		case 9:
		{
			findProductBymerchantId();
			break;
		}
		case 10:
		{
			findProductBymerchantPhoneAndpassword();
			break;
		}
		}
	}
	public static void saveMerchant()
	{
		System.out.println("enter name,phone email and password");
		Merchant m=new Merchant();
		m.setName(s.next());
		m.setPhone(s.nextLong());
		m.setEmail(s.next());
		m.setPassword(s.next());
		m=merchantDao.saveMerchant(m);
		System.out.println("Merchant saved with id :"+m.getId());
		
	}
	public static void updateMerchant()
	{
		System.out.println("enter merchant id to update the merchant record");
		int id=s.nextInt();
		System.out.println("enter name,phone email and password");
		Merchant m=new Merchant();
		m.setId(id);
		m.setName(s.next());
		m.setPhone(s.nextLong());
		m.setEmail(s.next());
		m.setPassword(s.next());
		m=merchantDao.updateMerchant(m);
		System.out.println("Merchant updated  with id :"+m.getId());
	}
	public static void saveProduct()
	{
		System.out.println("enter merchant id to save the product");
		int id=s.nextInt();
		System.out.println("enter name, brand, category, image, cost");
		Product p=new Product();
		p.setName(s.next());
		p.setBrand(s.next());
		p.setCategory(s.next());
		p.setImage(s.next());
		p.setCost(s.nextDouble());
		p=productDao.addProduct(p, id);
		System.out.println("Product Saved  with id :"+p.getId());
	}
	
	public static void updateProduct()
	{
		System.out.println("enter product id to update the product record");
		int id=s.nextInt();
		System.out.println("enter name, brand, category, image, cost");
		Product p=new Product();
		p.setId(id);
		p.setName(s.next());
		p.setBrand(s.next());
		p.setCategory(s.next());
		p.setImage(s.next());
		p.setCost(s.nextDouble());
		p=productDao.updateProduct(p);
		System.out.println("Product update  with id :"+p.getId());
	}
	public static void verifyMerchantbyphoneAndPassword() 
	{
		System.out.println("Enter phone and password");
		long phone =s.nextLong();
		String password=s.next();
		Merchant m=new Merchant();
		m=merchantDao.verifyMerchant(phone, password);
		if(m!=null)
		{
			System.out.println("ID :"+m.getId());
			System.out.println("Name :"+m.getName());
			System.out.println("Phone :"+m.getPhone());
			System.out.println("Email"+m.getEmail());
			System.out.println("Password :"+m.getPassword());
		}
		else
		{
			System.err.println("Enter Invalid Credential");
		}
	}
	public static void verifyMerchantbyemailAndPassword() 
	{
		System.out.println("Enter email and password");
		String email=s.next();
		String password=s.next();
		Merchant m=new Merchant();
		m=merchantDao.verifyMerchant(email, password);
		if(m!=null)
		{
			System.out.println("ID :"+m.getId());
			System.out.println("Name :"+m.getName());
			System.out.println("Phone :"+m.getPhone());
			System.out.println("Email"+m.getEmail());
			System.out.println("Password :"+m.getPassword());
		}
		else
		{
			System.err.println("Enter Invalid Credential");
		}
	}
	public static void findProductBybrand()
	{
		System.out.println("enter brand");
		String brand=s.next();
		List<Product> prods=productDao.findProduct(brand);
		if(prods.size()>0)
		{
			for(Product p: prods)
			{
				System.out.println("id :"+p.getId());
				System.out.println("name :"+p.getName());
				System.out.println("brand :"+p.getBrand());
				System.out.println("category :"+p.getCategory());
				System.out.println("image :"+p.getImage());
				System.out.println("cost :"+p.getCost());
			}
		}
		else
		{
			System.err.println("Enter Invalid Credential");
		}
		
	}
	public static void findProductBycategory()
	{
		System.out.println("enter category");
		String category=s.next();
		List<Product> prods=productDao.findProducts(category);
		if(prods.size()>0)
		{
			for(Product p: prods)
			{
				System.out.println("id :"+p.getId());
				System.out.println("name :"+p.getName());
				System.out.println("brand :"+p.getBrand());
				System.out.println("category :"+p.getCategory());
				System.out.println("image :"+p.getImage());
				System.out.println("cost :"+p.getCost());
			}
		}
		else
		{
			System.err.println("Enter Invalid Credential");
		}
	}
	public static void findProductBymerchantId()
	{
		System.out.println("enter merchant id");
		int id=s.nextInt();
		List<Product> prods=productDao.findProductss(id);
		if(prods.size()>0)
		{
			for(Product p: prods)
			{
				System.out.println("id :"+p.getId());
				System.out.println("name :"+p.getName());
				System.out.println("brand :"+p.getBrand());
				System.out.println("category :"+p.getCategory());
				System.out.println("image :"+p.getImage());
				System.out.println("cost :"+p.getCost());
			}
		}
		else
		{
			System.err.println("Enter Invalid Credential");
		}
	}
	public static void findProductBymerchantPhoneAndpassword()
	{
		System.out.println("enter merchant phone and password");
		long phone=s.nextLong();
		String password=s.next();
		List<Product> prods=productDao.findProductsss(phone, password);
		if(prods.size()>0)
		{
			for(Product p: prods)
			{System.out.println("id :"+p.getId());
			System.out.println("name :"+p.getName());
			System.out.println("brand :"+p.getBrand());
			System.out.println("category :"+p.getCategory());
			System.out.println("image :"+p.getImage());
			System.out.println("cost :"+p.getCost());
			}
		}
		else
		{
			System.err.println("Enter Invalid Credential");
		}
	}

}
