package com.incomex.cliente.infrastructure.repository.db;

import com.incomex.cliente.application.port.input.repository.db.ICategoryDb;
import com.incomex.cliente.domain.CategoryDomain;
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
public class CategoryDb implements ICategoryDb {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @param category recibe un objeto de categoria domain. Solo es requerido el name
     * @return retorna el Id del registro
     */
    @Override
    public int create(CategoryDomain category) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = con -> {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Categories (CategoryName,Description,Picture) value ( ?,?,?)");
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setString(3, category.getPictureBase64());
            return ps;
        };

        jdbcTemplate.update(psc, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    /**
     * @param categoryName busca una categoria por el nombre si no la encuentra retorna null
     * @return retorna una categoria si existe, si no un null
     */
    @Override
    public CategoryDomain getByName(String categoryName) {

        List<CategoryDomain> query = jdbcTemplate.query("Select  CategoryID, CategoryName,Description,Picture from Categories where CategoryName=?", new BeanPropertyRowMapper<>(CategoryDomain.class), categoryName);
        return query.stream().findAny().orElse(null);
    }

    /**
     * @param id busca una categoria por el id si no la encuentra retorna null
     * @return retorna una categoria si existe, si no un null
     */
    @Override
    public CategoryDomain getById(int id) {

        List<CategoryDomain> query = jdbcTemplate.query("Select  CategoryID, CategoryName,Description,Picture from Categories where CategoryID=?", new BeanPropertyRowMapper<>(CategoryDomain.class), id);
        return query.stream().findAny().orElse(null);
    }
}
