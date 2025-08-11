package cn.library.context;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户身份支持器
 *
 */
public class LocalThreadHolder {

    private static final ThreadLocal<Map<String, Integer>> USER_HOLDER = new ThreadLocal<>();


    public static void setUserId(Integer userId, Integer userRole) {
        Map<String, Integer> map = new HashMap<>();
        map.put("userId", userId);
        map.put("userRole", userRole);
        USER_HOLDER.set(map);
    }


    public static Integer getUserId() {
        return USER_HOLDER.get().get("userId");
    }


    public static Integer getRoleId() {
        return USER_HOLDER.get().get("userRole");
    }


    public static void clear() {
        USER_HOLDER.remove();
    }

}
