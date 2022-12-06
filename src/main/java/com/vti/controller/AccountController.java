package com.vti.controller;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccontDto;
import com.vti.entity.Account;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForUpdating;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "api/v1/accounts")
@CrossOrigin("*")
public class AccountController {

	@Autowired
	private IAccountService accountService;

	@GetMapping()
	public ResponseEntity<?> getAllAccount(Pageable pageable) {
//		List<Account> entities = accountService.getAllAccount();
//
//		List<AccontDto> dtos = new ArrayList<>();
//
//		// convert entities --> dtos
//		for (Account account : entities) {
//			AccontDto dto = new AccontDto(account.getId(), account.getEmail(), account.getUsername(),
//					account.getFullname(), account.getDepartment().getName(),
//					account.getPosition().getName().toString(), account.getCreateDate());
//			dtos.add(dto);
//		}

		Page<Account> entities = accountService.getAllAccount(pageable);

		// convert entities --> dtos
		Page<AccontDto> dtoPage = entities.map(new Function<Account, AccontDto>() {

			@Override
			public AccontDto apply(Account account) {
				AccontDto dto = new AccontDto(account.getId(), account.getEmail(), account.getUsername(),
						account.getFullname(), account.getDepartment().getName(),
						account.getPosition().getName().toString(), account.getCreateDate());
				return dto;
			}
		});
		return new ResponseEntity<>(dtoPage, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getAccountByID(@PathVariable(name = "id") short id) {
		Account account = accountService.getAccountById(id);
		AccontDto dto = new AccontDto(account.getId(), account.getEmail(), account.getUsername(), account.getFullname(),
				account.getDepartment().getName(), account.getPosition().getName().toString(), account.getCreateDate());
		return new ResponseEntity<AccontDto>(dto, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> createAccount(@RequestBody AccountFormForCreating form) {
		Account accountNew = accountService.createAccount(form);
		AccontDto accountNewDto = new AccontDto(accountNew.getId(), accountNew.getEmail(), accountNew.getUsername(),
				accountNew.getFullname(), accountNew.getDepartment().getName(),
				accountNew.getPosition().getName().toString(), accountNew.getCreateDate());
		return new ResponseEntity<>(accountNewDto, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateAccount(@PathVariable(name = "id") short id,
			@RequestBody AccountFormForUpdating form) {
		Account accountUpdate = accountService.updateAccount(id, form);
		AccontDto accountUpdateDto = new AccontDto(accountUpdate.getId(), accountUpdate.getEmail(),
				accountUpdate.getUsername(), accountUpdate.getFullname(), accountUpdate.getDepartment().getName(),
				accountUpdate.getPosition().getName().toString(), accountUpdate.getCreateDate());
		return new ResponseEntity<>(accountUpdateDto, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable(name = "id") short id) {
//		accountService.deleteAccount(id);
//		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);

		try {
//			Thực hiện lấy thông tin chi tiết của Account cần xóa, sau khi xóa xong thực hiện trả về thông tin chi tiết của Account vừa xóa
			Account accountDelete = accountService.getAccountById(id);

//			Convert
			AccontDto accountDeleteDto = new AccontDto(accountDelete.getId(), accountDelete.getEmail(),
					accountDelete.getUsername(), accountDelete.getFullname(), accountDelete.getDepartment().getName(),
					accountDelete.getPosition().getName().toString(), accountDelete.getCreateDate());

//			Xóa Account
			accountService.deleteAccount(id);

			return new ResponseEntity<>(accountDeleteDto, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
		}

	}

}
