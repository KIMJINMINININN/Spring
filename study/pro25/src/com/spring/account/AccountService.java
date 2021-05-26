package com.spring.account;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED)
//Ŭ������ ��� �޼����� Ʈ����� ����
public class AccountService{
	   private AccountDAO accDAO;
	   //setter ����
	   public void setAccDAO(AccountDAO accDAO){
	      this.accDAO = accDAO;
	   }
	   
	   public void sendMoney() throws Exception{
		   accDAO.updateBalance1();
		   accDAO.updateBalance2();
	   }
}
