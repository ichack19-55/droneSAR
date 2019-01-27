package com.droneSAR.myCampaigns;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.component.upload.Upload;

import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.component.upload.receivers.FileData;


import java.io.*;


public class CampaignForm extends Div {
    private VerticalLayout content;

    private TextField productName;
    private TextField price;
    private TextField stockCount;
    private Upload upload;
    private Button save;
    private Button cancel;
    private Button delete;
    private MyCampaigns MyCamp;
    private Receiver receiver;
    private MemoryBuffer buffer;

    public CampaignForm(MyCampaigns mycamp) {
        MyCamp = mycamp;
        setClassName("product-form");
        content = new VerticalLayout();
        content.setSizeUndefined();
        add(content);

        productName = new TextField("Campaign Name");
        productName.setWidth("100%");
        productName.setRequired(true);
        productName.setValueChangeMode(ValueChangeMode.EAGER);
        content.add(productName);


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

    public void saveData(){
        try {
            InputStream stream = buffer.getInputStream();
            byte[] bufferByte = new byte[stream.available()];
            stream.read(bufferByte);
            File targetFile = new File(productName.getValue() + ".mp4");
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(bufferByte);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

}
