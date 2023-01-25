package com.luanhydev.dashboard.widget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WidgetRepository  extends JpaRepository<Widget, Long> {
}
