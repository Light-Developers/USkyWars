package xyz.directplan.directlib.shop;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
public abstract class AbstractProduct<U> implements Product<U> {

    private final ProductType type;

    private final String name;
    private final int itemSlot;
    private final boolean enabled;

    public AbstractProduct(String name, int itemSlot, boolean enabled) {
        this(ProductType.PRODUCT, name, itemSlot, enabled);
    }

    public AbstractProduct(String name, int itemSlot) {
        this(name, itemSlot, true);
    }

    @Override
    public boolean isCategory() {
        return type.isCategory();
    }

    @Override
    public boolean isDisplay() {
        return false;
    }
}