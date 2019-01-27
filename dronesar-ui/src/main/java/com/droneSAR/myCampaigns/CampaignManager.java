package com.droneSAR.myCampaigns;

import com.droneSAR.MainLayout;
import com.droneSAR.backend.Campaign;
import com.droneSAR.backend.CampaignStore;
import com.droneSAR.backend.DroneClip;
import com.droneSAR.backend.ReviewPriority;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Route(value = "Manage", layout = MainLayout.class)
@PageTitle("Manage a Campaign")
public class CampaignManager extends VerticalLayout {

    public static final String VIEW_NAME = "Manage";
    private static final String VIDEO_FP_PREFIX = "src/main/webapp/videos/";

    private Tabs tabs = new Tabs();

    private Map<Tab, Component> components = new LinkedHashMap<>();

    private FlexLayout content = new FlexLayout();

    private TextField campaignName;
    private Upload upload;
    private Button save;
    private Button delete;
    private Button back;

    private MemoryBuffer buffer;

    public CampaignManager() {
        back = new Button("Done");
        back.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        back.addClickListener(event -> returnToList());

        HorizontalLayout horizontal = new HorizontalLayout();
        horizontal.setWidth("100%");
        tabs.setWidth("100%");

        horizontal.add(tabs);
        horizontal.add(back);
        back.getStyle().set("margin-left", "auto");

        add(horizontal);
        add(content);
        setWidth("100%");

        setFlexGrow(1, content);

        tabs.addSelectedChangeListener(this::onTabChanged);



        addTab("Overview", setoverviewLayout());
        addTab("Search Area", new Label("hi2"));
        addTab("Upload Footage", setUploadView());
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

    private VerticalLayout setoverviewLayout(){
        VerticalLayout layout = new VerticalLayout();

        Label campaignLabel = new Label("Campaign Name: ");
        Label campaignID = new Label("Campaign ID: ");
        Label crowdReviewers = new Label("Number of Reviewers:  ");

        layout.add(campaignID, campaignLabel, crowdReviewers);
        return layout;
    }

    private VerticalLayout setUploadView(){
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("100%");
        campaignName = new TextField("Campaign Name");
        campaignName.setWidth("100%");
        campaignName.setRequired(true);
        campaignName.setValueChangeMode(ValueChangeMode.EAGER);
        layout.add(campaignName);

        buffer = new MemoryBuffer();
        upload = new Upload(buffer);
        upload.setAcceptedFileTypes("video/mp4");
        layout.add(upload);

        save = new Button("Save");
        save.setWidth("100%");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickListener(event -> {
            saveData();
        });

        delete = new Button("Delete");
        delete.setWidth("100%");
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
//        delete.addClickListener(event -> {
//            if (currentProduct != null) {
//                viewLogic.deleteProduct(currentProduct);
//            }
//        });
        layout.add(save, delete);
        return layout;
    }

    public void saveData(){
        try {
            InputStream stream = buffer.getInputStream();
            byte[] bufferByte = new byte[stream.available()];
            stream.read(bufferByte);
            File targetFile = new File(VIDEO_FP_PREFIX + campaignName.getValue() + "_initial.mp4");
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(bufferByte);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Campaign campaign = new Campaign(campaignName.getValue());
        File droneVideo = new File(VIDEO_FP_PREFIX + campaignName.getValue() + "_initial.mp4");
        campaign.addDroneClip(new DroneClip(droneVideo, ReviewPriority.HIGH));
        CampaignStore.getInstance().addCampaign(campaign);

    }

    private void returnToList(){
        getUI().get().navigate("MyCampaigns");
    }
}
