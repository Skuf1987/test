package org.example;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TestNode {
    int id;
    String name;
    Integer value;
    List<TestNode> children;

    public TestNode(int id, String name, Integer value, List<TestNode> children) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.children = children;
    }
}

class ValueEntry {
    int id;
    int value;
}

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Использование: java <values.json> <tests.json> <report.json>");
            return;
        }

        String valuesPath = args[0];
        String testsPath = args[1];
        String reportPath = args[2];

        try {
            Type valuesListType = new TypeToken<List<ValueEntry>>() {}.getType();
            List<ValueEntry> values = new Gson().fromJson(new FileReader(valuesPath), valuesListType);

            Map<Integer, Integer> valueMap = new HashMap<>();
            for (ValueEntry valueEntry : values) {
                valueMap.put(valueEntry.id, valueEntry.value);
            }

            TestNode root = new Gson().fromJson(new FileReader(testsPath), TestNode.class);

            fillValues(root, valueMap);

            try (FileWriter writer = new FileWriter(reportPath)) {
                new GsonBuilder().setPrettyPrinting().create().toJson(root, writer);
            }

            System.out.println("Успешно.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillValues(TestNode node, Map<Integer, Integer> valueMap) {
        if (valueMap.containsKey(node.id)) {
            node.value = valueMap.get(node.id);
        }

        if (node.children != null) {
            for (TestNode child : node.children) {
                fillValues(child, valueMap);
            }
        }
    }
}