package com.flexPerk.flexCore.repository;

import com.flexPerk.flexCore.model.Employer;
import com.flexPerk.flexCore.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
