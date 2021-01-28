package com.service.checkpoint.web.service.service;

import com.service.checkpoint.web.service.dao.InventoryDao;
import com.service.checkpoint.web.service.dto.InventoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventoryService {

    @Autowired
    private InventoryDao dao;

    public int save(InventoryDto.New value) throws SQLException{
        value.setId(UUID.randomUUID().toString());
        value.setTglUpload(Timestamp.valueOf(LocalDateTime.now()));
        value.setLastUpdate(value.getTglUpload());
        return dao.save(value);
    }

    public void delete(String id) throws DataAccessException{
        dao.delete(id);
    }

    public void update(InventoryDto.Update value) throws DataAccessException{
        value.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        dao.update(value);
    }

    public Optional<InventoryDto.Information> findById(String id) throws EmptyResultDataAccessException{
        return dao.findById(id);
    }

    public List<InventoryDto.Information> findAll() throws EmptyResultDataAccessException{
        return dao.findAll();
    }


}
