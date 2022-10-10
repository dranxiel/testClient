package com.incomex.cliente.infrastructure.repository.db;

import com.incomex.cliente.application.port.input.repository.db.IProductDb;
import com.incomex.cliente.domain.ProductDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductDb implements IProductDb {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @param productDomain recibe un objeto de producto domain. Solo es requerido el name
     * @return retorna el Id del registro
     */
    @Override
    public int create(ProductDomain productDomain) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = con -> {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Products (ProductName,SupplierID,CategoryID,QuantityPerUnit," +
                            "UnitsPrice,UnitsInStock,UnitsOnOrder,ReorderLevel,Discontinued)" +
                            " value ( ?,?,?,?,?,?,?,?,?)");
            ps.setString(1, productDomain.getName());
            ps.setInt(2, productDomain.getSupplierID());
            ps.setInt(3, productDomain.getCategoryID());
            ps.setInt(4, productDomain.getQuantityPerUnit());
            ps.setDouble(5, productDomain.getUnitsPrice());
            ps.setInt(6, productDomain.getUnitsInStock());
            ps.setInt(7, productDomain.getUnitsOnOrder());
            ps.setInt(8, productDomain.getReorderLevel());
            ps.setBoolean(9, productDomain.getDiscontinued());

            return ps;
        };

        jdbcTemplate.update(psc, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    /**
     * @param productName busca un producto por el nombre si no la encuentra retorna null
     * @return retorna una producto si existe, si no un null
     */
    @Override
    public ProductDomain getByProductName(String productName) {

        List<ProductDomain> query = jdbcTemplate.query("Select ProductID, ProductName,SupplierID,CategoryID,QuantityPerUnit," +
                "                            UnitsPrice,UnitsInStock,UnitsOnOrder,ReorderLevel,Discontinued from Products " +
                "where CategoryName=?", new BeanPropertyRowMapper<>(ProductDomain.class), productName);
        return query.stream().findAny().orElse(null);
    }

    /**
     * @param id busca un producto por el id si no la encuentra retorna null
     * @return retorna una producto si existe, si no un null
     */
    @Override
    public ProductDomain getByProductID(int id) {

        List<ProductDomain> query = jdbcTemplate.query("Select ProductID, ProductName,SupplierID,CategoryID,QuantityPerUnit," +
                "                            UnitsPrice,UnitsInStock,UnitsOnOrder,ReorderLevel,Discontinued from Products " +
                "where ProductID=?", new BeanPropertyRowMapper<>(ProductDomain.class), id);
        return query.stream().findAny().orElse(null);
    }

    /**
     * @param offset parametro de ini de busqueda
     * @param fetch cantidad de registros a tomar
     * @return retorna una producto si existe, si no un null
     */
    @Override
    public List<ProductDomain> getByProducts(int offset, int fetch) {

        return jdbcTemplate.query("Select ProductID, ProductName,SupplierID,CategoryID,QuantityPerUnit," +
                "                            UnitsPrice,UnitsInStock,UnitsOnOrder,ReorderLevel,Discontinued from Products " +
                "where OFFSET ? ROWS FETCH NEXT ? ROWS ONLY", new BeanPropertyRowMapper<>(ProductDomain.class), offset,fetch);
    }
}
