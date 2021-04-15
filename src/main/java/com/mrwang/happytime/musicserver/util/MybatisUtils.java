package com.mrwang.happytime.musicserver.util;

import com.mrwang.happytime.musicserver.annotation.MybatisMap;
import com.mrwang.happytime.musicserver.po.Rank;
import com.mrwang.happytime.musicserver.po.Singer;
import com.mrwang.happytime.musicserver.po.Song;
import com.mrwang.happytime.musicserver.po.SongList;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MybatisUtils {
    private static String ifStatementPrefix = "<if test=\"";
    private static String ifStatementAfterPrefix = " != null\">";
    private static String ifStatementAfterAfterPrefix = "= #{";
    private static String ifStatementSecondSuffix = "}";
    private static String ifStatementSuffix = "\n</if>\n";
    public static String generateIfCode(Map<String,String> map,String prefix,String suffix){
        StringBuffer stringBuffer = new StringBuffer();
        Set<String> set = map.keySet();
        for(String s:set){
            stringBuffer.append(ifStatementPrefix);
            stringBuffer.append(s);
            stringBuffer.append(ifStatementAfterPrefix);
            stringBuffer.append("\n" + "    "  +  prefix );
            stringBuffer.append(map.get(s));
            stringBuffer.append(ifStatementAfterAfterPrefix);
            stringBuffer.append(s);
            stringBuffer.append(ifStatementSecondSuffix);
            stringBuffer.append(" " + suffix);
            stringBuffer.append(ifStatementSuffix);
        }
        return stringBuffer.toString();
    }
    public static String generateSelectCode(Map<String,String> map){
        StringBuffer sb = new StringBuffer();
        Set<String> set = map.keySet();
        sb.append("select ");
        for(String s:set) {
            sb.append(s + " as " + map.get(s) + ",");
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }
    public static String generateInsertStatement(Map<String,String> map,String tableName){
        StringBuffer sb = new StringBuffer();
        Set<String> set = map.keySet();
        sb.append("insert into " + tableName+"(");
        for(String s:set){
            sb.append(map.get(s) + ",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(")");
        sb.append(" values(");
        for(String s:set){
            sb.append("#{");
            sb.append(s);
            sb.append("},");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(")");
        return sb.toString();
    }
    public static Map<String,String>  generateMapByClass(Class clazz){
        Map<String,String> map = new HashMap();
        if(clazz == null){
            return map;
        }
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            if(field.isAnnotationPresent(MybatisMap.class)){
                MybatisMap annotation = field.getAnnotation(MybatisMap.class);
                map.put(field.getName(),annotation.value());
            }
        }
        return map;
    }
    public static void main(String[] args) {
        Map map = generateMapByClass(SongList.class);
        System.out.println(generateIfCode(map,"",","));
        System.out.println(generateSelectCode(map) + "\n");
        System.out.println(generateInsertStatement(map,"song_list"));
    }
}
