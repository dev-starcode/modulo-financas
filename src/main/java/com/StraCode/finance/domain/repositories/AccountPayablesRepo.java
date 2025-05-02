package com.StraCode.finance.domain.repositories;

import com.StraCode.finance.domain.model.AccountPayables;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountPayablesRepo extends JpaRepository<AccountPayables, UUID> {
}
