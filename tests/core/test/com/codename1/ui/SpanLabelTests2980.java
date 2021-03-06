/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.ui;

import com.codename1.components.SpanButton;
import com.codename1.components.SpanLabel;
import com.codename1.testing.AbstractTest;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.Layout;

/**
 *
 * @author shannah
 */
public class SpanLabelTests2980 extends AbstractTest {

    private Layout layout;

    @Override
    public boolean shouldExecuteOnEDT() {
        return true;
    }

    @Override
    public boolean runTest() throws Exception {
        if (layout == null) {
            Layout[] layouts = new Layout[]{
                new FlowLayout(),
                BoxLayout.x(),
                BoxLayout.y()
            };
            for (Layout l : layouts) {
                layout = l;
                runTest();
            }
            return true;
        }
        System.out.println("Laying out SpanLabel with layout " + layout);
        Label label = new Label("Tap the following");

        Container cnt = new Container(layout) {
            @Override
            protected Dimension calcPreferredSize() {
                return new Dimension(label.getPreferredW(), CN.convertToPixels(1000));
            }

            @Override
            public int getWidth() {
                return label.getPreferredW();
            }

            @Override
            public int getHeight() {
                return CN.convertToPixels(1000);
            }

        };
        cnt.setScrollableX(false);
        cnt.setScrollableY(false);
        cnt.setWidth(label.getPreferredW());
        cnt.setHeight(CN.convertToPixels(1000));
        SpanLabel sl = new SpanLabel("Tap the following button to open the gallery. You should be able to select multiple images and videos.");

        sl.setName("TheSpanLabel");
        cnt.add(sl);
        cnt.add(new Button("Click Me"));
        cnt.setShouldCalcPreferredSize(true);
        cnt.layoutContainer();
        assertTrue(sl.getHeight() > label.getPreferredH() * 2, "Span Label height is too low for layout " + layout + ": was " + sl.getHeight() + " but should be at least " + (label.getPreferredH() * 2));

        SpanButton sb = new SpanButton("Tap the following button to open the gallery. You should be able to select multiple images and videos.");

        sb.setName("TheSpanButton");
        cnt.removeAll();
        cnt.add(sb);
        cnt.add(new Button("Click Me"));
        cnt.setShouldCalcPreferredSize(true);
        cnt.layoutContainer();
        assertTrue(sb.getHeight() > label.getPreferredH() * 2, "Span button height is too low for layout " + layout + ": was " + sb.getHeight() + " but should be at least " + (label.getPreferredH() * 2));

        return true;
    }

}
