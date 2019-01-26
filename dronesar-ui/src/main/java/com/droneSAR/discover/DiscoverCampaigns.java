package com.droneSAR.discover;

import com.droneSAR.MainLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "DiscoverCampaigns", layout = MainLayout.class)
@PageTitle("Discover Campaigns")
public class DiscoverCampaigns extends HorizontalLayout {

    public static final String VIEW_NAME = "Discover Campaigns";

    private TextField filter;

    public DiscoverCampaigns() {
        setSizeFull();

        HorizontalLayout topLayout = createTopBar();

        //grid.asSingleSelect().addValueChangeListener(
                //event -> viewLogic.rowSelected(event.getValue()));

        VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.add(topLayout);
        barAndGridLayout.setFlexGrow(0, topLayout);
        barAndGridLayout.setSizeFull();

        add(barAndGridLayout);
    }

    public HorizontalLayout createTopBar() {
        filter = new TextField();
        filter.setPlaceholder("Filter name, availability or category");
        // Apply the filter to grid's data provider. TextField value is never null

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth("100%");
        topLayout.add(filter);
        topLayout.setVerticalComponentAlignment(Alignment.START, filter);
        topLayout.expand(filter);
        return topLayout;
    }

}
