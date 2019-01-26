package com.droneSAR.discover;

import com.droneSAR.MainLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "DiscoverCampaigns", layout = MainLayout.class)
@PageTitle("Discover Campaigns")
public class DiscoverCampaigns extends HorizontalLayout {

    public static final String VIEW_NAME = "Discover Campaigns";

    public DiscoverCampaigns() {
        add(new Span("Discover campaigns here..."));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
    }

}
