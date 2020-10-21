package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.Account;
import nl.tipsntricks.games.repository.AccountRepository;
import nl.tipsntricks.games.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private IAccountService accountService;

    @GetMapping(value= "/api/accounts")
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }


    @GetMapping (value= "/api/account/{accountid}")
    public Account getAccountById(@PathVariable long accountid){
        return accountService.getAccountById(accountid);
    }

    @PostMapping (value = "/api/account")
    public Account addAccount(@RequestBody Account newAccount){
        return accountService.addAccount(newAccount);
    }

    @PutMapping (value = "/api/account/user/{userid}")
    public Account addAccountToUser (@PathVariable long userid, @RequestBody Account newAccount){
        return accountService.addAccountToUser(userid, newAccount);
    }
    @PutMapping(value = "api/account/{accountid}")
    public Account updateAccountById (@PathVariable long accountid, Account updatedAccount){
        return accountService.updateAccountById(accountid, updatedAccount);
    }
    @DeleteMapping(value = "api/account/{accountid}")
    public String deleteAccountById (@PathVariable long accountid){
        return accountService.deleteAccountById(accountid);
    }

}
