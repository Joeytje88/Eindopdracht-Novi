package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.Account;;
import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.exception.AccountException;
import nl.tipsntricks.games.exception.UserNotFoundException;
import nl.tipsntricks.games.repository.AccountRepository;
import nl.tipsntricks.games.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccountById(long accountid) {
        return accountRepository.findById(accountid)
                .orElseThrow(()-> new AccountException("Account met id " +accountid +" niet gevonden" ));
    }


    @Override
    public Account addAccount(Account newAccount) {
            String accountUrl = newAccount.getAccountUrl();
            if (checkIsValidAccountName(accountUrl)) {
                return accountRepository.save(newAccount);
            } throw new AccountException("account bestaat niet");
    }

    @Override
    public Account addAccountToUser(long userid, Account newAccount) {
       Optional<AppUser> user = appUserRepository.findById(userid);
       if (user.isPresent()) {
           AppUser userAccount = user.get();
           Account account = userAccount.getAccount();

           newAccount.setUserAccount(userAccount);
           userAccount.setAccount(account);

           return accountRepository.save(newAccount);
       }
       throw new AccountException("Account bestaat niet");
    }

    @Override
    public Account updateAccountById(long accountid, Account updatedAccount) {
       return accountRepository.findById(accountid).map(
               account -> {
                   account.setAccountUrl(account.getAccountUrl());
                   account.setImage(account.getImage());
                   account.setUserAccount(account.getUserAccount());
                   return accountRepository.save(account);
               }).orElseGet(() ->{
                   updatedAccount.setAccountid(accountid);
                   return accountRepository.save(updatedAccount);
               });
    }

    @Override
    public String deleteAccountById(long accountid) {
        Optional<Account> account = accountRepository.findById(accountid);
        if (account.isPresent()) {
            accountRepository.deleteById(accountid);
            return "Account met id " + account.get().getAccountid() + " is verwijderd";
        }
        throw new AccountException("Account bestaat niet");
    }

    @Override
    public Boolean checkIsValidAccountName(String accountName) {
        if (accountName.contains("fuck") || accountName.equalsIgnoreCase(" ")) {
            return false; }
        return true;
    }


}
