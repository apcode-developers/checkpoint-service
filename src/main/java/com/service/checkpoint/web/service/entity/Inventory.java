package com.service.checkpoint.web.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    private String id;
    private String inventoryName;
    private Integer idCategory;
    private String namaKategori;
    private String idAdmin;
    private String adminInventory;
    private Timestamp tglUpload;
    private Timestamp lastUpdate;
}
