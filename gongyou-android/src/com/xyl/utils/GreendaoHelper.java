package com.xyl.utils;


import android.content.Context;


import com.xyl.base.BaseApplication;
import com.xyl.greendao.Cart;
import com.xyl.greendao.CartDao;
import com.xyl.greendao.DaoSession;
import com.xyl.greendao.LibraryCartBean;
import com.xyl.greendao.LibraryCartBeanDao;
import com.xyl.greendao.TreasuryCartBean;
import com.xyl.greendao.TreasuryCartBeanDao;

import java.util.List;

public class GreendaoHelper {

    public static void getQueryIdCart(Cart cart, long productId){
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        CartDao cartDao = daoSession.getCartDao();
        List<Cart> carts = cartDao.queryBuilder().where(CartDao.Properties.ProductId.eq(productId)).build().list();
        if (carts!=null&&carts.size()>0) {
            updateCart(cart, productId);
        } else {
            saveCart(cart);
        }

    }

    public static void updateCart(Cart cart, long productId) {
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        CartDao cartDao = daoSession.getCartDao();
        Cart unique = cartDao.queryBuilder().where(CartDao.Properties.ProductId.eq(productId)).build().unique();
        if (unique!=null){
            unique.setSaleCount(cart.getSaleCount());
            cartDao.update(unique);
        }
    }
    public static void saveCart(Cart cart) {
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        CartDao cartDao = daoSession.getCartDao();
        cartDao.save(cart);
    }

    public static double saleCount(Context cxt, int productId, int businessId) {
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        CartDao cartDao = daoSession.getCartDao();
        List<Cart> carts = cartDao.queryBuilder().where(CartDao.Properties.ProductId.eq(productId)).build().list();
        if (carts!=null&&carts.size()>0) {
            return carts.get(0).getSaleCount();
        } else {
            return 0;
        }
    }
    public static void deleteCart(int productId) {
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        CartDao cartDao = daoSession.getCartDao();
        Cart unique = cartDao.queryBuilder().where(CartDao.Properties.ProductId.eq(productId)).build().unique();
        if (unique !=null){
            cartDao.deleteByKey(unique.getId());
        }
    }

    public static void clearCart(boolean isSelected) {
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        CartDao cartDao = daoSession.getCartDao();
        List<Cart> carts = cartDao.queryBuilder().where(CartDao.Properties.IsSelected.eq(isSelected)).build().list();
        if (carts!=null&&carts.size()>0) {
            cartDao.deleteAll();
        }
    }
    public static List<Cart> getCartList(int businessId) {
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        CartDao cartDao = daoSession.getCartDao();

        List<Cart> carts = cartDao.queryBuilder().where(CartDao.Properties.BusinessId.eq(businessId)).build().list();
       if (carts !=null){
           return carts;
       }else{
           return null;
       }

    }


    /**
     * 出库模块数据库操作
     * @param
     * @param productId
     */

    public static void getQueryIdTreasury(TreasuryCartBean treasuryCartBean, long productId){
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        TreasuryCartBeanDao treasuryCartBeanDao = daoSession.getTreasuryCartBeanDao();
        List<TreasuryCartBean> treasuryCartBeen = treasuryCartBeanDao.queryBuilder().where(TreasuryCartBeanDao.Properties.ProductId.eq(productId)).build().list();
        if (treasuryCartBeen!=null&&treasuryCartBeen.size()>0) {
            updateTreasury(treasuryCartBean, productId);
        } else {
            saveTreasury(treasuryCartBean);
        }

    }

    public static void updateTreasury(TreasuryCartBean treasuryCartBean, long productId) {
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        TreasuryCartBeanDao treasuryCartBeanDao = daoSession.getTreasuryCartBeanDao();
        TreasuryCartBean unique = treasuryCartBeanDao.queryBuilder().where(TreasuryCartBeanDao.Properties.ProductId.eq(productId)).build().unique();
        if (unique!=null){
            unique.setSaleCount(treasuryCartBean.getSaleCount());
            treasuryCartBeanDao.update(unique);
        }
    }
    public static void updateTreasuryCart(Cart cart, long productId) {
        TreasuryCartBean treasuryCartBean = new TreasuryCartBean();
        treasuryCartBean.setName(cart.getName());
        treasuryCartBean.setProductId(cart.getProductId());
        treasuryCartBean.setSaleCount(cart.getSaleCount());
        treasuryCartBean.setPrice(cart.getPrice());
        treasuryCartBean.setProductType(cart.getProductType());
        treasuryCartBean.setBusinessId(cart.getBusinessId());
        treasuryCartBean.setIsSelected(cart.getIsSelected());
        cart.setImageUrl(treasuryCartBean.getImageUrl());
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        TreasuryCartBeanDao treasuryCartBeanDao = daoSession.getTreasuryCartBeanDao();
        TreasuryCartBean unique = treasuryCartBeanDao.queryBuilder().where(TreasuryCartBeanDao.Properties.ProductId.eq(productId)).build().unique();
        if (unique!=null){
            unique.setSaleCount(treasuryCartBean.getSaleCount());
            treasuryCartBeanDao.update(unique);
        }
    }
    public static void saveTreasury(TreasuryCartBean treasuryCartBean) {
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        TreasuryCartBeanDao treasuryCartBeanDao = daoSession.getTreasuryCartBeanDao();
        treasuryCartBeanDao.save(treasuryCartBean);
    }

    public static double saleCountTreasury(Context cxt, int productId, int businessId) {
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        TreasuryCartBeanDao treasuryCartBeanDao = daoSession.getTreasuryCartBeanDao();
        List<TreasuryCartBean> treasuryCartBeans = treasuryCartBeanDao.queryBuilder().where(TreasuryCartBeanDao.Properties.ProductId.eq(productId)).build().list();
        if (treasuryCartBeans!=null&&treasuryCartBeans.size()>0) {
            return (double) treasuryCartBeans.get(0).getSaleCount();
        } else {
            return 0;
        }
    }
    public static void deleteTreasury(int productId) {
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        TreasuryCartBeanDao treasuryCartBeanDao = daoSession.getTreasuryCartBeanDao();
        TreasuryCartBean unique = treasuryCartBeanDao.queryBuilder().where(TreasuryCartBeanDao.Properties.ProductId.eq(productId)).build().unique();
        if (unique !=null){
            treasuryCartBeanDao.deleteByKey(unique.getId());
        }
    }

    public static void clearTreasury(boolean isSelected) {
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        TreasuryCartBeanDao treasuryCartBeanDao = daoSession.getTreasuryCartBeanDao();
        List<TreasuryCartBean> treasuryCartBeans = treasuryCartBeanDao.queryBuilder().where(TreasuryCartBeanDao.Properties.IsSelected.eq(isSelected)).build().list();
        if (treasuryCartBeans!=null&&treasuryCartBeans.size()>0) {
            treasuryCartBeanDao.deleteAll();
        }
    }
    public static List<TreasuryCartBean> getTreasuryList(int businessId) {
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        TreasuryCartBeanDao treasuryCartBeanDao = daoSession.getTreasuryCartBeanDao();
        List<TreasuryCartBean> treasuryCartBeans = treasuryCartBeanDao.queryBuilder().where(TreasuryCartBeanDao.Properties.BusinessId.eq(businessId)).build().list();
        if (treasuryCartBeans !=null){
            return treasuryCartBeans;
        }else{
            return null;
        }

    }

    /**
     * 盘库模块数据库操作
     * @param
     * @param productId
     */
    public static void getQueryIdLibrary(LibraryCartBean libraryCartBean, long productId){
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        LibraryCartBeanDao libraryCartBeanDao = daoSession.getLibraryCartBeanDao();
        List<LibraryCartBean> carts = libraryCartBeanDao.queryBuilder().where(LibraryCartBeanDao.Properties.ProductId.eq(productId)).build().list();
        if (carts!=null&&carts.size()>0) {
            updateLibrary(libraryCartBean, productId);
        } else {
            saveLibrary(libraryCartBean);
        }

    }

    public static void updateLibrary(LibraryCartBean cart, long productId) {
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        LibraryCartBeanDao libraryCartBeanDao = daoSession.getLibraryCartBeanDao();
        LibraryCartBean unique = libraryCartBeanDao.queryBuilder().where(LibraryCartBeanDao.Properties.ProductId.eq(productId)).build().unique();
        if (unique!=null){
            unique.setSaleCount(cart.getSaleCount());
            libraryCartBeanDao.update(unique);
        }
    }
    public static void saveLibrary(LibraryCartBean cart) {
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        LibraryCartBeanDao libraryCartBeanDao = daoSession.getLibraryCartBeanDao();
        libraryCartBeanDao.save(cart);
    }

    public static double saleLibraryCount(Context cxt, int productId, int businessId) {
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        LibraryCartBeanDao libraryCartBeanDao = daoSession.getLibraryCartBeanDao();
        List<LibraryCartBean> carts = libraryCartBeanDao.queryBuilder().where(LibraryCartBeanDao.Properties.ProductId.eq(productId)).build().list();
        if (carts!=null&&carts.size()>0) {
            return (double) carts.get(0).getSaleCount();
        } else {
            return 0;
        }
    }
    public static void deleteLibraryCart(int productId) {
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        LibraryCartBeanDao libraryCartBeanDao = daoSession.getLibraryCartBeanDao();
        LibraryCartBean unique = libraryCartBeanDao.queryBuilder().where(LibraryCartBeanDao.Properties.ProductId.eq(productId)).build().unique();
        if (unique !=null){
            libraryCartBeanDao.deleteByKey(unique.getId());
        }
    }

    public static void clearLibraryCart(boolean isSelected) {
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        LibraryCartBeanDao libraryCartBeanDao = daoSession.getLibraryCartBeanDao();
        List<LibraryCartBean> carts = libraryCartBeanDao.queryBuilder().where(LibraryCartBeanDao.Properties.IsSelected.eq(isSelected)).build().list();
        if (carts!=null&&carts.size()>0) {
            libraryCartBeanDao.deleteAll();
        }
    }
    public static List<LibraryCartBean> getLibraryCartList(int businessId) {
        DaoSession daoSession = BaseApplication.getBaseApplication().getDaoSession();
        LibraryCartBeanDao libraryCartBeanDao = daoSession.getLibraryCartBeanDao();

        List<LibraryCartBean> carts = libraryCartBeanDao.queryBuilder().where(LibraryCartBeanDao.Properties.BusinessId.eq(businessId)).build().list();
        if (carts !=null){
            return carts;
        }else{
            return null;
        }

    }





}
