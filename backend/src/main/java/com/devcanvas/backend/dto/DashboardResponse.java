package com.devcanvas.backend.dto;

import lombok.Data;

@Data
public class DashboardResponse {
    private long total;

    private long pending;

    private long processing;

    private long sent;

    private long failed;
}
