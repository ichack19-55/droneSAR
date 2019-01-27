package com.droneSAR.review;

import com.droneSAR.MainLayout;
import com.droneSAR.backend.DroneClip;
import com.droneSAR.backend.User;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServletService;
import com.vaadin.flow.server.VaadinSession;

@Route(value = "ReviewFootage", layout = MainLayout.class)
@PageTitle("Review Footage")
public class ReviewFootageView extends HorizontalLayout {

    private Button nextImage;
    private Button flagImage;
    private String campaignName;
    private Image img;

    private DroneClip clip;

    public ReviewFootageView() {
        buildUI();
    }

    // Call into this before loading the page
    public void setClipToReview(DroneClip clip) {
        if (clip == null) {
            return;
        }

        this.clip = clip;
        String filename = clip.getNextFrameToReviewFile();
        if (filename != null) {
            loadStaticImage(filename);
        }
    }

    private void buildUI() {
        campaignName = "Placeholder Campaign Name";
        setSizeFull();

        HorizontalLayout topBar = createTopBar();
        HorizontalLayout btmBar = createBtmBar();

        // TODO: should load clip clicked on
        loadStaticImage(null);

        VerticalLayout barAndImages = new VerticalLayout();
        barAndImages.add(topBar);
        barAndImages.add(img);
        barAndImages.setAlignSelf(Alignment.CENTER, img);
        barAndImages.add(btmBar);

        barAndImages.setFlexGrow(1, img);
        barAndImages.setFlexGrow(0, topBar);
        barAndImages.setSizeFull();

        add(barAndImages);
    }

    private void loadStaticImage(String filepath) {
        if (filepath == null) {
            filepath = VaadinServletService.getCurrent()
                .resolveResource("frontend://img/Placeholder.png",
                    VaadinSession.getCurrent().getBrowser());
        }

        img = new Image(filepath, "");
        img.setHeight("60%");
    }

    private HorizontalLayout createTopBar() {
        Span campaign = new Span(campaignName);
        campaign.getStyle().set("margin-left", "auto");
        campaign.getStyle().set("margin-right", "auto");
        campaign.getStyle().set("font-size", "30px");

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth("100%");

        topLayout.add(campaign);
        topLayout.setAlignSelf(Alignment.CENTER, campaign);

//        topLayout.getElement().addEventListener("keypress", event -> left()).setFilter("event.key == 'Left'");
//        topLayout.getElement().addEventListener("keypress", event -> right()).setFilter("event.key == 'Right'");
//        topLayout.getElement().addEventListener("keypress", event -> flag()).setFilter("event.key == 'Space'");

        return topLayout;
    }

    private HorizontalLayout createBtmBar() {
        nextImage = new Button("Next Image");
        nextImage.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        nextImage.setIcon(VaadinIcon.ANGLE_RIGHT.create());
        nextImage.setIconAfterText(true);
        nextImage.addClickListener(click -> right());

        flagImage = new Button("Flag");
        flagImage.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        flagImage.setIcon(VaadinIcon.FLAG.create());
        flagImage.setIconAfterText(true);
        flagImage.getStyle().set("margin-left", "auto");
        flagImage.addClickListener(click -> flag());

        HorizontalLayout btmLayout = new HorizontalLayout();
        btmLayout.setWidth("100%");

        btmLayout.add(nextImage);
        btmLayout.add(flagImage);

        return btmLayout;
    }

    private void right() {
        setClipToReview(clip);
    }

    private void flag() {
        clip.addFlag();
    }
}
