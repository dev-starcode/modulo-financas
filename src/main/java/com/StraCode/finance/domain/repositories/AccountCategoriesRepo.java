package com.StraCode.finance.domain.repositories;

import com.StraCode.finance.domain.model.AccountCategories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountCategoriesRepo extends JpaRepository<AccountCategories, UUID> {
}
