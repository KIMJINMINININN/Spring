package com.spring.account;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED)
//클래스의 모든 메서드의 트랜잭션 적용
public class AccountService{
	   private AccountDAO accDAO;
	   //setter 구현
	   public void setAccDAO(AccountDAO accDAO){
	      this.accDAO = accDAO;
	   }
	   
	   public void sendMoney() throws Exception{
		   accDAO.updateBalance1();
		   accDAO.updateBalance2();
	   }
}
