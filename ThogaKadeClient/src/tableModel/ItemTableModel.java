package tableModel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author chamodshehanka on 11/12/2017
 * @project ThogaKadeFX
 **/
public class ItemTableModel {
    private SimpleStringProperty code = new SimpleStringProperty("");
    private SimpleStringProperty description = new SimpleStringProperty("");
    private SimpleDoubleProperty unitPrice = new SimpleDoubleProperty(0.0);
    private SimpleIntegerProperty qtyOnHand = new SimpleIntegerProperty(0);

    public ItemTableModel() {
    }

    public ItemTableModel(SimpleStringProperty code, SimpleStringProperty description, SimpleDoubleProperty unitPrice, SimpleIntegerProperty qtyOnHand) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }

    public String getCode() {
        return code.get();
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public double getUnitPrice() {
        return unitPrice.get();
    }

    public SimpleDoubleProperty unitPriceProperty() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice.set(unitPrice);
    }

    public int getQtyOnHand() {
        return qtyOnHand.get();
    }

    public SimpleIntegerProperty qtyOnHandProperty() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand.set(qtyOnHand);
    }
}
