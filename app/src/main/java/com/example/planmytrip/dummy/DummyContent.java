package com.example.planmytrip.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();


    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(String.valueOf(item.hotel_id), item);
    }

    private static DummyItem createDummyItem(int position, String hotel_name, String hotel_address, float hotel_cost) {
        return new DummyItem(position, hotel_name, hotel_address, hotel_cost);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final int hotel_id;
        public final String hotel_address;
        public final float hotel_cost;
        public final String hotel_name;

        public DummyItem(int hotel_id, String hotel_name, String hotel_address, float hotel_cost) {
            this.hotel_id = hotel_id;
            this.hotel_name = hotel_name;
            this.hotel_address = hotel_address;
            this.hotel_cost = hotel_cost;
        }

        @Override
        public String toString() {
            return hotel_id + " " + hotel_name;
        }
    }
}