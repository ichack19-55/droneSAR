package com.droneSAR.review;

import com.droneSAR.crud.ProductGrid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServletService;
import com.vaadin.flow.server.VaadinSession;

import com.droneSAR.MainLayout;

@Route(value = "ReviewFootage", layout = MainLayout.class)
@PageTitle("Review Footage")
public class ReviewFootageView extends HorizontalLayout {

    public static final String VIEW_NAME = "Review Footage";
    private TextField filter;
    private Button lastImage;
    private Button nextImage;
    private Button flagImage;
    private String campaignName;
    private ProductGrid grid;
    private Image img;

    public ReviewFootageView() {
        campaignName = "Placeholder Campaign Name";
        setSizeFull();
        HorizontalLayout topLayout = createTopBar();
        LoadStaticImage();
        VerticalLayout barAndImages = new VerticalLayout();
        barAndImages.add(topLayout);
        barAndImages.add(img);
        barAndImages.setFlexGrow(1, img);
        barAndImages.setFlexGrow(0, topLayout);
        barAndImages.setSizeFull();
        add(barAndImages);
    }

    public void LoadStaticImage(){
        String resolvedImage = VaadinServletService.getCurrent()
                .resolveResource("frontend://img/Placeholder.png",
                        VaadinSession.getCurrent().getBrowser());

        img = new Image(resolvedImage, "");
        img.setWidth("100%");
    }

    public HorizontalLayout createTopBar(){

        // Apply the filter to grid's data provider. TextField value is never null
        //filter.addValueChangeListener(event -> dataProvider.setFilter(event.getValue()));

        lastImage = new Button("Last Image");
        lastImage.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        lastImage.setIcon(VaadinIcon.ANGLE_LEFT.create());
        //TODO: Link to logic to load last image
        //lastImage.addClickListener(click -> viewLogic.newProduct());

        nextImage = new Button("Next Image");
        nextImage.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        nextImage.setIcon(VaadinIcon.ANGLE_RIGHT.create());
        nextImage.setIconAfterText(true);
        //TODO: Link to logic to load next image
        //nextImage.addClickListener(click -> viewLogic.newProduct());

        flagImage = new Button("Flag");
        flagImage.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        flagImage.setIcon(VaadinIcon.FLAG.create());
        flagImage.setIconAfterText(true);
        flagImage.getStyle().set("margin-left", "auto");
        //TODO: Link to logic to flagged image logic
        //flagImage.addClickListener(click -> viewLogic.newProduct());

        Span campaign = new Span(campaignName);
        campaign.getStyle().set("margin-left", "auto");
        campaign.getStyle().set("margin-right", "auto");
        campaign.getStyle().set("font-size", "30px");

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth("100%");
        topLayout.add(lastImage);
        topLayout.add(nextImage);
        topLayout.add(campaign);
        topLayout.setAlignSelf(Alignment.CENTER, campaign);
        topLayout.add(flagImage);


        return topLayout;
    }

}
