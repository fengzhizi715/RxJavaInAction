package com.safframework.study.operator.menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.safframework.study.operator.R;
import com.safframework.study.operator.fragment.CombiningFragment;
import com.safframework.study.operator.fragment.CreateFragment;
import com.safframework.study.operator.fragment.HomeFragment;

/**
 * Created by tony on 2016/11/20.
 */

public class MenuManager {

    private static MenuManager instance = null;
    private FragmentManager fragmentManager;
    private MenuType curType;

    public enum MenuType {

        HOME("RxJava介绍",false),
        CREATE("创建操作符",true),
        COMBINING("合并操作符",true);

        public final String title;
        public final boolean removed;

        MenuType(String title, boolean removed) {
            this.title = title;
            this.removed = removed;
        }

        public String getTitle() {
            return title;
        }

        public boolean isRemoved() {
            return removed;
        }
    }

    private MenuManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        curType = MenuType.HOME;
    }

    public static MenuManager getInstance(FragmentManager fragmentManager) {
        if (instance == null) {
            instance = new MenuManager(fragmentManager);
        }

        return instance;
    }

    public MenuType getCurType() {

        return curType;
    }


    public boolean show(MenuType type) {
        if (curType.equals(type)) {
            return true;
        } else {
            hide(curType);
        }

        Fragment fragment = (Fragment) fragmentManager.findFragmentByTag(type.getTitle());
        if (fragment == null) {
            fragment = create(type);
            if (fragment == null) {
                return false;
            }
        }

        fragmentManager.beginTransaction().show(fragment).commit();
        curType = type;
        return true;
    }

    private Fragment create(MenuType type) {
        Fragment fragment = null;
        switch (type) {
            case HOME:
                fragment = new HomeFragment();
                break;

            case CREATE:
                fragment = new CreateFragment();
                break;

            case COMBINING:
                fragment = new CombiningFragment();
                break;

            default:
                break;
        }
        fragmentManager.beginTransaction().add(R.id.content_frame, fragment, type.getTitle()).commitAllowingStateLoss();
        return fragment;
    }

    private void hide(MenuType type) {
        Fragment fragment = (Fragment) fragmentManager.findFragmentByTag(type.getTitle());
        if (fragment != null) {
            if (type.isRemoved()) {
                fragmentManager.beginTransaction().remove(fragment).commit();
            } else {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                // ft.addToBackStack(type.getTitle());
                ft.hide(fragment);
                ft.commit();
            }
        }
    }

    /**
     * 判断某个fragment是否存在
     *
     * @param type
     * @return
     */
    public boolean isFragmentExist(MenuType type) {
        Fragment fragment = (Fragment) fragmentManager.findFragmentByTag(type.getTitle());
        return fragment != null;
    }

    /**
     * 返回菜单的总数
     *
     * @return
     */
    public int getMenuCount() {

        if (MenuType.values() != null) {
            return MenuType.values().length;
        }

        return 0;
    }

}
