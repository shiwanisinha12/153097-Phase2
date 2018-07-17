package com.capgemini.mypayment.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mypayment.bean.Customer;
import com.capgemini.mypayment.bean.Wallet;
import com.capgemini.mypayment.exception.InsufficientBalanceException;
import com.capgemini.mypayment.exception.InvalidInputException;
import com.capgemini.mypayment.repo.WalletRepo;
import com.capgemini.mypayment.repo.WalletRepoImpl;
import com.capgemini.mypayment.service.WalletService;
import com.capgemini.mypayment.service.WalletServiceImpl;

public class TestClass {


	WalletService service;
	WalletRepo repo;
	Customer customer1,customer2,customer3;
	
	
	@Before
	public void initialiseData() throws InvalidInputException{
		service=new WalletServiceImpl();
		boolean b=service.truncate();
		customer1=service.createAccount("Shiwani", "9096736965", new BigDecimal(47500));
		customer2=service.createAccount("Shraddha", "9431223174", new BigDecimal(41500));
		customer3=service.createAccount("Shrutika", "9876543210", new BigDecimal(50000));
	}
	
	@Test
	public void testShowBalance1() throws InvalidInputException {
		
		Customer customer=new Customer();
		customer=service.showBalance("9096736965");
		assertEquals(new BigDecimal(47500),customer.getWallet().getBalance());
	}
	@Test
	public void testShowBalance2() throws InvalidInputException {
		
		Customer customer=new Customer();
		customer=service.showBalance("9431223174");
		assertEquals(new BigDecimal(41500),customer.getWallet().getBalance());
	}
	@Test
	public void testShowBalance3() throws InvalidInputException {
		
		Customer customer=new Customer();
		customer=service.showBalance("9876543210");
		assertEquals(new BigDecimal(50000),customer.getWallet().getBalance());
	}
	@Test
	public void testFundTransfer1() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.fundTransfer("9096736965", "9431223174",new BigDecimal(500));
		assertEquals(new BigDecimal(47000), customer.getWallet().getBalance());
	}
	@Test
	public void testFundTransfer2() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.fundTransfer("9431223174","9096736965",new BigDecimal(500));
		assertEquals(new BigDecimal(41000), customer.getWallet().getBalance());
	}
	@Test
	public void testFundTransfer3() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.fundTransfer("9876543210","9431223174",new BigDecimal(500));
		assertEquals(new BigDecimal(49500), customer.getWallet().getBalance());
	}
	@Test(expected=InvalidInputException.class)
	public void testFundTransferEx1() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.fundTransfer("90967365", "9431223174",new BigDecimal(500));
		//assertEquals(new BigDecimal(47000), customer.getWallet().getBalance());
	}
	@Test(expected=InvalidInputException.class)
	public void testFundTransferEx2() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.fundTransfer("9431223174","90966965",new BigDecimal(500));
		//assertEquals(new BigDecimal(41000), customer.getWallet().getBalance());
	}
	@Test(expected=InvalidInputException.class)
	public void testFundTransferEx3() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.fundTransfer("98765430","94312274",new BigDecimal(500));
		//assertEquals(new BigDecimal(49500), customer.getWallet().getBalance());
	}
	@Test
	public void testDeposit1() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.depositAmount("9096736965", new BigDecimal(50000));
		assertEquals(new BigDecimal(97500), customer.getWallet().getBalance());
	}
	@Test
	public void testDeposit2() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.depositAmount("9431223174", new BigDecimal(500));
		assertEquals(new BigDecimal(42000), customer.getWallet().getBalance());
	}
	@Test
	public void testDeposit3() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.depositAmount("9876543210", new BigDecimal(500));
		assertEquals(new BigDecimal(50500), customer.getWallet().getBalance());
	}
	@Test
	public void testWithdraw1() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.withdrawAmount("9096736965", new BigDecimal(500));
		assertEquals(new BigDecimal(47000), customer.getWallet().getBalance());
	}
	@Test
	public void testWithdraw2() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.withdrawAmount("9431223174", new BigDecimal(500));
		assertEquals(new BigDecimal(41000), customer.getWallet().getBalance());
	}
	@Test
	public void testWithdraw3() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.withdrawAmount("9876543210", new BigDecimal(500));
		assertEquals(new BigDecimal(49500), customer.getWallet().getBalance());
	}
	@Test(expected=InvalidInputException.class)
	public void testDepositEx1() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.depositAmount("90967369", new BigDecimal(50000));
	
	}
	@Test(expected=InvalidInputException.class)
	public void testDepositEx2() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.depositAmount("94312231", new BigDecimal(50000));
		
	}
	@Test(expected=InvalidInputException.class)
	public void testDepositEx3() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.depositAmount("0000011111", new BigDecimal(50000));
		
	}
	@Test(expected=InsufficientBalanceException.class)
	public void testWithdraw1Ex() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.withdrawAmount("9096736965", new BigDecimal(100000));
		
	}
	@Test(expected=InsufficientBalanceException.class)
	public void testWithdraw2Ex() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.withdrawAmount("9431223174", new BigDecimal(50000));
		
	}
	@Test(expected=InsufficientBalanceException.class)
	public void testWithdraw3Ex() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.withdrawAmount("9876543210", new BigDecimal(60000));
		
	}
	@Test(expected=InvalidInputException.class)
	public void testWithdraw1Ex1() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.withdrawAmount("90967365", new BigDecimal(100000));
		
	}
	@Test(expected=InvalidInputException.class)
	public void testWithdraw2Ex2() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.withdrawAmount("94312234", new BigDecimal(50000));
		
	}
	@Test(expected=InvalidInputException.class)
	public void testWithdraw3Ex3() throws InvalidInputException
	{
		Customer customer=new Customer();
		customer=service.withdrawAmount("98765430", new BigDecimal(60000));
		
	}
}
