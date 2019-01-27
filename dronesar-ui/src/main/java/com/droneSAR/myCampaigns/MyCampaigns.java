package com.droneSAR.myCampaigns;

import com.droneSAR.MainLayout;
import com.droneSAR.backend.Campaign;
import com.droneSAR.backend.CampaignStore;
import com.droneSAR.backend.ClipBeingReviewed;
import com.droneSAR.review.ReviewFootageView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "MyCampaigns", layout = MainLayout.class)
@PageTitle("My Campaigns")
public class MyCampaigns extends HorizontalLayout {

    public static final String VIEW_NAME = "Campaigns";

    private TextField filter;
    private CampaignForm form;
    private Button newCampaign;
    private Grid<Campaign> grid;

    public MyCampaigns() {
        setSizeFull();
        form = new CampaignForm(this);
        showForm(false);
        HorizontalLayout topLayout = createTopBar();
        VerticalLayout barAndGridLayout = new VerticalLayout();

        grid = new Grid<>();
        grid.setItems(CampaignStore.getInstance().getCampaignStore());
        grid.addColumn(Campaign::getName).setHeader("Campaign Name");
        grid.addColumn(Campaign::getCampaignId).setHeader("Campaign ID");
        grid.addColumn(new NativeButtonRenderer<>("Review footage", this::goToReview));

        barAndGridLayout.add(topLayout);
        barAndGridLayout.add(grid);
        barAndGridLayout.setFlexGrow(1, grid);
        barAndGridLayout.setFlexGrow(0, topLayout);
        barAndGridLayout.setSizeFull();
        barAndGridLayout.expand(grid);
        add(barAndGridLayout);
        add(form);
    }

    private HorizontalLayout createTopBar() {
        filter = new TextField();
        filter.setPlaceholder("Filter name, availability or category");
        // Apply the filter to grid's data provider. TextField value is never null

        HorizontalLayout topLayout = new HorizontalLayout();

        newCampaign = new Button("Add Campaign");
        newCampaign.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        newCampaign.setIcon(VaadinIcon.PLUS_CIRCLE.create());
        newCampaign.addClickListener(event -> showForm(true));
        //        Disable if user already has campaign
        //        if(user has campaign){
        //            newCampaign.isEnabled(false);
        //        }

        topLayout.setWidth("100%");
        topLayout.add(filter);
        topLayout.add(newCampaign);
        topLayout.setVerticalComponentAlignment(Alignment.START, filter);
        topLayout.expand(filter);
        return topLayout;
    }

    public void showForm(boolean show) {
        form.setVisible(show);
    }

    // Set up data for review footage view
    private void goToReview(Campaign c) {
        ClipBeingReviewed.getInstance().setDroneClip(c.droneClip);
        ClipBeingReviewed.getInstance().setCampaignName(c.getName());
        getUI().get().navigate("ReviewFootage");
    }
}
