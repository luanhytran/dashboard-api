package com.luanhydev.dashboard.dashboard;

import com.luanhydev.dashboard.widget.Widget;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Dashboard")
@Table(name = "dashboard")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dashboard {
    @Id
    @SequenceGenerator(
            name = "dashboard_sequence",
            sequenceName = "dashboard_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dashboard_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "user_id",
            nullable = false
    )
    private Long userId;

    @Column(
            name = "title",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String title;

    @Column(
            name = "layout_type",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String layoutType;

    @OneToMany(
            mappedBy = "dashboard",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<Widget> widget = new ArrayList<>();

    public Dashboard(Long userId, String title, String layoutType) {
        this.userId = userId;
        this.title = title;
        this.layoutType = layoutType;
    }

    public Dashboard(Long userId, String title, String layoutType, List<Widget> widget) {
        this.userId = userId;
        this.title = title;
        this.layoutType = layoutType;
        this.widget = widget;
    }

    public void addWidget(Widget widget) {
        if(!this.widget.contains(widget)) {
            this.widget.add(widget);
            widget.setDashboard(this);
        }
    }

    public void removeWidget(Widget widget) {
        if(this.widget.contains(widget)) {
           this.widget.remove(widget);
           widget.setDashboard(null);
        }
    }
}
