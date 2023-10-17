package com.miniapps.xmart.repository;

import com.miniapps.xmart.model.Barang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BarangRepository extends JpaRepository<Barang, Long>, JpaSpecificationExecutor<Barang> {
    Barang findByRfid(String rfid);
}
