package com.StraCode.finance.services;

import com.StraCode.finance.adapters.factories.AccountCategoriesFactory;
import com.StraCode.finance.domain.model.AccountCategories;
import com.StraCode.finance.domain.repositories.AccountCategoriesRepo;
import com.StraCode.finance.dto.AccountCategoriesDto;
import com.StraCode.finance.exceptions.AccountNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountCategoriesServiceTest {

    @InjectMocks
    private AccountCategoriesService service;

    @Mock
    private AccountCategoriesRepo repo;

    private AccountCategoriesDto dto;
    private AccountCategories entity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dto = new AccountCategoriesDto();
        dto.setName("Utilities");
        dto.setDescription("Utility bills");

        entity = new AccountCategories();
        entity.setName("Utilities");
        entity.setDescription("Utility bills");
        // Note: We no longer set the ID here
    }

    @Test
    @DisplayName("Should create a new account category successfully")
    void testCreateAccountCategories() {
        when(repo.save(any(AccountCategories.class))).thenReturn(entity);

        AccountCategoriesDto result = service.createAccountCategories(dto);

        assertEquals(dto.getName(), result.getName());
        verify(repo, times(1)).save(any(AccountCategories.class));
    }

    @Test
    @DisplayName("Should return all account categories")
    void testFindAll() {
        when(repo.findAll()).thenReturn(List.of(entity));

        List<AccountCategoriesDto> result = service.findAll();

        assertEquals(1, result.size());
        assertEquals(entity.getName(), result.get(0).getName());
        verify(repo, times(1)).findAll();
    }

    @Test
    @DisplayName("Should update an existing account category")
    void testUpdateAccountCategories_Success() {
        UUID id = UUID.randomUUID();

        AccountCategories existing = new AccountCategories();
        existing.setName("Old Name");
        existing.setDescription("Old Description");

        when(repo.findById(id)).thenReturn(Optional.of(existing));
        when(repo.save(any(AccountCategories.class))).thenReturn(existing);

        dto.setName("New Name");
        dto.setDescription("New Description");

        AccountCategoriesDto updated = service.updateAccountCategories(id, dto);

        assertEquals("New Name", updated.getName());
        assertEquals("New Description", updated.getDescription());
        verify(repo).save(existing);
    }

    @Test
    @DisplayName("Should throw exception when updating a non-existent account category")
    void testUpdateAccountCategories_NotFound() {
        UUID id = UUID.randomUUID();
        when(repo.findById(id)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> service.updateAccountCategories(id, dto));
        verify(repo, never()).save(any());
    }

    @Test
    @DisplayName("Should delete an existing account category")
    void testDeleteAccountCategories_Success() {
        UUID id = UUID.randomUUID();
        when(repo.findById(id)).thenReturn(Optional.of(entity));

        service.deleteAccountCategories(id);

        verify(repo).delete(entity);
    }

    @Test
    @DisplayName("Should throw exception when deleting a non-existent account category")
    void testDeleteAccountCategories_NotFound() {
        UUID id = UUID.randomUUID();
        when(repo.findById(id)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> service.deleteAccountCategories(id));
        verify(repo, never()).delete(any());
    }
}
