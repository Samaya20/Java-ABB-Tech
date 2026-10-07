package com.example.orderingtask.service;

import com.example.orderingtask.dto.*;
import com.example.orderingtask.enums.CustomerType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderCalculationService {
    private static final int QUANTITY_DISCOUNT_THRESHOLD = 10;
    private static final BigDecimal QUANTITY_DISCOUNT_RATE = new BigDecimal("0.05");
    private static final BigDecimal VIP_DISCOUNT_RATE = new BigDecimal("0.15");
    private static final BigDecimal YENI10_RATE = new BigDecimal("0.10");
    private static final BigDecimal ENDIRIM20_AMOUNT = new BigDecimal("20.00");
    private static final BigDecimal ENDIRIM20_MIN_SUBTOTAL = new BigDecimal("100.00");
    private static final BigDecimal MAX_DISCOUNT_RATE = new BigDecimal("0.30");
    private static final BigDecimal VAT_RATE = new BigDecimal("0.18");
    private static final BigDecimal FREE_DELIVERY_THRESHOLD = new BigDecimal("200.00");
    private static final BigDecimal BAKU_DELIVERY_FEE = new BigDecimal("5.00");
    private static final BigDecimal OTHER_CITY_DELIVERY_FEE = new BigDecimal("10.00");


    public OrderResponse calculate(OrderRequest request) {
        if (isValid(request) == false) {
            return null;
        }

        OrderResponse cavab = new OrderResponse();
        List<String> tetbiqOlunanQaydalar = new ArrayList<>();
        List<OrderItemResponse> hesablanmisMehsullar = new ArrayList<>();

        BigDecimal subtotal = new BigDecimal("0.00");

        for (OrderItemRequest mehsul : request.getItems()) {
            OrderItemResponse hesablanmisMehsul = calculateLine(mehsul, tetbiqOlunanQaydalar);
            hesablanmisMehsullar.add(hesablanmisMehsul);
            subtotal = subtotal.add(hesablanmisMehsul.getFinalLineTotal());
        }

        BigDecimal musteriEndirimi = new BigDecimal("0.00");
        if (request.getCustomerType() == CustomerType.VIP) {
            musteriEndirimi = subtotal.multiply(VIP_DISCOUNT_RATE);
            tetbiqOlunanQaydalar.add("VIP endirimi 15%");
        }

        BigDecimal promoEndirimi = calculatePromoDiscount(request.getPromoCode(), subtotal, tetbiqOlunanQaydalar);

        BigDecimal umumiEndirim = musteriEndirimi.add(promoEndirimi);
        BigDecimal maksimumIcaZeliEndirim = subtotal.multiply(MAX_DISCOUNT_RATE);

        if (umumiEndirim.compareTo(maksimumIcaZeliEndirim) > 0) {
            umumiEndirim = maksimumIcaZeliEndirim;
            tetbiqOlunanQaydalar.add("Endirim limiti tətbiq olundu: maksimum 30%");
        }

        BigDecimal endirimdenSonrakiMebleg = subtotal.subtract(umumiEndirim);
        BigDecimal edvMeblegi = endirimdenSonrakiMebleg.multiply(VAT_RATE);
        BigDecimal catdirilmaHaqqi = calculateDeliveryFee(endirimdenSonrakiMebleg, request.getDeliveryCity(), tetbiqOlunanQaydalar);

        BigDecimal yekunMebleg = endirimdenSonrakiMebleg.add(edvMeblegi);
        yekunMebleg = yekunMebleg.add(catdirilmaHaqqi);

        cavab.setItems(hesablanmisMehsullar);
        cavab.setSubtotal(yuvarlaqlasdir(subtotal));
        cavab.setCustomerDiscount(yuvarlaqlasdir(musteriEndirimi));
        cavab.setPromoDiscount(yuvarlaqlasdir(promoEndirimi));
        cavab.setTotalDiscount(yuvarlaqlasdir(umumiEndirim));
        cavab.setAmountAfterDiscount(yuvarlaqlasdir(endirimdenSonrakiMebleg));
        cavab.setVat(yuvarlaqlasdir(edvMeblegi));
        cavab.setDeliveryFee(yuvarlaqlasdir(catdirilmaHaqqi));
        cavab.setGrandTotal(yuvarlaqlasdir(yekunMebleg));
        cavab.setAppliedRules(tetbiqOlunanQaydalar);

        return cavab;
    }

    private OrderItemResponse calculateLine(OrderItemRequest mehsul, List<String> tetbiqOlunanQaydalar) {
        OrderItemResponse cavabMehsul = new OrderItemResponse();
        cavabMehsul.setProductName(mehsul.getProductName());
        cavabMehsul.setQuantity(mehsul.getQuantity());
        cavabMehsul.setUnitPrice(yuvarlaqlasdir(mehsul.getUnitPrice()));

        BigDecimal say = new BigDecimal(mehsul.getQuantity());
        BigDecimal setirMeblegi = mehsul.getUnitPrice().multiply(say);
        cavabMehsul.setLineTotal(yuvarlaqlasdir(setirMeblegi));

        BigDecimal sayEndirimi = new BigDecimal("0.00");
        if (mehsul.getQuantity() >= QUANTITY_DISCOUNT_THRESHOLD) {
            sayEndirimi = setirMeblegi.multiply(QUANTITY_DISCOUNT_RATE);
            tetbiqOlunanQaydalar.add("Say endirimi 5%: " + mehsul.getProductName());
        }

        cavabMehsul.setQuantityDiscount(yuvarlaqlasdir(sayEndirimi));

        BigDecimal yekunSetirMeblegi = setirMeblegi.subtract(sayEndirimi);
        cavabMehsul.setFinalLineTotal(yuvarlaqlasdir(yekunSetirMeblegi));

        return cavabMehsul;
    }

    private BigDecimal yuvarlaqlasdir(BigDecimal deyer) {
        return deyer.setScale(2, RoundingMode.HALF_UP);
    }

    private boolean isValid(OrderRequest request) {
        if (request.getItems() == null || request.getItems().isEmpty()) {
            System.out.println("Xeta: Sifarisde en azi bir mehsul olmalidir");
            return false;
        }
        if (request.getCustomerType() == null) {
            System.out.println("Xeta: Musteri tipi mütleqdir");
            return false;
        }
        if (request.getDeliveryCity() == null || request.getDeliveryCity().isEmpty()) {
            System.out.println("Xeta: Catdirilma seheri mütleqdir");
            return false;
        }

        for (OrderItemRequest mehsul : request.getItems()) {
            if (mehsul.getProductName() == null || mehsul.getProductName().isEmpty()) {
                System.out.println("Xeta: Mehsulun adi bos ola bilmez");
                return false;
            }
            if (mehsul.getQuantity() == null || mehsul.getQuantity() < 1) {
                System.out.println("Xeta: Say 1-den kicik ola bilmez");
                return false;
            }
            if (mehsul.getUnitPrice() == null || mehsul.getUnitPrice().compareTo(new BigDecimal("0.00")) <= 0) {
                System.out.println("Xeta: Qiymet 0-dan böyük olmalidir");
                return false;
            }
        }
        return true;
    }

    private BigDecimal calculateDeliveryFee(BigDecimal mebleg, String seher, List<String> tetbiqOlunanQaydalar) {
        if (mebleg.compareTo(FREE_DELIVERY_THRESHOLD) >= 0) {
            tetbiqOlunanQaydalar.add("Pulsuz catdirilma");
            return new BigDecimal("0.00");
        }

        if (seher.equals("Baki")) {
            return BAKU_DELIVERY_FEE;
        } else {
            return OTHER_CITY_DELIVERY_FEE;
        }
    }

    private BigDecimal calculatePromoDiscount(String promoKod, BigDecimal subtotal, List<String> tetbiqOlunanQaydalar) {
        if (promoKod == null || promoKod.isEmpty()) {
            return new BigDecimal("0.00");
        }

        if (promoKod.equals("YENI10")) {
            tetbiqOlunanQaydalar.add("YENI10 promo kodu 10%");
            return subtotal.multiply(YENI10_RATE);
        } else if (promoKod.equals("ENDIRIM20")) {
            if (subtotal.compareTo(ENDIRIM20_MIN_SUBTOTAL) >= 0) {
                tetbiqOlunanQaydalar.add("ENDIRIM20 promo kodu 20.00 AZN");
                return ENDIRIM20_AMOUNT;
            } else {
                tetbiqOlunanQaydalar.add("ENDIRIM20 tetbiq olunmadi: minimum mebleg 100.00 AZN");
                return new BigDecimal("0.00");
            }
        } else {
            System.out.println("Xeberdarliq: Promo kod tapilmadi: " + promoKod);
            return new BigDecimal("0.00");
        }
    }


}
