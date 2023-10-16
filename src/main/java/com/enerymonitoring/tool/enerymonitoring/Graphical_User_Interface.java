
        package com.enerymonitoring.tool.enerymonitoring;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.lang.module.Configuration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

        public class Graphical_User_Interface extends Application {
    private EnerymonitoringApplication energyMonitoringApp;

    private HostEnergyConsumptionGauge energyGauge;

    private TextArea outputTextArea;// TextArea to display the output

    private Slider vmCoresSlider; // Slider for VM CPU Cores

    private Slider host_no_slider;
    private Slider host_cores;
    private Slider vm_no_slider;
    private Slider cloudlet_no_slider;
    private Slider cloudlet_core;
    private String darkModeStyles;
    private Slider vmRamSlider;
    private Slider vmBwSlider;
    private Slider vmStorageSlider;
    private Slider cloudletLengthSlider;
            private final int hostDefaultRAM = 2048;


                public static void main(String[] args) {
                    launch(args);
                }



    @Override
    public void start(Stage primaryStage) {
        HostEnergyConsumptionGauge energyGauge = new HostEnergyConsumptionGauge();

        VMEnergyConsumptionGauge vmenergyGauge = new VMEnergyConsumptionGauge();

        StackPane Host_root = new StackPane(energyGauge.getGauge());
        StackPane vm_root = new StackPane(vmenergyGauge.getGauge());

        // Create a configuration manager
        ConfigurationManager configManager = new ConfigurationManager();

        // Add multiple configurations to the manager
        configManager.addConfiguration("Default", 4, 2, 4, 8, 8, 2);
        configManager.addConfiguration("Custom 1", 8, 3, 5, 10, 12, 3);
        configManager.addConfiguration("Configuration 1", 5, 4, 10, 2, 20, 1);
        configManager.addConfiguration("Configuration 2", 3, 6, 9, 3, 15, 2);
        configManager.addConfiguration("Configuration 3", 2, 8, 8, 4, 12, 4);
        configManager.addConfiguration("Configuration 4", 4, 2, 6, 2, 10, 2);
        configManager.addConfiguration("Configuration 5", 3, 6, 12, 2, 15, 1);
        configManager.addConfiguration("Configuration 6", 6, 3, 9, 3, 18, 2);
        configManager.addConfiguration("Configuration 7", 4, 4, 8, 2, 12, 2);
        configManager.addConfiguration("Configuration 8", 5, 5, 10, 2, 15, 2);
        configManager.addConfiguration("Configuration 9", 3, 6, 6, 2, 12, 2);
        configManager.addConfiguration("Configuration 10", 5, 3, 10, 2, 15, 2);

        // Create a ComboBox to select configurations
        ObservableList<String> configNames = FXCollections.observableArrayList(configManager.getConfigurationNames());

        ComboBox<String> configComboBox = new ComboBox<>(configNames);
        configComboBox.setValue("Default");

        configComboBox.setOnAction(e -> {
            Configuration selectedConfig = configManager.getConfiguration(configComboBox.getValue());
            vmCoresSlider.setValue(selectedConfig.vmCores);
            host_no_slider.setValue(selectedConfig.noOfHosts);
            host_cores.setValue(selectedConfig.hostCores);
            vm_no_slider.setValue(selectedConfig.noOfVMs);
            cloudlet_no_slider.setValue(selectedConfig.noOfCloudlets);
            cloudlet_core.setValue(selectedConfig.cloudletCores);
        });
        VBox configComboBoxBox = new VBox(10);
        configComboBoxBox.getChildren().addAll(new Label("Select Configuration"), configComboBox);

        HBox hbox_gauge = new HBox(10); // 10 pixels spacing
        Label aboutUsLabel = new Label("Welcome to the VMWattEco!\n\n" +
                "This project is a part of the Capstone Project of  CSD5002 - Virtualization Essentials (Fall Semester 2023-24) by Dr. Hemraj S.L.\n" +
                "Group Number: 12\n\n" +
                "Team Members:\n" +
                "1. Prakadesh R (Reg No: 20MEI10076)\n" +
                "2. Aakaash K S (Reg No: 20MEI10055)\n" +
                "3. M.Pon Dinesh Kumar (Reg No: 20MEI10010)\n" +
                "4. KOTHA SRIRAM (Reg No: 20MEI10062)\n" +
                "5. CHINTHA REDDY SAIKIRAN (Reg No: 20MEI10064)\n\n");

        Button moreButton = new Button("More");

        VBox aboutUsAndMoreLayout = new VBox(10); // 10 pixels spacing
        aboutUsAndMoreLayout.getChildren().addAll(aboutUsLabel, moreButton);

        hbox_gauge.getChildren().addAll(Host_root, vm_root, aboutUsAndMoreLayout);

        moreButton.setStyle(darkModeStyles);


        // Create GUI components
        Label errorLabel = new Label();



        // Create a Slider for VM CPU Cores
        vmCoresSlider = new Slider(0, 10, 0); // Min: 1, Max: 10, Initial: 1
        Label vmCoresValueLabel = new Label("VM CPU Cores: 0");

        host_no_slider = new Slider(0, 10, 0);
        Label hostnoValueLabel = new Label("NO OF HOSTS: 0");

        host_cores = new Slider(0, 10, 0);
        Label hostcoreValueLabel = new Label("Host CPU Cores : 0");

        vm_no_slider = new Slider(0, 10, 0);
        Label vmnoValueLabel = new Label("NO OF VM: 0");

        cloudlet_no_slider = new Slider(0, 10, 0);
        Label cloudletnoValueLabel = new Label("NO OF Cloudlets: 0");

        cloudlet_core = new Slider(0, 10, 0);
        Label cloudletcoreValueLabel = new Label("Cloudlets CPU Cores: 0");

        // Create a Slider for VM RAM
         vmRamSlider = new Slider(0, 8192, 0); // Min: 0 MB, Max: 8192 MB, Initial: 512 MB
        Label vmRamValueLabel = new Label("VM RAM (MB): 0");

// Create a Slider for VM Bandwidth
         vmBwSlider = new Slider(0, 2000, 0); // Min: 0 Mbps, Max: 2000 Mbps, Initial: 1000 Mbps
        Label vmBwValueLabel = new Label("VM Bandwidth (Mbps): 0");

// Create a Slider for VM Storage
         vmStorageSlider = new Slider(0, 20000, 0); // Min: 0 MB, Max: 20000 MB, Initial: 10000 MB
        Label vmStorageValueLabel = new Label("VM Storage (MB): 0");

// Create a Slider for Cloudlet Length
         cloudletLengthSlider = new Slider(0, 100000, 0); // Min: 0, Max: 100000, Initial: 50000
        Label cloudletLengthValueLabel = new Label("Cloudlet Length: 0");



        // Add a listener to update the label when the Slider value changes
        vmCoresSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int selectedValue = newValue.intValue();
            vmCoresValueLabel.setText("VM CPU Cores: " + selectedValue);
        });

        host_no_slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int selectedValue = newValue.intValue();
            hostnoValueLabel.setText("NO OF HOSTS: " + selectedValue);
        });
        host_cores.valueProperty().addListener((observable, oldValue, newValue) -> {
            int selectedValue = newValue.intValue();
            hostcoreValueLabel.setText("Host CPU Cores: " + selectedValue);
        });
        vm_no_slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int selectedValue = newValue.intValue();
            vmnoValueLabel.setText("NO OF VM: " + selectedValue);
        });
        cloudlet_no_slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int selectedValue = newValue.intValue();
            cloudletnoValueLabel.setText("NO OF Cloudlets: " + selectedValue);
        });
        cloudlet_core.valueProperty().addListener((observable, oldValue, newValue) -> {
            int selectedValue = newValue.intValue();
            cloudletcoreValueLabel.setText("Host CPU Cores: " + selectedValue);
        });

        vmRamSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int selectedValue = newValue.intValue();
            vmRamValueLabel.setText("VM RAM (MB): " + selectedValue);
        });

        vmBwSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int selectedValue = newValue.intValue();
            vmBwValueLabel.setText("VM Bandwidth (Mbps): " + selectedValue);
        });

        vmStorageSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int selectedValue = newValue.intValue();
            vmStorageValueLabel.setText("VM Storage (MB): " + selectedValue);
        });

        cloudletLengthSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int selectedValue = newValue.intValue();
            cloudletLengthValueLabel.setText("Cloudlet Length: " + selectedValue);
        });


        // Initialize the energy gauge

        Button startButton = new Button("Start Energy Monitoring");


        // Create an HBox to hold the buttons
        HBox buttonBox = new HBox(10); // 10 pixels spacing
        buttonBox.getChildren().addAll(startButton);


        // Create a layout and add components
        VBox vbox = new VBox(10);// 10 pixels spacing

        // Create dark mode styles
        darkModeStyles =
                "-fx-background-color: #2C2B34;" +
                        "-fx-control-inner-background: #2C2B34;" +
                        "-fx-text-fill: white;";


        VBox.setMargin(errorLabel, new Insets(10, 10, 10, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(vmCoresSlider, new Insets(0, 170, 0, 10));
        VBox.setMargin(host_no_slider, new Insets(0, 170, 0, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(hostnoValueLabel, new Insets(10, 10, 0, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(host_cores, new Insets(0, 170, 0, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(hostcoreValueLabel, new Insets(0, 10, 0, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(vm_no_slider, new Insets(0, 170, 0, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(vmnoValueLabel, new Insets(0, 10, 0, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(vmCoresValueLabel, new Insets(0, 10, 0, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(cloudlet_no_slider, new Insets(0, 170, 0, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(cloudletnoValueLabel, new Insets(0, 10, 0, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(cloudlet_core, new Insets(0, 170, 0, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(cloudletcoreValueLabel, new Insets(0, 10, 0, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(vmRamSlider, new Insets(0, 170, 0, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(vmRamValueLabel, new Insets(0, 10, 0, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(vmBwSlider, new Insets(0, 170, 0, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(vmBwValueLabel, new Insets(0, 10, 0, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(vmStorageSlider, new Insets(0, 170, 0, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(vmStorageValueLabel, new Insets(0, 10, 0, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(cloudletLengthSlider, new Insets(0, 170, 0, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(cloudletLengthValueLabel, new Insets(0, 10, 0, 10));// Set top and bottom margins to 10 pixels
        VBox.setMargin(startButton, new Insets(10));
        VBox.setMargin(buttonBox, new Insets(10, 10, 0, 10));
        vbox.setStyle(darkModeStyles); // Set the background color to black
        errorLabel.setStyle(darkModeStyles);
        hostnoValueLabel.setStyle(darkModeStyles);
        hostcoreValueLabel.setStyle(darkModeStyles);
        vmnoValueLabel.setStyle(darkModeStyles);
        vmCoresValueLabel.setStyle(darkModeStyles);
        cloudletnoValueLabel.setStyle(darkModeStyles);
        cloudletcoreValueLabel.setStyle(darkModeStyles);
        aboutUsLabel.setStyle(darkModeStyles);
        vmRamValueLabel.setStyle(darkModeStyles);
        vmBwValueLabel.setStyle(darkModeStyles);
        vmStorageValueLabel.setStyle(darkModeStyles);
        cloudletLengthValueLabel.setStyle(darkModeStyles);

        errorLabel.setStyle("-fx-text-fill: red;"); // Set the error text color to red

        vbox.getChildren().addAll(
                configComboBoxBox,
                hostnoValueLabel, host_no_slider, hostcoreValueLabel, host_cores,
                vmnoValueLabel, vm_no_slider, vmCoresValueLabel, vmCoresSlider,
                cloudletnoValueLabel, cloudlet_no_slider, cloudletcoreValueLabel, cloudlet_core, vmRamValueLabel,
                vmRamSlider,
                vmBwValueLabel,
                vmBwSlider,
                vmStorageValueLabel,
                vmStorageSlider,
                cloudletLengthValueLabel,
                cloudletLengthSlider,
                buttonBox, errorLabel, createOutputTextArea(), hbox_gauge
        );
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vbox);
        scrollPane.pannableProperty().set(true);
        scrollPane.setFitToWidth(true);  // This line will make the scroll pane take the full width
        // Get the vertical scrollbar of the scroll pane
        ScrollBar verticalScrollBar = (ScrollBar) scrollPane.lookup(".vertical .scroll-bar");





        //stackpane to expand

        //StackPane stackPane = new StackPane(scrollPane);
        //bind
        //scrollPane.prefWidthProperty().bind(stackPane.widthProperty());
        //scrollPane.prefHeightProperty().bind(stackPane.heightProperty());
        // Create a scene
        Scene scene = new Scene(scrollPane);

        // Set the stage title
        primaryStage.setTitle("VMWattEco");
        primaryStage.setMaximized(true);
        primaryStage.setFullScreen(false);
        // to handle maximize screen
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            // Adjust the ScrollPane's width when the window is resized
            double newWidth = (double)newVal;
            scrollPane.setPrefWidth(newWidth);
        });

        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            // Adjust the ScrollPane's height when the window is resized
            double newHeight = (double)newVal;
            scrollPane.setPrefHeight(newHeight);
        });

        // Set the action for the "Start" button

        startButton.setOnAction(e -> {
            int vmcpuCoresValue = (int) vmCoresSlider.getValue();
            int ionohosts = (int) host_no_slider.getValue();
            int vmNoIO = (int) vm_no_slider.getValue();
            int cloudlets = (int) cloudlet_no_slider.getValue();
            int host_cpu = (int) host_cores.getValue();
            int cloudlets_core = (int) cloudlet_core.getValue();
            int vmram = (int) vmRamSlider.getValue();
            int vmbw = (int) vmBwSlider.getValue();
            int vmstor = (int) vmStorageSlider.getValue();
            int cloudletlen = (int) cloudletLengthSlider.getValue();

            // Calculate total available host resources (you need to define these)
            int totalHostCores = ionohosts * host_cpu;
            int totalRAM = hostDefaultRAM;

            int totalHostRAM = ionohosts * totalRAM; // Total available RAM in all hosts'

            if (vmcpuCoresValue > host_cpu || vmram > (totalHostRAM / vmNoIO)) {
                errorLabel.setText("Host resources cannot handle the VM requirements.");
            } else if (cloudlets_core > host_cpu) {
                errorLabel.setText("Host resources cannot handle the cloudlet requirements.");
            } else if (totalHostCores < vmcpuCoresValue * vmNoIO) {
                errorLabel.setText("Not enough host resources for VMs.");
            } else if (totalHostCores < cloudlets_core * cloudlets) {
                errorLabel.setText("Not enough host resources for cloudlets.");
            }else {
                try {
                    scrollPane.setVvalue(1.0);

                    // Initialize variables with default values
                    int vmPesValue = 4;
                    int noofhost = 2;
                    int vmno = 4;
                    int cloudletno = 8;
                    int hostcores = 8;
                    int cloudletCore = 2;
                    int ramDefault = 512;
                    int bwDefault =  1000;
                    int storageDefault = 10000;
                    int cloudletLength = 50000;
                    // Parse the user input as an integer if provided
                    if (vmcpuCoresValue != 0) {
                        vmPesValue = vmcpuCoresValue;
                    }
                    if (ionohosts != 0) {
                        noofhost = ionohosts;
                    }
                    if (vmNoIO != 0) {
                        vmno = vmNoIO;
                    }
                    if (cloudlets != 0) {
                        cloudletno = cloudlets;
                    }
                    if (host_cpu != 0) {
                        hostcores = host_cpu;
                    }
                    if (cloudlets_core != 0) {
                        cloudletCore = cloudlets_core;
                    }
                    if (vmram != 0) {
                        ramDefault = vmram;
                    }
                    if (vmbw != 0){
                        bwDefault = vmbw;
                    }
                    if (vmstor != 0){
                        storageDefault=vmstor;
                    }
                    if (cloudletlen != 0){
                        cloudletLength=cloudletlen;
                    }
                    // Parse the user input as an integer





                    // Call the setVmPes method in the EnerymonitoringApplication class
                    errorLabel.setText(""); // Clear any previous error message
                    EnerymonitoringApplication.setVmPes(vmPesValue);
                    EnerymonitoringApplication.setHosts(noofhost);
                    EnerymonitoringApplication.setVms(vmno);
                    EnerymonitoringApplication.setCloudletno(cloudletno);
                    EnerymonitoringApplication.setHostcores(hostcores);
                    EnerymonitoringApplication.setCLOUDLETS_core(cloudletCore);
                    EnerymonitoringApplication.setVM_RAM_DEFAULT(ramDefault);
                    EnerymonitoringApplication.setVM_BW_DEFAULT(bwDefault);
                    EnerymonitoringApplication.setVM_STORAGE_DEFAULT(storageDefault);
                    EnerymonitoringApplication.setCLOUDLET_LENGTH(cloudletLength);


                    // Instantiate and start the EnerymonitoringApplication
                    energyMonitoringApp = new EnerymonitoringApplication();
                    // Assuming you have a method to start the energy monitoring

                    double averageHostConsumption = energyMonitoringApp.getAverageHostEnergyConsumption();
                    energyGauge.updateValue(averageHostConsumption);

                    double averageVMConsumption = energyMonitoringApp.getAverageVMEnergyConsumption();
                    vmenergyGauge.updateValue(averageVMConsumption);



                /*/ Update the gauge's value with new energy consumption data
                double averageHostConsumption = energyMonitoringApp.getAverageHostEnergyConsumption();
                energyGauge.updateValueHost(averageHostConsumption);
                System.out.println("Updated HOST Gauge Value: " + energyGauge   .getHostGauge().getValue());


                double averageVMConsumption = energyMonitoringApp.getAverageVMEnergyConsumption();
                energyGauge.updateValueVM(averageVMConsumption);
                System.out.println("Updated VM Gauge Value: " + energyGauge.getVMGauge().getValue());*/


                    // Update the circular meter with the average energy consumption values

                    // Display the output in a new window or dialog
                    displayOutputInTextArea(energyMonitoringApp.getOutput());
                } catch (NumberFormatException ex) {
                    // Handle the case where the input is not a valid integer
                    System.err.println("Invalid input. Please enter a valid integer.");
                    // You can display an error message to the user as needed
                }
            }




        });
        moreButton.setOnAction(e -> {
            showAboutUsPage();
        });

        // Set the scene and show the GUI
        primaryStage.setScene(scene);
        primaryStage.show();
    }




    // Add a method to display the output in a new window or dialog
    private TextArea createOutputTextArea() {
        outputTextArea = new TextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setWrapText(true);
        outputTextArea.setPrefRowCount(10); // Set the number of visible rows
        return outputTextArea;
    }

    // Method to display the output in the TextArea
    private void displayOutputInTextArea(String output) {
        outputTextArea.appendText("\nEnergy Monitoring Output:\n" + output);
        outputTextArea.positionCaret(outputTextArea.getText().length());
    }
    private void showAboutUsPage() {
        Stage aboutUsStage = new Stage();

        // Create the content for the "About Us" page
        Label aboutUsLabel = new Label("Welcome to the VMWattEco!\n\n" +
                "This project is a part of the Capstone Project of  CSD5002 - Virtualization Essentials (Fall Semester 2023-24) by Dr. Hemraj S.L.\n" +
                "Group Number: 12\n\n" +
                "Team Members:\n" +
                "1. Prakadesh R (Reg No: 20MEI10076)\n" +
                "2. Aakaash K S (Reg No: 20MEI10055)\n" +
                "3. M.Pon Dinesh Kumar (Reg No: 20MEI10010)\n" +
                "4. KOTHA SRIRAM (Reg No: 20MEI10062)\n" +
                "5. CHINTHA REDDY SAIKIRAN (Reg No: 20MEI10064)\n\n"+
                "This application uses Medusa for gauges, OpenJFX (JavaFX) for the graphical user interface, and Maven for project management. Here's a brief overview of each:\n" +
                "- CloudSim Plus: A Java 17 simulation framework that simplifies Cloud computing modeling and experimentation. It empowers developers to focus on design without worrying about low-level infrastructure details, based on CloudSim 3.\n" +
                "- Medusa: A gauge library for JavaFX that enables the creation of visually appealing and interactive gauges and displays.\n" +
                "- OpenJFX (JavaFX): A framework for building rich client applications using Java. It's commonly used for creating graphical user interfaces (GUIs) in Java applications.\n" +
                "- Maven: A project management and build automation tool. It simplifies the build process and handles project dependencies.");

        // Create a layout for the "About Us" page
        VBox aboutUsLayout = new VBox(10);
        aboutUsLayout.getChildren().addAll(aboutUsLabel);
        aboutUsLayout.setStyle(darkModeStyles);
        aboutUsLabel.setStyle(darkModeStyles);
        aboutUsLayout.setMargin(aboutUsLabel, new Insets(20));// Set top and bottom margins to 10 pixels

        // Create the "Back" button to return to the main GUI
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            aboutUsStage.close();
        });
        aboutUsLayout.getChildren().add(backButton);

        // Create a scene for the "About Us" page
        Scene aboutUsScene = new Scene(aboutUsLayout, 1300, 500);

        // Set the stage title and show the "About Us" page
        aboutUsStage.setTitle("About Us");
        aboutUsStage.setScene(aboutUsScene);
        aboutUsStage.show();
    }
    class ConfigurationManager {
        private final Map<String, Configuration> configurations = new HashMap<>();

        public void addConfiguration(String name, int vmCores, int hostNo, int vmNo, int cloudletNo, int hostCores, int cloudletCores) {
            configurations.put(name, new Configuration(vmCores, hostNo, vmNo, cloudletNo, hostCores, cloudletCores));
        }

        public Set<String> getConfigurationNames() {
            return configurations.keySet();
        }

        public Configuration getConfiguration(String name) {
            return configurations.get(name);
        }
    }
            class Configuration {
                int vmCores;
                int noOfHosts;
                int noOfVMs;
                int noOfCloudlets;
                int hostCores;
                int cloudletCores;

                public Configuration(int vmCores, int noOfHosts, int noOfVMs, int noOfCloudlets, int hostCores, int cloudletCores) {
                    this.vmCores = vmCores;
                    this.noOfHosts = noOfHosts;
                    this.noOfVMs = noOfVMs;
                    this.noOfCloudlets = noOfCloudlets;
                    this.hostCores = hostCores;
                    this.cloudletCores = cloudletCores;
                }
            }
        }


