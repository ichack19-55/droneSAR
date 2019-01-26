package com.droneSAR.discover;

import com.droneSAR.MainLayout;
import com.droneSAR.crud.ProductDataProvider;
import com.droneSAR.crud.ProductGrid;
import com.droneSAR.crud.SampleCrudLogic;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "DiscoverCampaigns", layout = MainLayout.class)
@PageTitle("Discover Campaigns")
public class DiscoverCampaigns extends HorizontalLayout {

    public static final String VIEW_NAME = "Discover Campaigns";

    private ProductGrid grid;
    private TextField filter;

    //private SampleCrudLogic viewLogic = new SampleCrudLogic(this);
    private ProductDataProvider dataProvider = new ProductDataProvider();

    public DiscoverCampaigns() {
        setSizeFull();

        HorizontalLayout topLayout = createTopBar();

        grid = new ProductGrid();
        //grid.asSingleSelect().addValueChangeListener(
                //event -> viewLogic.rowSelected(event.getValue()));

        VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.add(topLayout);
        barAndGridLayout.add(grid);
        barAndGridLayout.setFlexGrow(1, grid);
        barAndGridLayout.setFlexGrow(0, topLayout);
        barAndGridLayout.setSizeFull();
        barAndGridLayout.expand(grid);

        add(barAndGridLayout);
    }

    public HorizontalLayout createTopBar() {
        filter = new TextField();
        filter.setPlaceholder("Filter name, availability or category");
        // Apply the filter to grid's data provider. TextField value is never null
        filter.addValueChangeListener(event -> dataProvider.setFilter(event.getValue()));

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth("100%");
        topLayout.add(filter);
        topLayout.setVerticalComponentAlignment(Alignment.START, filter);
        topLayout.expand(filter);
        return topLayout;
    }

}
