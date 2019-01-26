package com.droneSAR.myCampaigns;

import com.droneSAR.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "MyCampaigns", layout = MainLayout.class)
@PageTitle("My Campaigns")
public class MyCampaigns extends HorizontalLayout {

    public static final String VIEW_NAME = "My Campaigns";

    private TextField filter;
    private CampaignForm form;
    private Button newCampaign;


    public MyCampaigns() {
        setSizeFull();
        form = new CampaignForm(this);
        showForm(false);
        HorizontalLayout topLayout = createTopBar();
        VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.add(topLayout);
        barAndGridLayout.setFlexGrow(0, topLayout);
        barAndGridLayout.setSizeFull();
        add(barAndGridLayout);
        add(form);
    }

    public HorizontalLayout createTopBar() {
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

    public void showForm(boolean show){
        form.setVisible(show);
    }
}
