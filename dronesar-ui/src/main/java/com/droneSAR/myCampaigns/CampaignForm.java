package com.droneSAR.myCampaigns;

import com.droneSAR.backend.Campaign;
import com.droneSAR.backend.CampaignStore;
import com.droneSAR.backend.DroneClip;
import com.droneSAR.backend.ReviewPriority;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import java.io.*;

public class CampaignForm extends Div {

    private static final String VIDEO_FP_PREFIX = "src/main/webapp/videos/";

    private VerticalLayout content;
    private TextField campaignName;
    private Upload upload;
    private Button save;
    private Button cancel;
    private Button delete;
    private MyCampaigns MyCamp;
    private MemoryBuffer buffer;

    public CampaignForm(MyCampaigns mycamp) {
        MyCamp = mycamp;
        setClassName("product-form");
        content = new VerticalLayout();
        content.setSizeUndefined();
        add(content);

        campaignName = new TextField("Campaign Name");
        campaignName.setWidth("100%");
        campaignName.setRequired(true);
        campaignName.setValueChangeMode(ValueChangeMode.EAGER);
        content.add(campaignName);


        buffer = new MemoryBuffer();
        buffer.receiveUpload("out", ".mp4");
        upload = new Upload(buffer);
        upload.setAcceptedFileTypes("video/mp4");


        content.add(upload);

        save = new Button("Save");
        save.setWidth("100%");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickListener(event -> {
            saveData();
            MyCamp.showForm(false);
        });


        cancel = new Button("Cancel");
        cancel.setWidth("100%");
        cancel.addClickListener(event -> MyCamp.showForm(false));
//        getElement()
//                .addEventListener("keydown", event -> viewLogic.cancelProduct())
//                .setFilter("event.key == 'Escape'");

        delete = new Button("Delete");
        delete.setWidth("100%");
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
//        delete.addClickListener(event -> {
//            if (currentProduct != null) {
//                viewLogic.deleteProduct(currentProduct);
//            }
//        });

        content.add(save, delete, cancel);
    }

    private void saveData() {
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

        // Add to backend model
        Campaign campaign = new Campaign(campaignName.getValue());
        File droneVideo = new File(VIDEO_FP_PREFIX + campaignName.getValue() + "_initial.mp4");
        campaign.addDroneClip(new DroneClip(droneVideo, ReviewPriority.HIGH));
        CampaignStore.getInstance().addCampaign(campaign);
    }

}
