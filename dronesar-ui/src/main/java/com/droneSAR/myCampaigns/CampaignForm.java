package com.droneSAR.myCampaigns;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.component.upload.Upload;

import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;


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


        MultiFileMemoryBuffer fileBuffer = new MultiFileMemoryBuffer();
        upload = new Upload(fileBuffer);
        upload.setAcceptedFileTypes("video/mp4");
        upload.setReceiver(receiver);
        //receiver.receiveUpload(fileBuffer.getFileData(), )

        content.add(upload);

        save = new Button("Save");
        save.setWidth("100%");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        save.addClickListener(event -> {
//            if (currentProduct != null
//                    && binder.writeBeanIfValid(currentProduct)) {
//                viewLogic.saveProduct(currentProduct);
//            }
//        });


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

//    public HorizontalLayout uploadFiles(){
//
//
//        HorizontalLayout layout = new HorizontalLayout();
//        layout.setWidth("100%");
//        layout.add(upload);
//        return layout;
//    }
}
