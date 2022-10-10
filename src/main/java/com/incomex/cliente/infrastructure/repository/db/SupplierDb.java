package com.incomex.cliente.infrastructure.repository.db;

import com.incomex.cliente.application.port.input.repository.db.ISupplierDb;
import com.incomex.cliente.domain.SupplierDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SupplierDb implements ISupplierDb {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @param  id busca un Supplier por el id si no la encuentra retorna null
     * @return  retorna una categoria si existe, si no un null*/
    @Override
    public SupplierDomain getById(int id) {

        List<SupplierDomain> query = jdbcTemplate.query(
                "Select SupplierID, CompanyName, ContactName,ContactTitle,Address,City,Region,PostalCode," +
                        "Country,Phone,Fax,HomePage" +
                        " from Suppliers where SupplierID=?",
                new BeanPropertyRowMapper<>(SupplierDomain.class), id);
        return query.stream().findAny().orElse(null);
    }
}
