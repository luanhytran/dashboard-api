package com.luanhydev.dashboard.widget;

import com.luanhydev.dashboard.dashboard.Dashboard;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Widget")
@Table(name = "widget")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Widget {
    @Id
    @SequenceGenerator(
            name = "widget_sequence",
            sequenceName = "widget_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "widget_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "title",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String title;

    @Column(
            name = "widget_type",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String widgetType;

    @Column(
            name = "min_width",
            nullable = false
    )
    private Integer minWidth;

    @Column(
            name = "min_height",
            nullable = false
    )
    private Integer minHeight;

//    @ElementCollection
//    private Map<String, String> configs;

    private String configs;

    @ManyToOne
    @JoinColumn(
            name = "dashboard_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "dashboard_widget_fk"
            )
    )
    @JsonBackReference
    private Dashboard dashboard;

    public Widget(String title, String widgetType, Integer minWidth, Integer minHeight) {
        this.title = title;
        this.widgetType = widgetType;
        this.minWidth = minWidth;
        this.minHeight = minHeight;
    }

    public Widget(String title, String widgetType, Integer minWidth, Integer minHeight, String configs) {
        this.title = title;
        this.widgetType = widgetType;
        this.minWidth = minWidth;
        this.minHeight = minHeight;
        this.configs = configs;
    }
}
