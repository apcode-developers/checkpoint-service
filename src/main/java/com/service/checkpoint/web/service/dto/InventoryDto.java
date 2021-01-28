package com.service.checkpoint.web.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

public class InventoryDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class New {
        private String id;
        private String inventoryName;
        private Integer idCategory;
        private String idAdmin;
        private Timestamp tglUpload;
        private Timestamp lastUpdate;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {

        private String id;
        private String inventoryName;
        private Integer idCategory;
        private String idAdmin;
        private Timestamp lastUpdate;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Information {
        private String id;
        private String inventoryName;
        private Integer idCategory;
        private String namaKategori;
        private String idAdmin;
        private String adminInventory;
        private Timestamp tglUpload;
        private Timestamp lastUpdate;
    }
}
