package com.example.malumukendi.assignment6activities.factoriesTest;

import com.example.malumukendi.assignment6activities.domain.Laptop;
import com.example.malumukendi.assignment6activities.domain.Shop;
import com.example.malumukendi.assignment6activities.domain.VideoCamera;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * Created by louisane Malu on images4/images2/2016.
 */
public class ShopFactoryTest {
    Shop shop;
    Shop newShop;
    Laptop laptop;
    List<VideoCamera> camera;

    public ShopFactoryTest() {
    }
    @Test
    public void createTest(){
        shop = new Shop.Builder().identification((long) 55).size(500.22).address("406, Protea Place, Plumstead").build();
        Assert.assertEquals(shop.getSize() , 500.22);
    }
    @Test
    public void updateTest() {
        newShop = new Shop.Builder().size(800.20).build();
        Assert.assertEquals(newShop.getSize(), 800.20);
    }
    @BeforeClass
    public static void setUpClass() throws Exception {
    }
    @AfterClass
    public static void tearDownClass() throws Exception {
    }
}
