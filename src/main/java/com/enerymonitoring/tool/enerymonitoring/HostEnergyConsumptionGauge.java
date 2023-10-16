package com.enerymonitoring.tool.enerymonitoring;

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.skins.ModernSkin;
import javafx.scene.paint.Color;

public class HostEnergyConsumptionGauge {
    private Gauge gauge; // Declare a private Gauge instance


    // Constructor
    public HostEnergyConsumptionGauge() {
        // Initialize the Gauge instance and customize it
        gauge = new Gauge();
        gauge.setSkin(new ModernSkin(gauge));
        gauge.setMinValue(0);
        gauge.setMaxValue(1000);
        gauge.setMajorTickSpace(100); // Set the interval to 100

        gauge.setUnit("Joules");

        gauge.setTitle("Energy Consumption of Host");
        gauge.setForegroundBaseColor(Color.WHITE);
    }
    public Gauge getGauge() {
        return gauge;
    }

    // Create a method to update the gauge's value
    public void updateValue(double value) {
        gauge.setValue(value);
    }

    /** / Initialize the gauges
    private void initGraphics() {
        // Create and configure gaugeHost
        gaugeHost = GaugeBuilder.create()
                .skinType(Gauge.SkinType.MODERN)
                .minValue(100)
                .maxValue(10000)
                .foregroundBaseColor(Color.WHITE)
                .valueVisible(true)
                .title("Energy Consumption of HOST")
                .unit("Watts")
                .build();
        gaugeHost.setValue(50);

        // Create and configure gaugeVM
        gaugeVM = GaugeBuilder.create()
                .skinType(Gauge.SkinType.MODERN)
                .minValue(0)
                .maxValue(100)
                .foregroundBaseColor(Color.WHITE)
                .valueVisible(true)
                .title("Energy Consumption of VM")
                .unit("Watts")
                .build();
        gaugeVM.setValue(75);
    }

    // Create a method to update the gauge's value
    public Gauge getHostGauge() {
        return gaugeHost;
    }

    public Gauge getVMGauge() {
        return gaugeVM;
    }

    public void updateValueHost(double value) {
        gaugeHost.setValue(value);
        gaugeHost.requestLayout();

    }
    public void updateValueVM(double value) {
        gaugeVM.setValue(value);
        gaugeVM.requestLayout();// Request layout update


    }
     */
}
