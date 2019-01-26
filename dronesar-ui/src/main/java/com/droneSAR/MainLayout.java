package com.droneSAR;

import com.droneSAR.discover.DiscoverCampaigns;
import com.droneSAR.myCampaigns.MyCampaigns;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.droneSAR.review.ReviewFootageView;

/**
 * The layout of the pages e.g. About and Inventory.
 */
@HtmlImport("css/shared-styles.html")
@Theme(value = Lumo.class)
@PWA(name = "Bookstore Starter", shortName = "Bookstore")
public class MainLayout extends FlexLayout implements RouterLayout {
    private Menu menu;

    public MainLayout() {
        setSizeFull();
        setClassName("main-layout");

        menu = new Menu();
        menu.addView(DiscoverCampaigns.class, DiscoverCampaigns.VIEW_NAME, VaadinIcon.SEARCH.create());
        menu.addView(ReviewFootageView.class, ReviewFootageView.VIEW_NAME, VaadinIcon.FILM.create());
        menu.addView(MyCampaigns.class, MyCampaigns.VIEW_NAME, VaadinIcon.USER.create());
        add(menu);
    }
}
