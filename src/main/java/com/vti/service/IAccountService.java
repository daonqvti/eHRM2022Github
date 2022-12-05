package com.vti.service;

import java.util.List;

import com.vti.entity.Account;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForUpdating;

public interface IAccountService {
	public List<Account> getAllAccount();

	public Account getAccountById(short id);

//
//	public Account getAccountByName(String name);
//
	public Account createAccount(AccountFormForCreating form);

	public Account updateAccount(short id, AccountFormForUpdating form);

	public void deleteAccount(short id);
//
//	public boolean isAccountExistsByID(short id);
//
//	public boolean isAccountExistsByName(String name);
}
