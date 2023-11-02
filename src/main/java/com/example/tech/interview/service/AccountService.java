package com.example.tech.interview.service;

import com.example.tech.interview.dao.entity.AccountEntity;
import com.example.tech.interview.dao.repo.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AccountService {

    @Value("{account.accountTypes}")
    private List<String> accountTypes;

    @Autowired
    private AccountRepository accountRepository;

    /**
     * This method creates a new Account
     * @param param The Account object to be created
     * @return AccountEntity The newly created object.
     */
    public AccountEntity createAccount(AccountEntity param) throws Exception {
        validateCreate(param);
        AccountEntity var = accountRepository.save(param);
        log.info("Account Created: {}", var);
        return var;
    }

    /**
     * This method retrieves an Account
     * @param accountId The identifier of the Account
     * @return AccountEntity The Account identified by the Account Id.
     */
    public AccountEntity ReadAccount(Long accountId) {
        return accountRepository.findByAccountId(accountId);
    }

    /**
     * This method retrieves all Accounts in the DB
     * @return List<AccountEntity> A collection of all Accounts.
     */
    public List<AccountEntity> readAllAccounts() {
        List<AccountEntity> entities = new ArrayList<>();
        accountRepository.findAll().forEach(entities::add);
        return entities;
    }

    /**
     * This method updates an existing Account
     * @param accountEntity The request object that contains the updated details of the Account.
     * @return AccountEntity The updated Account.
     */
    public AccountEntity updateAccount(AccountEntity accountEntity) throws Exception {
        validateUpdate(accountEntity);
        Optional<AccountEntity> optional = accountRepository.findById(accountEntity.getId());
        if (optional.isPresent()) {
            AccountEntity existingAccount = optional.get();
            existingAccount.setFirstName(accountEntity.getFirstName());
            existingAccount.setLastName(accountEntity.getLastName());
            existingAccount.setAddress(accountEntity.getAddress());
            existingAccount.setIndividualAccount(accountEntity.isIndividualAccount());
            existingAccount.setAccountType(accountEntity.getAccountType());
            return accountRepository.save(existingAccount);
        }
        log.warn("Account not found");
        return accountEntity;
    }

    /**
     * This method deletes an Account for the DB
     */
    public void deleteAccount(Long accountId) throws Exception {
        accountRepository.findById(accountId).orElseThrow(() -> new Exception("Account not found"));
        accountRepository.deleteById(accountId);
    }

    /**
     * This method performs validation for creating an Account
     * @param accountEntity The Account to be validated
     */
    private void validateCreate(AccountEntity accountEntity) throws Exception {
        validate(accountEntity);
    }

    /**
     * This method performs validation for updating an Account
     * @param accountEntity The Account to be validated
     */
    private void validateUpdate(AccountEntity accountEntity) throws Exception {
        if (accountEntity.getId() == null) {
            throw new Exception("Account Id is missing");
        }
        validate(accountEntity);
    }

    /**
     * This method performs validation of an Account
     * @param accountEntity The Account to be validated
     */
    private void validate(AccountEntity accountEntity) throws Exception {
        if (accountEntity.getAddress() == null) {
            throw new Exception("Address is missing");
        } else if (accountEntity.getAddress().getAddressLine1() == null) {
            throw new Exception("Address Line 1 is missing");
        } else if (accountEntity.getAddress().getCity() == null) {
            throw new Exception("City is missing");
        } else if (accountEntity.getAddress().getState() == null) {
            throw new Exception("State is missing");
        } else if (accountEntity.getAddress().getPostalCode() == null) {
            throw new Exception("Postal Code is missing");
        } else if (accountEntity.getAccountType() == null) {
            throw new Exception("Account Type is missing");
        } else if (!accountTypes.contains(accountEntity.getAccountType())) {
            throw new Exception("Account Type provided is invalid");
        }
    }

}
