package com.droneSAR.review;

import com.droneSAR.MainLayout;
import com.droneSAR.backend.ClipBeingReviewed;
import com.droneSAR.backend.DroneClip;
import com.droneSAR.backend.User;
import com.vaadin.flow.component.UI;
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

    private Image img;
    private DroneClip clip;
    private String name = "Drone Clip Reviewer";

    public ReviewFootageView() {
        // Select most recently set drone clip to review at load time
        clip = ClipBeingReviewed.getInstance().getDroneClip();
        name = ClipBeingReviewed.getInstance().getCampaignName();

        loadStaticImage(null);
        buildUI();
    }

    private void buildUI() {
        setSizeFull();

        HorizontalLayout topBar = createTopBar();
        HorizontalLayout btmBar = createBtmBar();

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

    // Call into this before loading the page
    private void setClipToReview(DroneClip clip) {
        if (clip == null) {
            System.out.println("No clip provided!");
            return;
        }

        this.clip = clip;
        loadStaticImage(clip.getNextFrameToReviewFile());
    }

    private HorizontalLayout createTopBar() {
        Span campaign = new Span(name);

        campaign.getStyle().set("margin-left", "auto");
        campaign.getStyle().set("margin-right", "auto");
        campaign.getStyle().set("font-size", "30px");

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth("100%");

        topLayout.add(campaign);
        topLayout.setAlignSelf(Alignment.CENTER, campaign);

        return topLayout;
    }

    private HorizontalLayout createBtmBar() {
        Button nextImage = new Button("Next Image");
        nextImage.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        nextImage.setIcon(VaadinIcon.ANGLE_RIGHT.create());
        nextImage.setIconAfterText(true);
        nextImage.addClickListener(click -> right());

        Button flagImage = new Button("Flag");
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
        setClipToReview(ClipBeingReviewed.getInstance().getDroneClip());
    }

    private void flag() {
        clip.addFlag();
    }
}
