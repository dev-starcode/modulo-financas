package com.StraCode.finance.domain.repositories;

import com.StraCode.finance.domain.model.AccountReceivables;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountReceivablesRepo extends JpaRepository<AccountReceivables, UUID> {

}
