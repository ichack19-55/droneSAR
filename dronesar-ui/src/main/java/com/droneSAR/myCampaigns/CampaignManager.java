package com.droneSAR.myCampaigns;

import com.droneSAR.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Route(value = "Manage", layout = MainLayout.class)
@PageTitle("Manage a Campaign")
public class CampaignManager extends VerticalLayout {

    private Tabs tabs = new Tabs();

    private Map<Tab, Component> components = new LinkedHashMap<>();

    private FlexLayout content = new FlexLayout();

    public CampaignManager() {
        add(tabs);
        add(content);
        setWidth("100%");

        setFlexGrow(1, content);

        tabs.addSelectedChangeListener(this::onTabChanged);

        addTab("Overview", new Label("hi1"));
        addTab("Search Area", new Label("hi2"));
        addTab("Upload Footage", new Label("hi3"));
    }

    /*private Component overview() {

    }*/

    private Tab addTab(String caption, Component component) {
        Tab tab = new Tab(caption);
        tabs.add(tab);
        if (components.isEmpty()) {
            content.add(component);
        }
        components.put(tab, component);
        return tab;
    }

    private int getSelectedIndex() {
        return tabs.getSelectedIndex();
    }

    private void setSelectedIndex(int selectedIndex) {
        tabs.setSelectedIndex(selectedIndex);
    }

    private Tab getSelectedTab() {
        return tabs.getSelectedTab();
    }

    private void setSelectedTab(Tab selectedTab) {
        tabs.setSelectedTab(selectedTab);
    }

    private void onTabChanged(Tabs.SelectedChangeEvent e) {
        Tab selectedTab = e.getSource().getSelectedTab();
        content.removeAll();

        Optional.ofNullable(components.get(selectedTab)).ifPresent(content::add);
    }
}
