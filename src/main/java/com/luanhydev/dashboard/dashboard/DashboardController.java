package com.luanhydev.dashboard.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/dashboards")
public class DashboardController {

    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public List<Dashboard> getDashboards() {
        return dashboardService.getDashboards();
    }

    @PutMapping(path = "{dashboardId}")
    public void updateDashboard(@PathVariable("dashboardId") Long dashboardId, @RequestBody Dashboard dashboard) {
        dashboardService.updateDashboard(dashboardId, dashboard);
    }
}
