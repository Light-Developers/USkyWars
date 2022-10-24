package xyz.directplan.directlib.shop;


/**
 * @author DirectPlan
 */
public interface Product<U> {

    ProductType getType();

    String getName();

    int getItemSlot();

    boolean isDisplay();

    boolean isEnabled();

    ProductCategory<U> getProductCategory();

    boolean isCategory();

    ProductItemDesign designItem(U user);

    void executeAction(U user);
}
