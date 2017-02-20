package com.druid;

import com.druid.model.Account;

import java.util.List;

/**
 * Created by druid on 17-2-20.
 */
public interface AccountService {
    List<Account> queryAccountList(Account account);
    void testDubbo();
}
