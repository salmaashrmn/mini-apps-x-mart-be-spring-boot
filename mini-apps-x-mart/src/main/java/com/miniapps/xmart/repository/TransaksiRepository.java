package com.miniapps.xmart.repository;

import com.miniapps.xmart.model.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransaksiRepository extends JpaRepository<Transaksi, Long>, JpaSpecificationExecutor<Transaksi> {
}
