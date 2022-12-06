package com.vti.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.Account;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForUpdating;

public interface IAccountService {
	public Page<Account> getAllAccount(Pageable pageable);

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
