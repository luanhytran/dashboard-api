package com.luanhydev.dashboard.dashboard;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class DashboardService {

    private final DashboardRepository dashboardRepository;

    public List<Dashboard> getDashboards() {
        return dashboardRepository.findAll();
    }

    @Transactional
    public void updateDashboard(Long dashboardId, Dashboard dashboard) {
        Dashboard currentDashboard = dashboardRepository.findById(dashboardId).orElseThrow(() -> {
            throw new IllegalStateException("dashboard with id " + dashboardId + " does not exists");
        });

        if(dashboard.getUserId() != null && dashboard.getUserId() > 0) {
            currentDashboard.setUserId(dashboard.getUserId());
        }

        if(dashboard.getTitle() != null && dashboard.getTitle().length() > 0) {
            currentDashboard.setTitle(dashboard.getTitle());
        }

        if(dashboard.getLayoutType() != null && dashboard.getLayoutType().length() > 0) {
            currentDashboard.setLayoutType(dashboard.getLayoutType());
        }

        if(dashboard.getWidget() != null && (long) dashboard.getWidget().size() > 0) {
            currentDashboard.setWidget(dashboard.getWidget());
        }
    }
}
