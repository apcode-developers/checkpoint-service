package com.service.checkpoint.web.service.dao;

import com.service.checkpoint.web.service.dto.InventoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class InventoryDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private JdbcTemplate template;

    public int save(InventoryDto.New value) throws SQLException {
        String baseQuery = "INSERT INTO inventory(id, inventory_name, id_category, id_admin, tgl_upload, last_update)" +
                "values(:id, :inventoryName, :idCategory, :idAdmin, :tglUpload, :lastUpdate)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", value.getId());
        parameterSource.addValue("inventoryName", value.getInventoryName());
        parameterSource.addValue("idCategory", value.getIdCategory());
        parameterSource.addValue("idAdmin", value.getIdAdmin());
        parameterSource.addValue("tglUpload", value.getTglUpload());
        parameterSource.addValue("lastUpdate", value.getLastUpdate());

        return jdbcTemplate.update(baseQuery,parameterSource);
    }

    public void delete(String id) throws DataAccessException {
        String baseQuery = "delete from inventory where id = :id";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);


        jdbcTemplate.update(baseQuery,parameterSource);
    }

    public void update(InventoryDto.Update value) throws DataAccessException{
        String baseQuery = "update inventory set inventory_name = :inventoryName, id_category = :idCategory," +
                "id_admin = :idAdmin, last_update = :lastUpdate where id = :id";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", value.getId());
        parameterSource.addValue("inventoryName", value.getInventoryName());
        parameterSource.addValue("idCategory", value.getIdCategory());
        parameterSource.addValue("idAdmin", value.getIdAdmin());
        parameterSource.addValue("lastUpdate", value.getLastUpdate());

        jdbcTemplate.update(baseQuery,parameterSource);
    }

    public Optional<InventoryDto.Information> findById(String id) throws EmptyResultDataAccessException {
        String baseQuery = "SELECT i.id as id, i.inventory_name as inventoryName, i.id_category as idCategory, \n" +
                "i.id_admin as idAdmin, i.tgl_upload  as tglUpload, i.last_update as lastUpdate, \n" +
                "CONCAT(ua.first_name,ua.last_name) as adminInventory, mki.nama_kategori as namaKategori \n" +
                "from inventory i left join user_admin ua on(i.id_admin = ua.id) \n" +
                "left join m_kategori_inventory mki on(i.id_category = mki.id); where i.id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        InventoryDto.Information arsip = jdbcTemplate.queryForObject(baseQuery, params, new RowMapper<InventoryDto.Information>() {
            @Override
            public InventoryDto.Information mapRow(ResultSet resultSet, int i) throws SQLException{
                InventoryDto.Information arsip = new InventoryDto.Information();
                arsip.setId(resultSet.getString("id"));
                arsip.setInventoryName(resultSet.getString("inventoryName"));
                arsip.setIdCategory(resultSet.getInt("idCategory"));
                arsip.setIdAdmin(resultSet.getString("idAdmin"));
                arsip.setTglUpload(resultSet.getTimestamp("tglUpload"));
                arsip.setLastUpdate(resultSet.getTimestamp("lastUpdate"));
                arsip.setAdminInventory(resultSet.getString("adminInventory"));
                arsip.setNamaKategori(resultSet.getString("namaKategori"));

                return arsip;
            }
        });
        return Optional.of(arsip);
    }

    public List<InventoryDto.Information> findAll() throws EmptyResultDataAccessException {
        String baseQuery = "SELECT i.id as id, i.inventory_name as inventoryName, i.id_category as idCategory, \n" +
                "i.id_admin as idAdmin, i.tgl_upload  as tglUpload, i.last_update as lastUpdate, \n" +
                "CONCAT(ua.first_name, ' ',ua.last_name) as adminInventory, mki.nama_kategori as namaKategori \n" +
                "from inventory i left join user_admin ua on(i.id_admin = ua.id) \n" +
                "left join m_kategori_inventory mki on(i.id_category = mki.id); ";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        return jdbcTemplate.query(baseQuery, parameterSource, new RowMapper<InventoryDto.Information>() {
            @Override
            public InventoryDto.Information mapRow(ResultSet resultSet, int i) throws SQLException {
                InventoryDto.Information arsip = new InventoryDto.Information();
                arsip.setId(resultSet.getString("id"));
                arsip.setInventoryName(resultSet.getString("inventoryName"));
                arsip.setIdCategory(resultSet.getInt("idCategory"));
                arsip.setIdAdmin(resultSet.getString("idAdmin"));
                arsip.setTglUpload(resultSet.getTimestamp("tglUpload"));
                arsip.setLastUpdate(resultSet.getTimestamp("lastUpdate"));
                arsip.setAdminInventory(resultSet.getString("adminInventory"));
                arsip.setNamaKategori(resultSet.getString("namaKategori"));

                return arsip;
            }
        });
    }

}
