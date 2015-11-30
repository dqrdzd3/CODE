package com.hw.hwsafe.smart.constants;

/**
 * @author 姚欣欣
 * @time 2015/11/5 15:26
 */
public class FrameConstant {
    //气体类型列表 bgn
    public static final int GAS_TYPE_CH4 = 0x01;
    public static final int GAS_TYPE_NH3 = 0x02;
    public static final int GAS_TYPE_H2S = 0x03;
    public static final int GAS_TYPE_CO = 0x04;
    public static final int GAS_TYPE_O2 = 0x05;
    public static final int GAS_TYPE_H2 = 0x06;
    public static final int GAS_TYPE_C2H6 = 0x07;
    public static final int GAS_TYPE_C2H4 = 0x08;
    public static final int GAS_TYPE_C2H2 = 0x09;
    public static final int GAS_TYPE_C3H8 = 0x0A;
    public static final int GAS_TYPE_C3H6 = 0x0B;
    public static final int GAS_TYPE_C4H10 = 0x0C;
    public static final int GAS_TYPE_C4H8 = 0x0D;
    public static final int GAS_TYPE_C4H6 = 0x0E;
    public static final int GAS_TYPE_Ligh_Oil = 0x0F;//轻油
    public static final int GAS_TYPE_Heavy_Oil = 0x10;//重油
    public static final int GAS_TYPE_Gasoline = 0x11;//汽油
    public static final int GAS_TYPE_Diesel_Oil = 0x12;//柴油
    public static final int GAS_TYPE_Kerosene = 0x13;//煤油
    public static final int GAS_TYPE_CH3OH = 0x14;
    public static final int GAS_TYPE_C2H5OH = 0x15;
    public static final int GAS_TYPE_CH32CHOH = 0x16;
    public static final int GAS_TYPE_HCHO = 0x17;
    public static final int GAS_TYPE_C3H7CHO = 0x18;
    public static final int GAS_TYPE_C3H6O = 0x19;
    public static final int GAS_TYPE_CH3COC2H5 = 0x1A;
    public static final int GAS_TYPE_C6H6 = 0x1B;//苯
    public static final int GAS_TYPE_Methylbenzene = 0x1C;//甲苯
    public static final int GAS_TYPE_Xylene = 0x1D;//二甲苯
    public static final int GAS_TYPE_Styrene = 0x1E;//苯乙烯
    public static final int GAS_TYPE_Phenol = 0x1F;//苯酚
    public static final int GAS_TYPE_C2H5OC2H5 = 0x20;//乙醚
    public static final int GAS_TYPE_C2H6O = 0x21;//二甲醚
    public static final int GAS_TYPE_Petroleum_Ether = 0x22;//石油醚
    public static final int GAS_TYPE_C2H7N = 0x23;//二甲胺
    public static final int GAS_TYPE_CH33N = 0x24;//三甲胺
    public static final int GAS_TYPE_HCONH2 = 0x25;//甲酰胺
    public static final int GAS_TYPE_Tetrahydrofuran = 0x26;//四氢呋喃
    public static final int GAS_TYPE_CH3COOCH2CH3 = 0x27;//醋酸乙酯
    public static final int GAS_TYPE_C7H7Cl = 0x28;//氯代甲苯
    public static final int GAS_TYPE_Epoxyethane = 0x29;//环氧乙烷
    public static final int GAS_TYPE_O3 = 0x2A;// 臭氧
    public static final int GAS_TYPE_SO2 = 0x2B;
    public static final int GAS_TYPE_NO2 = 0x2C;
    public static final int GAS_TYPE_NO = 0x2D;
    public static final int GAS_TYPE_HCL = 0x2E;
    public static final int GAS_TYPE_HCN = 0x2F;
    public static final int GAS_TYPE_CO2 = 0x30;
    public static final int GAS_TYPE_CL2 = 0x31;
    public static final int GAS_TYPE_CombustibleGas = 0x32;//可燃气体
    public static final int GAS_TYPE_C3H3N = 0x33;
    public static final int GAS_TYPE_HF = 0x34;
    public static final int GAS_TYPE_PH3 = 0x35;
    public static final int GAS_TYPE_CLO2 = 0x36;
    public static final int GAS_TYPE_C4H8S = 0x37;
    public static final int GAS_TYPE_CH3I = 0x38;
    public static final int GAS_TYPE_CHCL3 = 0x39;
    public static final int GAS_TYPE_SiH4 = 0x3A;
    public static final int GAS_TYPE_C2H3CL = 0x3B;
    public static final int GAS_TYPE_COCL2 = 0x3C;
    public static final int GAS_TYPE_AsH3 = 0x3D;
    public static final int GAS_TYPE_HBr = 0x3E;
    public static final int GAS_TYPE_CS2 = 0x3F;
    public static final int GAS_TYPE_C6H12 = 0x40;
    public static final int GAS_TYPE_ToxicGas = 0x41;//毒性气体
    public static final int GAS_TYPE_OneCH3NH2 = 0x42;//一甲胺
    public static final int GAS_TYPE_CH3NH2 = 0x43;//甲胺
    public static final int GAS_TYPE_DMF = 0x44;
    public static final int GAS_TYPE_Organic_Amine = 0x45;//有机胺
    public static final int GAS_TYPE_SF6 = 0x46;
    public static final int GAS_TYPE_Isobutene = 0x47;//异丁烯
    public static final int GAS_TYPE_Aniline = 0x48;//苯胺
    public static final int GAS_TYPE_H2O2 = 0x49;
    public static final int GAS_TYPE_C2H2Cl4 = 0x4A;//双光气
    public static final int GAS_TYPE_C2H53N = 0x4B;//三乙胺
    public static final int GAS_TYPE_CH3CN = 0x4C;//乙腈
    public static final int GAS_TYPE_HNO3 = 0x4D;
    public static final int GAS_TYPE_C3H5OCL = 0x4E;
    public static final int GAS_TYPE_C3H6CL2O = 0x4F;
    public static final int GAS_TYPE_CCL4 = 0x50;
    //气体类型列表 end
    //单位列表 bgn
    public static final int UNIT_NO = 0x00;
    public static final int UNIT_LEL = 0x01;
    public static final int UNIT_VOL = 0x02;
    public static final int UNIT_PPM = 0x03;
    public static final int UNIT_UMOLMOL = 0x04;
    public static final int UNIT_C = 0x05;
    public static final int UNIT_RH = 0x06;
    public static final int UNIT_M = 0x07;
    public static final int UNIT_MS = 0x08;
    public static final int UNIT_KMH = 0x09;
    public static final int UNIT_LX = 0x0A;
    public static final int UNIT_PA = 0x0B;
    public static final int UNIT_DB = 0x0C;
    public static final int UNIT_M3 = 0x0D;
    public static final int UNIT_M2 = 0x0E;
    public static final int UNIT_KG = 0x0F;
    public static final int UNIT_G = 0x10;
    public static final int UNIT_MM = 0x11;
    public static final int UNIT_CM = 0x12;
    public static final int UNIT_UGM3 = 0x13;
    public static final int UNIT_PCSML = 0x14;
    public static final int UNIT_MAH = 0x15;
    //单位列表 end
    //其他检测对象 bgn
    public static final int OTHER_GAS_TEMPERATURE = 0xC9;
    public static final int OTHER_GAS_HUMIDITY = 0xCA;//湿度
    public static final int OTHER_GAS_PRESSURE = 0xCB;
    public static final int OTHER_GAS_FLOW = 0xCC;
    public static final int OTHER_GAS_WIND_SPEED = 0xCD;
    public static final int OTHER_GAS_WIND_DIRECTION = 0xCE;
    public static final int OTHER_GAS_LIQUID_LEVEL = 0xCF;//液位
    public static final int OTHER_GAS_ILLUMINANCE = 0xD0;//光照度
    public static final int OTHER_GAS_NOISE = 0xD1;
    public static final int OTHER_GAS_WEIGHT = 0xD2;
    public static final int OTHER_GAS_VOLUME = 0xD3;
    public static final int OTHER_GAS_HEIGHT = 0xD4;
    public static final int OTHER_GAS_LENGTH = 0xD5;
    public static final int OTHER_GAS_AIR_PRESSURE = 0xD6;
    public static final int OTHER_GAS_ELEVATION = 0xD7;
    public static final int OTHER_GAS_PM2_5 = 0xD8;//PM2.5
    public static final int OTHER_GAS_VOC = 0xD9;
    public static final int OTHER_GAS_ELECTRIC_QUANTITY = 0xDA;//电量
    public static final int OTHER_GAS_O3_ANION = 0xDB;//负氧离子
    //其他检测对象 end
}
