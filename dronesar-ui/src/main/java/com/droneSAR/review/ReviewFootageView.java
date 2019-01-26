package com.droneSAR.review;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.droneSAR.MainLayout;

@Route(value = "ReviewFootage", layout = MainLayout.class)
@PageTitle("Review Footage")
public class ReviewFootageView extends HorizontalLayout {

    public static final String VIEW_NAME = "Review Footage";

    public ReviewFootageView() {
        add(new Span("Review footage here..."));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
    }
}
