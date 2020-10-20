package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.Account;

public interface IAccountService {
    Account getAccountById (long accountid);
    Account addAccount (Account newAccount);
    Account addAccountToUser (long userid, Account newAccount);
    Account updateAccountById (long accountid, Account updatedAccount);
    String deleteAccountById (long accountid);
    Boolean checkIsValidAccountName (String accountName);
}
