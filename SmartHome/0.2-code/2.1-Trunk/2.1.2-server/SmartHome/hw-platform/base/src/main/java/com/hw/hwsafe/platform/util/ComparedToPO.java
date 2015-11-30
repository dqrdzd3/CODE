package com.hw.hwsafe.platform.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.pojo.SysRecordPO;
import com.hw.hwsafe.platform.service.ISysRecordService;
import com.hw.hwsafe.utils.UUIDGenerater;

public class ComparedToPO {
	
	@Autowired
	private ISysRecordService sysRecordService;
	
	/**
	 * 比对程序
	 * @param newObject——新PO
	 * @param oldObject——老PO
	 * @param tableName——表名
	 * @param Id——该表的主键
	 * @throws Exception          
	 * @author:王贺禧
	 * @create_time:2013-9-12下午3:13:22
	 */
	public UserMessageData Compared(Object newObject,Object oldObject,String tableName,String Id) throws Exception {
		UserMessageData userMessageData = new UserMessageData();
		SysRecordPO sysRecordPO = new SysRecordPO();
		sysRecordPO.setMa001(UUIDGenerater.getUUID());
		sysRecordPO.setMa002(tableName);
		sysRecordPO.setMa003(Id);
		Class<?> classType = newObject.getClass();
		Field[] fields = classType.getDeclaredFields();
        for (Field f : fields) {
        	String fieldType = f.getType().toString();
            PropertyDescriptor pd = new PropertyDescriptor(f.getName(),classType);
            Method getMethod = pd.getReadMethod();
            Object newValue = getMethod.invoke(newObject);
            Object oldValue = getMethod.invoke(oldObject);
            if(!isEquals(newValue,oldValue,fieldType)){
            	sysRecordPO.setMa004(f.getName());
            	sysRecordPO.setMa005((String)oldValue);
            	sysRecordPO.setMa006((String)newValue);
            	sysRecordService.insertInstance(sysRecordPO);
            	userMessageData.set(Constants.MSG_OK,"所有修改记录已经入库！");
            }else{
            	userMessageData.set(Constants.MSG_ERROR,"没有进行任何选项的修改！");
            }
         }
		return userMessageData;
	}
	
	
    private boolean isEquals(Object obj1, Object obj2, String fieldType) {
        boolean flag = true;
        if ((obj1 == null && obj2 != null) || (obj1 != null && obj2 == null)) {
            System.out.println("类型为【" + fieldType + "】的值不相同！");
            return false;
        } else if (obj1 == null && obj2 == null) {
            System.out.println("类型为" + fieldType + "的值相同！");
            return true;
        } else {
            if ("class java.lang.Integer".equals(fieldType) || "int".equals(fieldType)) {
                Integer aa = (Integer) obj1;
                Integer bb = (Integer) obj2;
                if (!aa.equals(bb)) {
                    System.out.println("类型为【" + fieldType + "】的值不相同！");
                    return false;
                } else {
                    System.out.println("类型为" + fieldType + "的值相同！");
                    return true;
                }
            }
            if ("class java.lang.Double".equals(fieldType) || "double".equals(fieldType)) {
                Double aa = (Double) obj1;
                Double bb = (Double) obj2;
                if (Math.abs(aa - bb) >= 0.01) {
                    System.out.println("类型为【" + fieldType + "】的值不相同！");
                    return false;
                } else {
                    System.out.println("类型为" + fieldType + "的值相同！");
                    return true;
                }
            }
            if ("class java.lang.Float".equals(fieldType) || "float".equals(fieldType)) {
                Float aa = (Float) obj1;
                Float bb = (Float) obj2;
                if (Math.abs(aa - bb) >= 0.01) {
                    System.out.println("类型为【" + fieldType + "】的值不相同！");
                    return false;
                } else {
                    System.out.println("类型为" + fieldType + "的值相同！");
                    return true;
                }
            }
            if ("class java.lang.String".equals(fieldType)) {
                String aa = (String) obj1;
                String bb = (String) obj2;
                if (!aa.equals(bb)) {
                    System.out.println("类型为【" + fieldType + "】的值不相同！");
                    return false;
                } else {
                    System.out.println("类型为" + fieldType + "的值相同！");
                    return true;
                }
            }
            if ("class java.util.Date".equals(fieldType)) {
                Date aa = (Date) obj1;
                Date bb = (Date) obj2;
                if (aa.getTime() != bb.getTime()) {
                    System.out.println("类型为【" + fieldType + "】的值不相同！");
                    return false;
                } else {
                    System.out.println("类型为" + fieldType + "的值相同！");
                    return true;
                }
            }
        }
        return flag;
    }


	public ISysRecordService getSysRecordService() {
		return sysRecordService;
	}

	public void setSysRecordService(ISysRecordService sysRecordService) {
		this.sysRecordService = sysRecordService;
	}
	

}
