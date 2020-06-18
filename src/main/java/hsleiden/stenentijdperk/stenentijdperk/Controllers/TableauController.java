package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import hsleiden.stenentijdperk.stenentijdperk.Models.TableauModel;
import hsleiden.stenentijdperk.stenentijdperk.observers.TableauObserver;

public class TableauController {
    private TableauModel tableauModel;

    public TableauController() {
        this.tableauModel = new TableauModel();
    }

    public void verhoogShit() {
        System.out.println("test");
        this.tableauModel.verhoogGereedschap();
    }

    // Register the observer
    public void registerObserver(TableauObserver to) {
        tableauModel.register(to);
    }
}
